package com.liukunup.next.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Tesseract 输入参数对象
 * @author Liu Kun
 * @date 2021-06-20 16:53:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TessParam implements Serializable {

    /**
     * 图片对象
     */
    private Media image;

    /**
     * ROI区域设置
     */
    private Rectangle rect;

    /**
     * 语言设置
     */
    private String language;

    /**
     * OEM配置
     * 可选
     */
    private Integer oem;
}
