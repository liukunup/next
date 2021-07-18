package com.liukunup.next.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用例
 * 持久化对象
 *
 * @author Liu Kun
 * @since 2020-08-04
 */
@Entity
@Table(name="t_testcase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TestcasePO extends BaseEntity {

    /**
     * 输入对象：包括但不限于输入参数、输入文件、输入链接
     * 用于存储被测对象的输入
     *
     * 建议使用json格式
     */
    @Column(name = "param_text", columnDefinition = "TEXT", nullable = false)
    private String param;

    /**
     * 期待结果：包括但不限于标注数据、预期结果、历史输出
     * 用于存储被测对象在指定输入对象下的期望结果
     *
     * 建议使用json格式
     */
    @Column(name = "except_text", columnDefinition = "TEXT", nullable = false)
    private String except;

    /**
     * 用例描述
     * 用于存储用例名称等描述性信息
     *
     * 可选
     */
    @Column(name = "description", length = 1024)
    private String description;

    /**
     * 用例标签
     * 用于存储用例标签信息,便于后续筛选和分析
     * 可选
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private List<LabelPO> labelList = new ArrayList<>();

    /**
     * 标签创建者
     * 可选
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
    private UserPO user;
}
