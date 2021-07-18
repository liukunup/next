package com.liukunup.next.bean;

import com.liukunup.next.constant.RetCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接口响应包装泛型
 * @author Liu Kun
 * @date 2021-06-20 14:34:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 临时自定义返回值
     * @param code    返回值
     * @param message 返回消息
     */
    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    /**
     * 默认返回值+数据
     * @param retCode 返回值枚举
     * @param data    数据
     */
    public ApiResult(RetCode retCode, T data) {
        this(retCode.getCode(), retCode.getMessage(), data);
    }

    /**
     * 默认返回值
     * @param retCode 返回值枚举
     */
    public ApiResult(RetCode retCode) {
        this(retCode, null);
    }

    /**
     * 检查接口返回结果
     * @param success 是否成功
     * @param info 附带信息
     * @return 待输出的返回信息对象
     */
    public static ApiResult<Object> check(boolean success, Object info) {
        if (success) {
            return new ApiResult<>(RetCode.OK, info);
        } else {
            return new ApiResult<>(RetCode.FAILED, null);
        }
    }
}
