package com.bitkeep.sdk;

import com.bitkeep.sdk.api.*;
import com.bitkeep.sdk.model.HeadersParam;
import com.bitkeep.sdk.utils.JsonUtil;
import com.bitkeep.sdk.utils.SignatureUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.bitkeep.sdk.constant.BitKeepConstants.*;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class BitKeepClient {

    static final Logger LOG = LoggerFactory.getLogger(BitKeepClient.class);

    private OkHttpClient clnt;

    private BitkeepConf conf = new BitkeepConf();

    private ApiFactory api;

    /**
     * 构造器里的key作为默认值,方法请求时可以自定义
     */
    public BitKeepClient() {
        this(System.getProperty(BitkeepConf.BK_CLIENT_ID), System.getProperty(BitkeepConf.BK_SECRET), System.getProperty(BitkeepConf.BK_FILE));
    }

    public BitKeepClient(String clientId, String secretKey) {
        this(clientId, secretKey, System.getProperty(BitkeepConf.BK_FILE));
    }

    public BitKeepClient(String clientId, String secretKey, String file) {
        conf.with(clientId, secretKey);
        if (file != null) {
            conf.with(new File(System.getProperty(BitkeepConf.BK_FILE, file)));
        }
    }

    public BitKeepClient(String clientId, String secretKey, InputStream in) {
        conf.with(clientId, secretKey).with(in);
    }

    public BitKeepClient(String clientId, String secretKey, Properties props) {
        conf.with(clientId, secretKey).with(props);
    }

    public AccountApi account() {
        return api.<AccountApi>api(AccountApi.NAME);
    }

    public CoinApi coin() {
        return api.<CoinApi>api(CoinApi.NAME);
    }

    public TransferApi transfer() {
        return api.<TransferApi>api(TransferApi.NAME);
    }


    private static MediaType DefaultContentType;

    @PostConstruct
    public BitKeepClient init() {
        LOG.info("BitKeepClient is initing!");
        try {
            clnt = createOkHttpClient(conf.build());
            DefaultContentType = MediaType.parse("application/json; charset=utf-8");
            api = new ApiFactory(this);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
        return this;
    }

    public BitkeepConf getConf() {
        return conf;
    }

    private OkHttpClient createOkHttpClient(BitkeepConf conf) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    static Map<String, String> HEADERS = new HashMap<>(1, 1);

    static {
        HEADERS.put("Api-Lang", "java");
        HEADERS.put("User-Agent", "BitKeep Java Client");
        HEADERS.put(HEADERS_FIELD_PLATFORM, "web");
        HEADERS.put(HEADERS_FIELD_LANGUAGE, "cn");
//        HEADERS.put(HEADERS_FIELD_CONTENT_TYPE, DefaultContentType.toString());
    }

    public final Map<String, String> newParam(int size) {
        return size <= 0 ? Collections.<String, String>emptyMap() : new HashMap<String, String>(size + 1, 1);
    }

    public void closeResponse(Response rsp) {
        if (rsp != null) {
            rsp.close();
        }
    }

    public Response post(String url, Map<String, String> requetBodyParam, HeadersParam headers) throws Exception {
        return post(url, headers, requetBodyParam, DefaultContentType);
    }


    public Response post(String url, Map<String, String> requetBodyParam) throws Exception {
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppId(conf.getClientId());
        return post(url, headersParam, requetBodyParam, DefaultContentType);
    }


    public Response post(String url, HeadersParam headersParam, Map<String, String> requetBodyParam) throws Exception {
        return post(url, headersParam, requetBodyParam, DefaultContentType);
    }

    public Response post(String url, HeadersParam headersParam, Map<String, String> requetBodyParam, MediaType mediaType) throws Exception {
        //随机字符串
        requetBodyParam.put(FIELD_NONCE, System.currentTimeMillis() + "");
        //参数签名
        Map<String,String> sData = new HashMap<>();
        sData.putAll(requetBodyParam);
        String sign = SignatureUtil.generateSignature(sData, getConf().getSecretKey());
        requetBodyParam.put(FIELD_SIGN, sign.toLowerCase());

        String requestBodyJsonStr = JsonUtil.toJsonString(requetBodyParam);
        LOG.info("BitKeep SDK request data:{} ", requestBodyJsonStr);
        RequestBody body = RequestBody.create(mediaType, requestBodyJsonStr);

        headersParam.setContentType(DefaultContentType.toString());
        return post(url, headersParam.getHeaders(), body);
    }

    public Response post(String url, Map<String, String> headers, RequestBody body) throws IOException {
        HEADERS.putAll(headers);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(HEADERS))
                .post(body)
                .build();
        LOG.info("BitKeep SDK request headers:{}", request.headers());
        return clnt.newCall(request).execute();
    }


    @Override
    public String toString() {
        return conf.toString();
    }

}
