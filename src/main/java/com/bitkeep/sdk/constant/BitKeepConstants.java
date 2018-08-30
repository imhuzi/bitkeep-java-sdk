package com.bitkeep.sdk.constant;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public interface BitKeepConstants {

    public enum SignType {
        /**
         * MD5签名方式
         */
        MD5,
        /**
         * sha256
         */
        HMACSHA256,
        SHA256
    }


    String SIGN_TYPE_HMACSHA256 = "HMAC-SHA256";
    String SIGN_TYPE_MD5 = "MD5";

    String FIELD_SIGN = "sign";
    String FIELD_SIGN_TYPE = "sign_type";
    String FIELD_NONCE = "nonce";
    String FIELD_RESPONSE_DATA= "data";


    /**************************** bitkeep.properties ***********************/
    String BK_FILE = "bk.file";
    String BK_CLIENT_ID = "bk.clientId";
    String BK_SECRET = "bk.secret";
    String BK_API_HOST = "bk.api.host";
    String BK_API_VERSION = "bk.api.version";


    String HTTP_CHARSET_DEFAULT = "utf-8";

    String API_VERSION_01 = "0.1";


    // 返回值字段
    String STATUS = "status";
    String MSG = "msg";
    String DATA = "data";


    /**************************** http *************************************/
    String HTTP_CONN_TIMEOUT = "http.conn.timeout";
    String HTTP_SO_TIMEOUT = "http.so.timeout";
    String HTTP_CHARSET = "http.charset";
    String HTTP_CONN_MAXPERROUTE = "http.conn.maxperroute";
    String HTTP_CONN_MAXTOTAL = "http.conn.maxtotal";
    String HTTP_SSL_KEYSTORE = "http.ssl.keystore";
    String HTTP_SSL_PASSWD = "http.ssl.passwd";


    String SEPERATOR_COMMA = ",";


    /**************************** headers *************************************/
    String HEADERS_FIELD_VERSION = "apiVersion";
    String HEADERS_FIELD_APP_ID = "appId";
    String HEADERS_FIELD_APP_USER_ID = "appUserId";
    String HEADERS_FIELD_CONTENT_TYPE = "content_type";
    String HEADERS_FIELD_PLATFORM = "platform";
    String HEADERS_FIELD_LANGUAGE = "language";
    String HEADERS_FIELD_CURRENCY = "currency";
    String CURRENCY_TYPE_CNY = "cny";
    String CURRENCY_TYPE_USD = "usd";

    /**************************** 转账类型，a2a:表示地址对地址 u2a:用户id对地址 u2u:用户id对用户id *************************************/
    String TRANSFER_TYPE_ADDRESS_TO_ADDRESS = "a2a";
    String TRANSFER_TYPE_USERID_TO_ADDRESS = "u2a";
    String TRANSFER_TYPE_USERID_TO_USERID = "u2u";

}
