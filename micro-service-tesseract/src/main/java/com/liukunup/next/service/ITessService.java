package com.liukunup.next.service;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.TessParam;

/**
 * OCR Service Interface
 * @author Liu Kun
 * @date 2021-06-20 15:26:18
 **/
public interface ITessService {

    /**
     * OCR process
     * @param param 输入参数
     * @return 识别结果
     */
    ApiResult<Object> process(TessParam param);
}
