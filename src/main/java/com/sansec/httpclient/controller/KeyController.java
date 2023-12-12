package com.sansec.httpclient.controller;

import com.sansec.kms.base.common.param.response.KmsRestResponse;
import com.sansec.kms.entity.request.GetMaterialRequest;
import com.sansec.kms.entity.response.KeyDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2023/12/11 19:12
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class KeyController {

    @PostMapping(value = "/kms/core/v4/keys/getmaterial")
    public KmsRestResponse<KeyDTO> getKey(@RequestBody GetMaterialRequest request) {
        return KmsRestResponse.success(new KeyDTO());
    }
}
