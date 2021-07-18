package com.liukunup.next.service.impl;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.Media;
import com.liukunup.next.bean.TessParam;
import com.liukunup.next.constant.RetCode;
import com.liukunup.next.service.ITessService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.leptonica.BOX;
import org.bytedeco.leptonica.BOXA;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;
import static org.bytedeco.tesseract.global.tesseract.RIL_TEXTLINE;

/**
 * Tesseract OCR Service Implement
 * @author Liu Kun
 * @date 2021-04-06 01:09:37
 **/
@Slf4j
@Service("tessService")
public class TesseractServiceImpl implements ITessService {

    @Value("${tesseract.tessdata.prefix}")
    public String dataPath;

    @Value("${tesseract.language}")
    public String language;

    @Value("${tesseract.oem}")
    public String oem;

    @Override
    public ApiResult<Object> process(TessParam param) {
        // 检查入参是否合法
        if (null == param) {
            return new ApiResult<>(RetCode.INVALID_PARAM);
        }
        Media image = param.getImage();
        if (null == image) {
            return new ApiResult<>(RetCode.INVALID_PARAM);
        }
        String language = param.getLanguage();
        try {
            // 处理成功
            return exec(image, language);
        } catch (IOException e) {
            // 打印处理异常
            log.error("{}", e.getMessage());
        }
        // 处理失败
        return new ApiResult<>(RetCode.FAILED);
    }

    private ApiResult<Object> exec(Media media, String language) throws IOException {
        TessBaseAPI api = new TessBaseAPI();
        // 初始化接口
        if (api.Init(dataPath, language) != 0) {
            log.error("Could not initialize tesseract.");
            return new ApiResult<>(RetCode.INVALID_PARAM, "Could not initialize tesseract. dataPath: " + dataPath);
        }
        // 待处理的图像
        PIX image = null;
        // 从图像链接读取 优先级最高
        String imageUrl = media.getUrl();
        if (!StringUtils.isEmpty(imageUrl)) {
            image = readFromUrl(imageUrl);
            api.SetImage(image);
        }

        ApiResult<Object> result = new ApiResult<>(RetCode.OK);
        result.setData(basicProcess(api, image));

        pixDestroy(image);
        api.End();

        return result;
    }

    /**
     * Basic
     * @param api   已初始化的接口实例
     * @param image 待处理的图像
     * @return 识别结果
     */
    private String basicProcess(@NonNull TessBaseAPI api, @NonNull PIX image) {
        // 获取输出结果
        BytePointer outText = api.GetUTF8Text();
        String content = outText.getString();
        // 销毁对象并释放内存
        outText.deallocate();
        return content;
    }

    private String advanceProcess(@NonNull TessBaseAPI api, @NonNull PIX image) {
        BOXA boxa = api.GetComponentImages(RIL_TEXTLINE, true, null, (int[]) null);
        for (int i = 0; i < boxa.n(); i++) {

            BOX box = boxa.box(i);
            api.SetRectangle(box.x(), box.y(), box.w(), box.h());

            // 获取输出结果
            BytePointer outText = api.GetUTF8Text();
            String content = outText.getString();

            int confidence = api.MeanTextConf();
            log.info("Box[{}]: x={}, y={}, w={}, h={}, confidence: {}, text: {}",
                    i, box.x(), box.y(), box.w(), box.h(), confidence, content);

            // 销毁对象并释放内存
            outText.deallocate();
        }

        return null;
    }

    /**
     * 从图像链接读取
     * @param url 图像链接
     * @return 图像对象
     */
    private PIX readFromUrl(String url) throws IOException {
        log.debug("read image from url {}", url);
        URL imageUrl = new URL(url);
        File file = Loader.cacheResource(imageUrl);
        return pixRead(file.getAbsolutePath());
    }

    /**
     * 从图像数据读取
     * @param base64 图像数据
     * @return 图像对象
     */
    private PIX readFromBase64(String base64) throws IOException {
        log.debug("read image from base64 {}", base64.substring(0, 16));
        // TODO
        return null;
    }

    /**
     * 从图像文件读取
     * @param file 图像文件
     * @return 图像对象
     */
    private PIX readFromFile(File file) throws IOException {
        log.debug("read image from file {}", file);
        // TODO
        return null;
    }
}
