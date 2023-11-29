package com.sansec.httpclient.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yaoxin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetKeyRequest extends KmsCoreBaseRequest {

    private String keyName;

    private String keyId;

    private String clientIp;

    private String username;

    private String tenantAccount;


    private String plainText;

}
