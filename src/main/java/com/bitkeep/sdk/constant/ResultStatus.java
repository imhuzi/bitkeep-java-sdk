package com.bitkeep.sdk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public final class ResultStatus {

    public static String getErrorMsg(int code) {
        return codeMsgMap.get(code);
    }


    /**
     * 正确
     */
    public static final int OK = 0;

    /**
     * 未知异常
     */
    public static final int UNKNOWN_EXCEPTION = -50;

    /**
     * 接口404异常
     */
    public static final int HTTP_EXCEPTION_404 = 404;

    /**
     * 接口 500 异常
     */
    public static final int HTTP_EXCEPTION_500 = 500;


    /**
     * 错误码和对应的错误消息
     */
    public static final Map<Integer, String> codeMsgMap = new HashMap<Integer, String>();


    static {
        codeMsgMap.put(OK, Messages.getString("Status.ok")); //$NON-NLS-1$

        /****************** 调用API时间发生的错误，需要开发者自己处理 ****************************/

        /*************** 系统相关系统 需要技术支持 *******************/
        codeMsgMap.put(UNKNOWN_EXCEPTION, Messages.getString("Status.unknown_exception")); //$NON-NLS-1$
    }

}
