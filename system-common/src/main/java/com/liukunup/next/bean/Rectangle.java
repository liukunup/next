package com.liukunup.next.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 矩形对象
 * @author Liu Kun
 * @date 2021-06-20 22:43:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle implements Serializable {

    private Integer left;

    private Integer top;

    private Integer width;

    private Integer height;
}
