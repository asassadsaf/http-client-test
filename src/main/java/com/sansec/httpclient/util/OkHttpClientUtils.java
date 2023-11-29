package com.sansec.httpclient.util;

import com.sansec.httpclient.constant.HttpStatus;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2023/11/28 16:04
 */
public class OkHttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtils.class);

    private static OkHttpClient client;

    private static final String DEFAULT_MEDIA_TYPE = "application/json; charset=utf-8";

    private static final int CONNECT_TIMEOUT = 5;

    private static final int READ_TIMEOUT = 7;

    private static final String GET = "GET";

    private static final String POST = "POST";

    /**
     * 单例模式  获取类实例
     *
     * @return client
     */
    private static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpClient.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//                            .connectionPool(new ConnectionPool(300, 30, TimeUnit.SECONDS))
                            .build();
                }
            }
        }
        return client;
    }

    public static String doGet(String url, String callMethod) {

        try {
            long startTime = System.currentTimeMillis();
            addRequestLog(GET, callMethod, url, null, null);

            Request request = new Request.Builder().url(url).build();
            // 创建一个请求
            Response response = getInstance().newCall(request).execute();
            int httpCode = response.code();
            String result;
            ResponseBody body = response.body();
            if (body != null) {
                result = body.string();
                addResponseLog(httpCode, result, startTime);
            } else {
                response.close();
                throw new RuntimeException("exception in OkHttpUtil,response body is null");
            }
            return handleHttpResponse(httpCode, result);
        } catch (Exception ex) {
            handleHttpThrowable(ex, url);
            return StringUtils.EMPTY;
        }
    }


    public static String doPost(String url, String postBody) {
        Response response = null;
        try {
            MediaType createMediaType = MediaType.parse(DEFAULT_MEDIA_TYPE);
            Request request = new Request.Builder()
                    .headers(new Headers.Builder()
                            .add("Content-type", "application/json; charset=utf-8")
//                            .add("Connection", "Close")
                            .add("X-SW-ORIGIN", "web_console")
                            .build())
                    .url(url)
                    .post(RequestBody.create(createMediaType, postBody))
                    .build();

            response = getInstance().newCall(request).execute();

            ResponseBody body = response.body();
            return body.string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            Optional.ofNullable(response).ifPresent(Response::close);
        }
    }
        public static String doPost(String url, Map<String, String> parameterMap, String callMethod) {
        try {
            long startTime = System.currentTimeMillis();
            List<String> parameterList = new ArrayList<>();
            FormBody.Builder builder = new FormBody.Builder();
            if (parameterMap.size() > 0) {
                for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                    String parameterName = entry.getKey();
                    String value = entry.getValue();
                    builder.add(parameterName, value);
                    parameterList.add(parameterName + ":" + value);
                }
            }

            addRequestLog(POST, callMethod, url, null, Arrays.toString(parameterList.toArray()));

            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .headers(   new Headers.Builder()
                            .add("Content-type", "application/json; charset=utf-8")
                            .add("Connection", "Close")
                            .add("X-SW-ORIGIN", "web_console")
                            .build())
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = getInstance().newCall(request).execute();
            String result;
            int httpCode = response.code();
            ResponseBody body = response.body();
            if (body != null) {
                result = body.string();
                addResponseLog(httpCode, result, startTime);
            } else {
                response.close();
                throw new IOException("exception in OkHttpUtil,response body is null");
            }
            return handleHttpResponse(httpCode, result);

        } catch (Exception ex) {
            handleHttpThrowable(ex, url);
            return StringUtils.EMPTY;
        }
    }


    private static void addRequestLog(String method, String callMethod, String url, String body, String formParam) {
        logger.info("===========================request begin================================================");
        logger.info("URI          : {}", url);
        logger.info("Method       : {}", method);
        if (StringUtils.isNotBlank(body)) {
            logger.info("Request body : {}", body);
        }
        if (StringUtils.isNotBlank(formParam)) {
            logger.info("Request param: {}", formParam);
        }
        logger.info("---------------------------request end--------------------------------------------------");
    }

    private static void addResponseLog(int httpCode, String result, long startTime) {
        long endTime = System.currentTimeMillis();
        logger.info("Status       : {}", httpCode);
        logger.info("Response     : {}", result);
        logger.info("Time         : {} ms", endTime - startTime);
        logger.info("===========================response end================================================");
    }

    private static String handleHttpResponse(int httpCode, String result) {
        if (httpCode == HttpStatus.OK.value()) {
            return result;
        }
        throw new RuntimeException(String.valueOf(httpCode));
    }

    private static void handleHttpThrowable(Exception ex, String url)  {
        logger.error("OkHttp error url: " + url, ex);
        if (ex instanceof SocketTimeoutException) {
            throw new RuntimeException("request time out of OkHttp when do url:" + url);
        }
        throw new RuntimeException(ex);
    }






}
