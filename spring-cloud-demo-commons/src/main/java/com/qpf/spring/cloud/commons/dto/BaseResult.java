package com.qpf.spring.cloud.commons.dto;

import lombok.Data;
import lombok.Setter;

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
        baseResult.setErrors(errors);
        baseResult.setCursor(cursor);
        return baseResult;
    }

    /**
     * 操作成功
     * @param data
     * @param message
     * @param cursor
     * @return
     */
    public static BaseResult OK(Object data, String message, Cursor cursor) {
        return crateBaseResult(true, data, message, null, null, cursor);
    }

    /**
     * 操作成功
     * @param data
     * @param message
     * @return
     */
    public static BaseResult OK(Object data, String message) {
        return OK(data, message, null);
    }

    /**
     * 操作成功
     * @param data
     * @param cursor
     * @return
     */
    public static BaseResult OK(Object data, Cursor cursor) {
        return OK(data, SUCCESS_MESSAGE, cursor);
    }

    /**
     * 操作成功
     * @param data
     * @return
     */
    public static BaseResult OK(Object data) {
        return OK(data, SUCCESS_MESSAGE);
    }

    /**
     * 操作成功
     * @return
     */
    public static BaseResult OK() {
        return OK(null);
    }

    /**
     * 操作失败
     * @param message
     * @return
     */
    public static BaseResult ER(String message) {
        return crateBaseResult(false, null, message, null, null, null);
    }

    /**
     * 操作失败
     * @param message
     * @param errors
     * @return
     */
    public static BaseResult ER(String message, List<Error> errors) {
        return crateBaseResult(false, null, message, null, errors, null);
    }

    /**
     * 操作失败
     * @param errors
     * @return
     */
    public static BaseResult ER(List<Error> errors) {
        return crateBaseResult(false, null, FAILED_MESSAGE, null, errors, null);
    }

    /**
     * 操作失败
     * @param message
     * @param errors
     * @return
     */
    public static BaseResult ER(String message, Error... errors) {
        return crateBaseResult(false, null, message, null, Arrays.asList(errors), null);
    }

    /**
     * 操作失败
     * @param errors
     * @return
     */
    public static BaseResult ER(Error... errors) {
        return crateBaseResult(false, null, FAILED_MESSAGE, null, Arrays.asList(errors), null);
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
