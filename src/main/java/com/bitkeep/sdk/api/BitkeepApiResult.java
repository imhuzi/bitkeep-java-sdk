/**
 *
 */
package com.bitkeep.sdk.api;


import com.bitkeep.sdk.constant.BitKeepConstants;
import com.bitkeep.sdk.constant.ResultStatus;
import com.bitkeep.sdk.model.Result;
import com.bitkeep.sdk.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.lang.reflect.Type;
import java.util.*;

/**
 * YunpianApi 结果处理
 *
 * @author dzh
 * @date Nov 25, 2016 2:48:27 PM
 * @since 1.2.0
 */
public interface BitkeepApiResult {

    /**
     * 获取最终的结果
     *
     * @param rsp 响应结果
     * @param h   结果处理器
     * @param r   返回结果，若r=null则自动创建
     * @return
     */
    <R, T> Result<T> result(String rsp, ResultHandler<R, T> h, Result<T> r);

    /**
     * @param <R> HttpResponse的转换类型
     * @param <T> {@code Result}的data类型
     * @author dzh
     * @date Nov 25, 2016 5:36:59 PM
     * @since 1.2.0
     */
    public static interface ResultHandler<R, T> {

        /**
         * 将响应输入流转换成R类型
         *
         * @param rsp 响应输入流
         * @return
         */
        R response(String rsp) throws Exception;

        /**
         * 根据rsp获取响应码
         *
         * @param rsp
         * @return
         */
        Integer status(R rsp);

        /**
         * 处理成功响应
         *
         * @param code 0
         * @param rsp
         * @param r
         * @return
         */
        Result<T> succ(Integer code, R rsp, Result<T> r);

        /**
         * 处理失败响应
         *
         * @param code
         * @param rsp
         * @param r
         * @return r
         */
        Result<T> fail(Integer code, R rsp, Result<T> r);

        /**
         * 处理异常
         *
         * @param e
         * @param r
         * @return
         */
        Result<T> catchExceptoin(Throwable e, Result<T> r);

        TypeReference getTypeReference();
    }

    public static abstract class AbstractResultHandler<R, T> implements ResultHandler<R, T> {
        @Override
        public Result<T> catchExceptoin(Throwable e, Result<T> r) {
            if (r == null) {
                r = new Result<>();
            }
            return r.setStatus(ResultStatus.UNKNOWN_EXCEPTION).setMsg(ResultStatus.getErrorMsg(ResultStatus.UNKNOWN_EXCEPTION)).setThrowable(e);
        }
    }

    /**
     * 处理返回json对象(非数组)
     *
     * @author dzh
     * @date Nov 25, 2016 8:31:07 PM
     * @since 1.2.0
     */
    public static abstract class MapResultHandler<T> extends AbstractResultHandler<Map<String, String>, T> implements BitKeepConstants {

        @Override
        public Map<String, String> response(String rsp) throws Exception {
            if (rsp == null) {
                return Collections.emptyMap();
            }
            return JsonUtil.toObject(rsp, HashMap.class);
        }

        @Override
        public Result<T> succ(Integer code, Map<String, String> rsp, Result<T> r) {
            if (r == null) {
                r = new Result<>();
            }
            r.setStatus(code).setMsg(rsp.containsKey(MSG) ? rsp.get(MSG) : ResultStatus.getErrorMsg(code));
            T data = data(rsp);
            return r.setData(data);
        }

        /**
         * 成功时返回对象
         *
         * @param rsp
         * @return 对象数据
         */
        public abstract T data(Map<String, String> rsp);

        /**
         * 错误流程 v1和v2返回格式一致
         */
        @Override
        public Result<T> fail(Integer code, Map<String, String> rsp, Result<T> r) {
            if (r == null) {
                r = new Result<>();
            }
            return r.setStatus(code).setMsg(rsp.containsKey(MSG) ? rsp.get(MSG) : ResultStatus.getErrorMsg(code));
        }

    }

    /**
     * 处理返回json数组的情况,在传回非json时，用map方式解析到rspMap。设计不好，最好统一用map格式
     *
     * @author dzh
     * @date Nov 25, 2016 6:05:18 PM
     * @since 1.2.0
     */
    public static abstract class ListResultHandler<R, T> extends AbstractResultHandler<List<R>, T> implements BitKeepConstants {

        /**
         * list解析错误时，按map方式解析
         */
        protected Map<String, String> rspMap;

        @Override
        public List<R> response(String rsp) throws Exception {
            if (rsp == null) {
                return Collections.emptyList();
            }
            if (rsp.startsWith("[")) {
                return JsonUtil.toObject(rsp, rspType());
            } else {
                rspMap = JsonUtil.toObject(rsp, HashMap.class);
                return Collections.emptyList();
            }
        }

        abstract Type rspType();

        @Override
        public Result<T> succ(Integer code, List<R> rsp, Result<T> r) {
            if (r == null) {
                r = new Result<>();
            }
            r.setStatus(code).setMsg(ResultStatus.getErrorMsg(code));
            T data = data(rsp);
            return r.setData(data);
        }

        /**
         * 成功时返回对象
         *
         * @param rsp
         * @return 对象数据
         */
        public abstract T data(List<R> rsp);

        /**
         * 错误流程 v1和v2返回格式一致
         */
        @Override
        public Result<T> fail(Integer code, List<R> rsp, Result<T> r) {
            if (r == null) {
                r = new Result<>();
            }
            Map<String, String> map = rspMap == null ? Collections.<String, String>emptyMap() : rspMap;
            return r.setStatus(code).setMsg(map.containsKey(MSG) ? map.get(MSG) : ResultStatus.getErrorMsg(code));
        }

    }

    public static abstract class SimpleListResultHandler<T> extends ListResultHandler<T, List<T>> implements BitKeepConstants {
    }

    public static abstract class StdResultHandler<T> extends AbstractResultHandler<Result<T>, T> implements BitKeepConstants {

        @Override
        public Result<T> response(String rsp) throws Exception {
            return JsonUtil.<Result<T>>toObject(rsp, getTypeReference());
        }

        @Override
        public Integer status(Result<T> rsp) {
            return rsp.getStatus();
        }

        @Override
        public Result<T> succ(Integer code, Result<T> rsp, Result<T> r) {
            return rsp;
        }

        @Override
        public Result<T> fail(Integer code, Result<T> rsp, Result<T> r) {
            return rsp;
        }

        @Override
        public abstract TypeReference getTypeReference();

    }
}
