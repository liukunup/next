package com.liukunup.next.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字段比较对象
 * 业务抽象对象
 *
 * KVVR -> Key Value Value Remarks
 *
 * @author Liu Kun
 * @since 2021-07-18 23:10:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FieldCompareDO implements Serializable {

    /**
     * 字段名
     * 必选
     */
    private String field;

    /**
     * 被测对象 输出结果
     * 可选
     */
    private String object;

    /**
     * 参考对象 期望结果
     * 可选
     */
    private String reference;

    /**
     * 附加信息
     * 用于存储和字段相关的信息
     * 例如：置信度、来源等
     * 可选
     */
    private Object remarks;
}
