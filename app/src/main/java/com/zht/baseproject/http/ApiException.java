package com.zht.baseproject.http;

/**
 * Created by ZHT on 2017/4/19.
 * 自定义数据返回异常处理
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 0;

    public static final int DATA_ERROR = 1;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 将错误码转换成为错误信息
     * @param code 错误码
     * @return 错误信息
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "用户不存在";
                break;

            case DATA_ERROR:
                message = "数据出错";
                break;

            default:
                break;
        }

        return message;
    }
}
