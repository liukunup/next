package com.liukunup.next.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 标签
 * 持久化对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:22:19
 */
@Entity
@Table(name="t_label")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LabelPO extends BaseEntity {

    /**
     * 标签内容
     */
    @Column(name = "content", unique = true, nullable = false, length = 512)
    private String content;

    /**
     * 标签创建者
     * 可选
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
    private UserPO user;
}
