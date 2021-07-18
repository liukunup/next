package com.liukunup.next.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 * 业务抽象对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:39:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDO implements Serializable {

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 昵称
     * 可选
     */
    private String nickname;

    /**
     * 电话
     * 可选
     */
    private String phone;

    /**
     * 邮箱
     * 可选
     */
    private String email;

    /**
     * 部门
     * 可选
     */
    private String department;

    /**
     * 秘钥
     * 不填自动生成
     * 可选
     */
    private String secretKey;
}
