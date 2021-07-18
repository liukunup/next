package com.liukunup.next.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 多媒体对象属性
 * @author Liu Kun
 * @date 2021-06-20 15:14:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaAttribute implements Serializable {

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 通道数
     */
    private Integer channel;

    /**
     * 格式
     */
    private String format;

    /**
     * 颜色空间
     */
    private String colorSpace;
}
