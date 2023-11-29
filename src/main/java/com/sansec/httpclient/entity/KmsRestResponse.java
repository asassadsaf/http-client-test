package com.sansec.httpclient.entity;

import lombok.ToString;


import java.io.Serializable;
import java.util.Locale;

/**
 * KMS 全局统一响应类
 *
 * ps:
 */
@ToString
public class KmsRestResponse<T> implements Serializable {
    private static final long serialVersionUID = 5778573516446596671L;

    private static final String SUCCESS_CODE="0";

    /**
     * 消息模板占位符，用来替换自定义信息
     */
    private static final String PLACEHOLDER="${customMessage}";

    private String code;
    private String message;
    private T result;

    private String requestId;
    private String timestamp;
    private Long costMillis;

    private KmsRestResponse() {
    }

    private KmsRestResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private KmsRestResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }



    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCostMillis() {
        return costMillis;
    }

    public void setCostMillis(Long costMillis) {
        this.costMillis = costMillis;
    }


    public boolean successStatus() {
        return SUCCESS_CODE.equalsIgnoreCase(this.code);
    }

    public boolean failStatus() {
        return !SUCCESS_CODE.equalsIgnoreCase(this.code);
    }

}
