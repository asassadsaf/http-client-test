package com.sansec.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sansec.httpclient.entity.*;
import com.sansec.httpclient.util.OkHttpClientUtils;
import com.sansec.kms.base.common.param.response.KmsRestResponse;
import com.sansec.kms.client.KMSCoreClient;
import com.sansec.kms.client.KMSCoreClientImpl;
import com.sansec.kms.entity.response.KeyDTO;
import com.sansec.kms.utils.BaseRequestConfig;
import com.sansec.kms.utils.ConnectionInitConfig;
import okhttp3.MediaType;

import java.security.PublicKey;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2023/11/28 16:21
 */
public class main {
    static AtomicLong count = new AtomicLong(0);
    public static void main(String[] args) {
        int threadCount = Integer.parseInt(args[0]);
        String ip = args[1];
        String keyId = args[2];

        long start = System.currentTimeMillis();
        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(1000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(32, 100, 30, TimeUnit.SECONDS, arrayBlockingQueue);
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, arrayBlockingQueue);
        executor2.execute(() -> {
            while (true){
                long countA = count.get();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(count.get() - countA + "     ");
                System.out.println(count.get() / ((System.currentTimeMillis() - start) / 1000));

            }
        });

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                com.sansec.kms.entity.request.GetMaterialRequest request = new com.sansec.kms.entity.request.GetMaterialRequest();
                request.setKeyId(keyId);
                ConnectionInitConfig config = new ConnectionInitConfig();
                config.setOriginHeader("web_console");
                KMSCoreClient kmsCoreClient = new KMSCoreClientImpl(config);
                BaseRequestConfig baseRequestConfig = new BaseRequestConfig();
                baseRequestConfig.setRestUrl("http://" + ip + ":3333/kms/core/v4");
                while (true){
//                    getMaterial(request);
                    KmsRestResponse<KeyDTO> material = kmsCoreClient.getMaterial(baseRequestConfig, request);
                    count.incrementAndGet();
                }
            });
        }
    }
//public static void main(String[] args) {
//    com.sansec.kms.entity.request.GetMaterialRequest request = new com.sansec.kms.entity.request.GetMaterialRequest();
//    request.setKeyId("Symm31d2dabd-a219-491a-af40-87b1f5196c64");
//    ConnectionInitConfig config = new ConnectionInitConfig();
//    config.setOriginHeader("web_console");
//    KMSCoreClient kmsCoreClient = new KMSCoreClientImpl(config);
//    BaseRequestConfig baseRequestConfig = new BaseRequestConfig();
//    baseRequestConfig.setRestUrl("http://10.20.44.67:3333/kms/core/v4");
//    com.sansec.kms.base.common.param.response.KmsRestResponse<com.sansec.kms.entity.response.KeyDTO> material = kmsCoreClient.getMaterial(baseRequestConfig, request);
//    System.out.println(material);
//}
//    public static KmsRestResponse<KeyDTO> getMaterial(GetMaterialRequest getKeyRequest) {
//        String res = OkHttpClientUtils.doPost("http://10.20.44.67:3333/kms/core/v4/keys/getmaterial", JSON.toJSONString(getKeyRequest));
//        KmsRestResponse response = JSON.parseObject(res, KmsRestResponse.class);
//        response.setResult(((JSONObject)response.getResult()).toJavaObject(KeyDTO.class));
//        return response;
//    }
}
