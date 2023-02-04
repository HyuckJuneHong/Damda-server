package kr.co.error.model;

import kr.co.enums.ErrorCode;

public class ResponseFormat<T> {

    public boolean isResult() {
        return result;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public ResponseFormat(boolean result,
                          T data,
                          String message,
                          int status) {
        this.result = result;
        this.data = data;
        this.message = message;
        this.status = status;
    }

    private boolean result;

    private T data;

    private String message;

    private int status;

    public static ResponseFormat ok(){
        return new ResponseFormat(
                true,
                null,
                ErrorCode.SUCCESS_NULL.getMessage(),
                ErrorCode.SUCCESS_NULL.getStatus()
        );
    }

    public static <T> ResponseFormat ok(T data){
        return new ResponseFormat(
                true,
                data,
                ErrorCode.SUCCESS_NULL.getMessage(),
                ErrorCode.SUCCESS_NULL.getStatus()
        );
    }

    public static ResponseFormat fail(String message){
        return new ResponseFormat(
                false,
                null,
                ErrorCode.FAIL_NULL.getMessage(),
                ErrorCode.FAIL_NULL.getStatus()
        );
    }

    public static ResponseFormat expire(){
        return new ResponseFormat(
                false,
                null,
                ErrorCode.FAIL_EXPIRE.getMessage(),
                ErrorCode.FAIL_EXPIRE.getStatus()
        );
    }}