package com.raymond.util;

/**
 * JSONResult : Response JSONResult for RESTful,封装返回JSON格式的数据
 */

public class JSONResult<T> extends Result {

    private static final long serialVersionUID = 7880907731807860636L;

    /**
     * 数据
     */
    private T data;


    public T getData() {
        return data;
    }

    public JSONResult<T> setData(T data) {
        this.data = data;
        map.put("data", data);
        return this;
    }

    public JSONResult() {
        super();
    }

    /**
     * 自定义返回的结果
     *
     * @param data
     * @param message
     * @param success
     */
    public JSONResult(T data, int statusCode , String message,boolean success) {
    		this.setData(data);
        super.setMessage(message);
        super.setSuccess(success);
        super.setStatusCode(statusCode);
    }

    /**
     * 成功返回数据和消息
     *
     * @param data
     * @param message
     */
    public JSONResult(T data, int statusCode , String message ) {
    		this.setData(data);
        super.setMessage(message);
        super.setStatusCode(statusCode);
        super.setSuccess(true);
    }

    /**
     * 成功返回数据
     *
     * @param data
     */
    public JSONResult(T data) {
    	this.setData(data);
    }
}