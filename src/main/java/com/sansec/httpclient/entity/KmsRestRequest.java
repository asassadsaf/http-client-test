package com.sansec.httpclient.entity;

import java.io.Serializable;

public class KmsRestRequest<T> implements Serializable {
    private static final long serialVersionUID = 5778573516446596672L;

    private String requestId;

    public KmsRestRequest() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
