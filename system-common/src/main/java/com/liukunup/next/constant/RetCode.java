package com.liukunup.next.constant;

/**
 * 返回码枚举
 * @author Liu Kun
 * @date 2021-06-20 14:45:00
 **/
public enum RetCode {

    /**
     * 非法参数
     */
    INVALID_PARAM(-1000, "Invalid param! please check your input!"),

    /**
     * 失败
     */
    FAILED(-1, "FAILED"),

    /**
     * 成功
     */
    OK(0, "SUCCESS");

    /**
     * 返回码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String message;

    RetCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
