package com.liukunup.next.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

/**
 * 多媒体对象
 * @author Liu Kun
 * @date 2021-06-20 15:08:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media implements Serializable {

    /**
     * 多媒体链接
     * 最高优先级
     */
    private String url;

    /**
     * 多媒体数据
     * 优先级 由大到小
     */
    private String base64;

    /**
     * 多媒体文件
     * 最低优先级
     */
    private File file;

    /**
     * 多媒体对象属性
     */
    private MediaAttribute attr;
}
