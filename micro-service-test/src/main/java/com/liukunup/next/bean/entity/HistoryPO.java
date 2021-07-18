package com.liukunup.next.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 测试用例执行历史记录
 * 持久化对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:29:02
 */
@Entity
@Table(name="t_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HistoryPO extends BaseEntity {

    /**
     * 任务编号
     */
    @Column(name = "task_id", length = 128, nullable = false, updatable = false)
    private String taskId;

    /**
     * 用例编号
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "testcase_id", referencedColumnName = "id", nullable = false, updatable = false)
    private TestcasePO testcase;

    /**
     * 字段名
     */
    @Column(name = "field", length = 256, nullable = false, updatable = false)
    private String field;

    /**
     * 被测对象 输出结果
     */
    @Column(name = "object", length = 1024, updatable = false)
    private String object;

    /**
     * 参考对象 期望结果
     */
    @Column(name = "reference", length = 1024, updatable = false)
    private String reference;

    /**
     * 附加信息
     */
    @Column(name = "remarks", length = 1024, updatable = false)
    private String remarks;

    /**
     * 记录创建者
     * 可选
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
    private UserPO user;
}
