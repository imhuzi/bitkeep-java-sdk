package com.bitkeep.sdk;

/**
 * Bitkeep 异常
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class BitKeepException extends Exception {
    private static final long serialVersionUID = 1L;


    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int status;


    public BitKeepException(int status, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.status = status;
        this.msg = String.format(msgFormat, args);
    }


    public BitKeepException() {
        super();
    }

    public BitKeepException(String message, Throwable cause) {
        super(message, cause);
    }

    public BitKeepException(Throwable cause) {
        super(cause);
    }

    public BitKeepException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

}
