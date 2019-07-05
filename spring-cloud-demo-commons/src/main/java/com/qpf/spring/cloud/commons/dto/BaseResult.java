package com.qpf.spring.cloud.commons.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
public class BaseResult {

    private final static String SUCCESS_MESSAGE = "操作成功";
    private final static String FAILED_MESSAGE = "操作失败";

    private Boolean result;
    private Object  data;
    private String message;
    private List<Error> errors;
    private Cursor cursor;
    private String token;

    private static BaseResult crateBaseResult(Boolean result, Object data, String message, String token, List<Error> errors, Cursor cursor) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setMessage(message);
        baseResult.setToken(token);
        baseResult.setErrors(errors);
        baseResult.setCursor(cursor);
        return baseResult;
    }

    /**
     * 操作成功
     * @param data      数据
     * @param message   信息
     * @param cursor    游标
     * @return          返回结果
     */
    public static BaseResult OK(Object data, String message, Cursor cursor) {
        return crateBaseResult(true, data, message, null, null, cursor);
    }

    /**
     * 操作成功
     * @param data      数据
     * @param message   信息
     * @return          返回结果
     */
    public static BaseResult OK(Object data, String message) {
        return OK(data, message, null);
    }

    /**
     * 操作成功
     * @param data      数据
     * @param cursor    游标
     * @return          返回结果
     */
    public static BaseResult OK(Object data, Cursor cursor) {
        return OK(data, SUCCESS_MESSAGE, cursor);
    }

    /**
     * 操作成功
     * @param data      数据
     * @return          返回结果
     */
    public static BaseResult OK(Object data) {
        return OK(data, SUCCESS_MESSAGE);
    }

    /**
     * 操作成功
     * @return          返回结果
     */
    public static BaseResult OK() {
        return OK(null);
    }

    /**
     * 操作失败
     * @param message   信息
     * @return          返回结果
     */
    public static BaseResult ER(String message) {
        return crateBaseResult(false, null, message, null, null, null);
    }

    /**
     * 操作失败
     * @param message   信息
     * @param errors    错误信息
     * @return          返回结果
     */
    public static BaseResult ER(String message, List<Error> errors) {
        return crateBaseResult(false, null, message, null, errors, null);
    }

    /**
     * 操作失败
     * @param errors    错误信息
     * @return          返回结果
     */
    public static BaseResult ER(List<Error> errors) {
        return crateBaseResult(false, null, FAILED_MESSAGE, null, errors, null);
    }

    /**
     * 操作失败
     * @param message   信息
     * @param errors    错误信息
     * @return          返回结果
     */
    public static BaseResult ER(String message, Error... errors) {
        return crateBaseResult(false, null, message, null, Arrays.asList(errors), null);
    }

    /**
     * 操作失败
     * @param errors    错误信息
     * @return          返回结果
     */
    public static BaseResult ER(Error... errors) {
        return crateBaseResult(false, null, FAILED_MESSAGE, null, Arrays.asList(errors), null);
    }

    /**
     * 插入错误信息
     * @param error     错误信息
     * @return          返回结果
     */
    public BaseResult errors(Error error) {
        if (!getResult()) {
            if (getErrors() == null) {
                this.errors = new ArrayList<>();
            }
            this.errors.add(error);
        }
        return this;
    }

    @Data
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    public static class Error {
        private String field;
        private String message;
    }
}
