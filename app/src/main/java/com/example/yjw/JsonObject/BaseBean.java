package com.example.yjw.JsonObject;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */
public class BaseBean<T> {
    private boolean Success;

    private String Message;

    private T ResultObj;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getResultObj() {
        return ResultObj;
    }

    public void setResultObj(T resultObj) {
        ResultObj = resultObj;
    }
}
