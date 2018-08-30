package com.bitkeep.sdk.api;

import com.bitkeep.sdk.BitKeepClient;
import com.bitkeep.sdk.model.CoinDetail;
import com.bitkeep.sdk.model.CoinListReulst;
import com.bitkeep.sdk.model.CoinSearchResultItem;
import com.bitkeep.sdk.model.HeadersParam;
import com.bitkeep.sdk.model.NoneData;
import com.bitkeep.sdk.model.Result;
import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 币相关接口
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class CoinApi extends BitkeepApi {

    public static final String NAME = "coin";

    /**
     * Api名称,如CoinApi的name就是https://open.bitkeep.com/coin/search里的coin
     *
     * @return
     */
    @Override
    public String name() {
        return NAME;
    }


    @Override
    public void init(BitKeepClient clnt) {
        super.init(clnt);
    }

    /**
     * ##3 货币
     * ###3.1添加货币
     * <p>
     * 接口地址
     * <p>
     * /coin/add
     *
     * @param appUserId
     * @param coinName
     * @return
     */
    public Result<CoinDetail> add(String appUserId, String coinName, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<CoinDetail> r = new Result<>();
        StdResultHandler<CoinDetail> handler = new StdResultHandler<CoinDetail>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<CoinDetail>>() { };
            }
        };
        try {
            return path("add").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###3.2删除货币
     * <p>
     * 接口地址
     * <p>
     * /coin/remove
     *
     * @param appUserId
     * @param coinName
     * @return
     */
    public Result<NoneData> remove(String appUserId, String coinName, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<NoneData> r = new Result<>();
        StdResultHandler<NoneData> handler = new StdResultHandler<NoneData>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<NoneData>>() { };
            }
        };
        try {
            return path("remove").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###3.3搜索货币
     * <p>
     * 接口地址
     * <p>
     * /coin/search
     *
     * @param appUserId
     * @param searchKey
     * @param currecyType
     * @return
     */
    public Result<ArrayList<CoinSearchResultItem>> search(String appUserId, String searchKey, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", searchKey);
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<ArrayList<CoinSearchResultItem>> r = new Result<>();
        StdResultHandler<ArrayList<CoinSearchResultItem>> handler = new StdResultHandler<ArrayList<CoinSearchResultItem>>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<ArrayList<CoinSearchResultItem>>>() { };
            }
        };
        try {
            return path("search").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###3.4货币列表数据
     * <p>
     * 第三方开发者后台配置默认调用哪些货币，如没有系统会提供一组默认货币
     * <p>
     * 接口地址
     * <p>
     * /coin/list
     *
     * @param appUserId
     * @param page
     * @param size
     * @param currecyType
     * @return
     */
    public Result<CoinListReulst> list(String appUserId, Integer page, Integer size, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 30;
        }
        data.put("page", page.toString());
        data.put("size", size.toString());
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<CoinListReulst> r = new Result<>();
        StdResultHandler<CoinListReulst> handler = new StdResultHandler<CoinListReulst>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<CoinListReulst>>() { };
            }
        };
        try {
            return path("list").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###3.5 货币详情
     * <p>
     * 获取某个货币的详细信息，里面包含用户的钱包余额，地址
     * <p>
     * 接口地址
     * <p>
     * /coin/detail
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @return
     */
    public Result<CoinDetail> detail(String appUserId, String coinName, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<CoinDetail> r = new Result<>();
        StdResultHandler<CoinDetail> handler = new StdResultHandler<CoinDetail>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<CoinDetail>>() { };
            }
        };
        try {
            return path("detail").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }
}
