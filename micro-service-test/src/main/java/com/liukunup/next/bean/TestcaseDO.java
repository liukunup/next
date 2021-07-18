package com.liukunup.next.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试用例
 * 业务抽象对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:19:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TestcaseDO implements Serializable {

    /**
     * 输入对象：包括但不限于输入参数、输入文件、输入链接
     * 用于存储被测对象的输入
     * 建议使用json格式
     */
    private String param;

    /**
     * 期待结果：包括但不限于标注数据、预期结果、历史输出
     * 用于存储被测对象在指定输入对象下的期望结果
     * 建议使用json格式
     * 可选
     */
    private String except;

    /**
     * 标签信息
     * 支持中英文
     * 以（英文）逗号作为分隔符
     * 可选
     */
    private String labels;

    /**
     * 用例描述
     * 用于存储用例名称等描述性信息
     * 可选
     */
    private String description;

    /**
     * 用例编号
     * 仅在修改或删除用例时有效
     */
    private Long id;
}
