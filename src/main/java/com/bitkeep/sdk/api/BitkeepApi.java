package com.bitkeep.sdk.api;

import com.bitkeep.sdk.BitKeepClient;
import com.bitkeep.sdk.BitKeepException;
import com.bitkeep.sdk.constant.BitKeepConstants;
import com.bitkeep.sdk.constant.ResultStatus;
import com.bitkeep.sdk.model.HeadersParam;
import com.bitkeep.sdk.model.Result;
import com.bitkeep.sdk.utils.ApiUtil;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public abstract class BitkeepApi implements BitKeepConstants, BitkeepApiResult {
    static final Logger LOG = LoggerFactory.getLogger(BitkeepApi.class);

    private BitKeepClient client;

    private String host;

    private String version;

    private String path;

    private String charset;

    public BitKeepClient client() {
        return client;
    }


    /**
     * @param clnt
     */
    public synchronized void init(BitKeepClient clnt) {
        if (clnt == null) {
            return;
        }
        this.client = clnt;
        host(clnt.getConf().getConf(BK_API_HOST, "https://open.bitkeep.com"))
                .version(API_VERSION_01)
                .charset(clnt.getConf().getConf(HTTP_CHARSET, HTTP_CHARSET_DEFAULT));
    }

    public String charset() {
        return this.charset;
    }

    public BitkeepApi charset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * @return 请求全路径
     */
    public String uri() {
        StringBuilder buf = new StringBuilder(host().length() + name().length() + path().length() + 3);
        buf.append(host()).append("/").append(name()).append("/").append(path());
        return buf.toString();
    }

    /**
     * Api名称,如UserApi的name就是https://sms.yunpian.com/v2/user/get.json里的user
     *
     * @return
     */
    abstract public String name();

    /**
     * @return 请求域名，如https://sms.yunpian.com
     */
    public String host() {
        return host;
    }

    public BitkeepApi host(String host) {
        this.host = host;
        return this;
    }

    /**
     * @return 接口版本，如v2
     */
    public String version() {
        if (version == null || "".equals(version)) {
            version(this.client.getConf().getConf(BitKeepConstants.BK_API_VERSION, "0.1"));
        }
        return version;
    }

    public BitkeepApi version(String v) {
        this.version = v;
        return this;
    }

    /**
     * @return 请求路径，如user/get.json
     */
    public String path() {
        return path;
    }

    public BitkeepApi path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BitkeepApi) {
            return ((BitkeepApi) obj).name().equals(name());
        }
        return false;
    }

    @Override
    public String toString() {
        return "BitKeepApi-" + name();
    }

    public String post(String uri, Map<String, String> data) throws Exception {
        return post(uri, data, new HeadersParam());
    }

    public String post(String uri, Map<String, String> data, HeadersParam headersParam) throws Exception {
        Response resPonse = client().post(uri, data, headersParam);
        LOG.info("BitKeep SDK response code: " + resPonse.code());
        if (resPonse.code() != 200) {
            throw new BitKeepException(ResultStatus.UNKNOWN_EXCEPTION, ResultStatus.getErrorMsg(ResultStatus.UNKNOWN_EXCEPTION));
        }
        ResponseBody responseBody = resPonse.body();
        String rbStr = responseBody.string();
        LOG.info("BitKeep SDK  post response body:{}", rbStr);
        return rbStr;
    }

    public <R, T> Result<T> post(Map<String, String> data, ResultHandler<R, T> h) {
        return post(uri(), data, new HeadersParam(), h, new Result<T>());
    }

    public <R, T> Result<T> post(Map<String, String> data, ResultHandler<R, T> h, Result<T> r) {
        return post(uri(), data, new HeadersParam(), h, r);
    }

    public <R, T> Result<T> post(Map<String, String> data, HeadersParam headersParam, ResultHandler<R, T> h, Result<T> r) {
        return post(uri(), data, headersParam, h, r);
    }

    public <R, T> Result<T> post(String uri, Map<String, String> data, HeadersParam headersParam, ResultHandler<R, T> h, Result<T> r) {
        LOG.debug("post uri-{} data-{}", uri, data);
        try {
            headersParam.setVersion(version());
            headersParam.setAppId(client().getConf().getClientId());
            String rsp = post(uri, data, headersParam);
            return result(rsp, h, r);
        } catch (Exception e) {
            return h.catchExceptoin(e, r);
        }
    }

    @Override
    public <R, T> Result<T> result(String rspContent, ResultHandler<R, T> h, Result<T> r) {
        try {
            R rsp = h.response(rspContent);
            Integer status = h.status(rsp);
            return status == ResultStatus.OK ? h.succ(status, rsp, r) : h.fail(status, rsp, r);
        } catch (Exception e) {
            return h.catchExceptoin(e, r);
        }
    }

    /**
     * @param text
     * @return
     * @since 1.2.2
     */
    public String urlEncode(String text) {
        return ApiUtil.urlEncode(text, charset());
    }


}
