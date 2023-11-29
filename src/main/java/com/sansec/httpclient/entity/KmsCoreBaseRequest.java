package com.sansec.httpclient.entity;


public class KmsCoreBaseRequest extends KmsRestRequest<String> {

    private String origin;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
