/**
 * 
 */
package com.bitkeep.sdk.model;


import com.bitkeep.sdk.constant.ResultStatus;

/**
 * Api返回格式
 * 
 * @author dzh
 * @date Nov 24, 2016 7:29:08 PM
 * @since 1.2.0
 */
public class Result<T> {

    private Integer status = ResultStatus.OK;
    private String msg;

    private Throwable e;

    private T data;

    public T getData() {
        return data;
    }


    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Result<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Throwable getThrowable() {
        return e;
    }

    public Result<T> setThrowable(Throwable e) {
        this.e = e;
        return this;
    }

    public boolean isSuccess() {
        return status == ResultStatus.OK;
    }

//    @Override
//    public String toString() {
//        return JsonUtil.toJson(this);
//    }

}
