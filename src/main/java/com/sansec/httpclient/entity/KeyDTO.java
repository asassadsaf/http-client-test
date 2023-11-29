package com.sansec.httpclient.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeyDTO implements Serializable {

    private String keyMaterial;

    private String objectType;

    private String keyFormatType;

    private String rotateStatus;

    private String rotateStartTime;

    private String contactInformation;

    private String remark;

    private String cryptographicAlgorithm;

    private String cryptographicLength;

    private String startTime="";

    private String stopTime="";

    private String keyType="";

    private String keyBits="";

    private String keyAlg="";

    private String symmetricKeyBase64="";

    private String symmetricKeyLen;

    private String symmetricKeyLenBase64;

    private String publicKeyBase64="";

    private String publicKeyLen;

    private String publicKeyLenBase64;

    private String privateKeyBase64="";

    private String privateKeyLen;

    private String privateKeyLenBase64;

    private String privateKeyStatus;

    private String publicKeyStatus;

    private String symmetricKeyStatus;

    private String sExtractable;

    //KMIP add
    private String keyHash;

    private String sActivationDate;

    private String sArchiveDate;

    private String sCompromiseDate;

    private String sCompromiseOccurrenceDate;

    private String sCryptographicUsageMask;

    private String sDeactivationDate="";

    private String sDestroyDate;

    private String sInitialDate;

    private Integer sKeyVersion;

    private String sLastChangeDate="";

    private String sProcessStartDate;

    private String sProtectStopDate="";

    private Integer sState;

    private String sUniqueIdentifier;

    private String keyLink;

    private String sNameValue;

    private String sNameType;

    //KMIP ext
    private String alwaysSensitive;

    private String description;

    private String neverExtractable;

    private String originalCreationDate;

    private String pkcs12FriendlyName;

    private String sensitive1;

    private String sComment;

    private String sDeleteAble;

    private Integer sLeaseTime;

    private String sOperationPolicyName;

    private String sRevocationMessage;

    private String sRevocationReasonCode;

    private Integer sUsageLimitsCount;

    private Integer sUsageLimitsTotal;

    private String sUsageLimitsUnit;

    private String privateKeyId;

    private String publicKeyId;

    private String archiveFlag;


}
