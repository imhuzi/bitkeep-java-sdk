package com.bitkeep.sdk.model;

import com.bitkeep.sdk.constant.BitKeepConstants;

import java.util.HashMap;

/**
 * 基础参数, 基础参数会放入headers
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class HeadersParam {

    final HashMap<String,String> headers = new HashMap<>();

    /**
     * 接口版本
     */
    private String version;

    /**
     * 开放平台分配的appId (必须)
     */
    private String appId;
    /**
     * 应用自己的用户Id (必须)
     */
    private String appUserId;
    /**
     * application/json (必须)
     */
    private String contentType;
    /**
     * web (必须) 支持选项：[andorid, ios, web]
     */
    private String platform;
    /**
     * language: cn    (必须) 支持选项：[cn, en]
     */
    private String language;
    /**
     * cny   (必须) 支持选项：[cny, usd]
     */
    private String currecy;


    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getVersion() {
        return version;
    }

    public HeadersParam setVersion(String version) {
        this.version = version;
        headers.put(BitKeepConstants.HEADERS_FIELD_VERSION, version);
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public HeadersParam setAppId(String appId) {
        this.appId = appId;
        headers.put(BitKeepConstants.HEADERS_FIELD_APP_ID, appId);
        return this;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public HeadersParam setAppUserId(String appUserId) {
        this.appUserId = appUserId;
        headers.put(BitKeepConstants.HEADERS_FIELD_APP_USER_ID, appUserId);
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public HeadersParam setContentType(String contentType) {
        this.contentType = contentType;
        headers.put(BitKeepConstants.HEADERS_FIELD_CONTENT_TYPE, contentType);
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public HeadersParam setPlatform(String platform) {
        this.platform = platform;
        headers.put(BitKeepConstants.HEADERS_FIELD_PLATFORM, platform);
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public HeadersParam setLanguage(String language) {
        this.language = language;
        headers.put(BitKeepConstants.HEADERS_FIELD_LANGUAGE, language);
        return this;
    }

    public String getCurrecy() {
        return currecy;
    }

    public HeadersParam setCurrecy(String currecy) {
        this.currecy = currecy;
        headers.put(BitKeepConstants.HEADERS_FIELD_CURRENCY, currecy);
        return this;
    }
}
