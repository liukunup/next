package com.liukunup.next.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 用户
 * 持久化对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:39:42
 */
@Entity
@Table(name="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPO extends BaseEntity {

    /**
     * 工号
     */
    @Column(name = "employee_id", unique = true, nullable = false, length = 10, updatable = false)
    private String employeeId;

    /**
     * 昵称
     * 可选
     */
    @Column(name = "nickname", length = 64)
    private String nickname;

    /**
     * 电话
     * 可选
     */
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 邮箱
     * 可选
     */
    @Column(name = "email", length = 32)
    private String email;

    /**
     * 部门
     * 可选
     */
    @Column(name = "department", length = 64)
    private String department;

    /**
     * UUID长度为32位 包含4个横线
     * 因此 总长度为 32+4= 36位
     * 不填自动生成
     * 可选
     */
    @Column(name = "secret_key", length = 36, nullable = false, updatable = false)
    private String secretKey;
}
