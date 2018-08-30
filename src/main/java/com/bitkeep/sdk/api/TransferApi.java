package com.bitkeep.sdk.api;

import com.bitkeep.sdk.BitKeepClient;
import com.bitkeep.sdk.model.CoinDetail;
import com.bitkeep.sdk.model.HeadersParam;
import com.bitkeep.sdk.model.NoneData;
import com.bitkeep.sdk.model.Result;
import com.bitkeep.sdk.model.TransferFeeList;
import com.bitkeep.sdk.model.TransferHistoryList;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;

/**
 * 交易相关接口
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class TransferApi extends BitkeepApi {

    public static final String NAME = "transfer";

    /**
     * Api名称,如TransferApi的name就是https://open.bitkeep.com/transfer/history里的transfer
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
     * ###4.1.1 创建转账
     * <p>
     * 地址对地址转账
     * <p>
     * 接口地址
     * <p>
     * /transfer/create
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @param from
     * @param to
     * @param amount
     * @param feeCoin
     * @param feeAmount
     * @param transferId
     * @return
     */
    public Result<NoneData> create(String appUserId, String coinName, String currecyType, String from, String to, String amount, String feeCoin, String feeAmount, String transferId) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        data.put("from", from);
        data.put("to", to);
        data.put("amount", amount);
        data.put("feeCoin", feeCoin);
        data.put("feeAmount", feeAmount);
        data.put("transferId", transferId);
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
            return path("create").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###4.1.2 创建转账
     * <p>
     * 第三方应用用户Id对地址转账
     * <p>
     * 接口地址
     * <p>
     * /transfer/createWithUserId
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @param to
     * @param amount
     * @param feeCoin
     * @param feeAmount
     * @param transferId
     * @return
     */
    public Result<NoneData> createWithUserId(String appUserId, String coinName, String currecyType, String to, String amount, String feeCoin, String feeAmount, String transferId) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        data.put("fromUserId", appUserId);
        data.put("to", to);
        data.put("amount", amount);
        data.put("feeCoin", feeCoin);
        data.put("feeAmount", feeAmount);
        data.put("transferId", transferId);
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
            return path("createWithUserId").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###4.1.3 创建转账
     * <p>
     * 第三方应用，用户Id对用户Id转账
     * <p>
     * 接口地址
     * <p>
     * /transfer/createWithUsers
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @param toUserId
     * @param amount
     * @param feeCoin
     * @param feeAmount
     * @param transferId
     * @return
     */
    public Result<NoneData> createWithUsers(String appUserId, String coinName, String currecyType, String toUserId, String amount, String feeCoin, String feeAmount, String transferId) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        data.put("fromUserId", appUserId);
        data.put("toUserId", toUserId);
        data.put("amount", amount);
        data.put("feeCoin", feeCoin);
        data.put("feeAmount", feeAmount);
        data.put("transferId", transferId);
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
            return path("createWithUsers").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###4.2 转账手续费接口
     * <p>
     * 手续费查询该接口，根据转出和转入地址，货币名称和转账数量返回手续费
     * <p>
     * 接口地址
     * <p>
     * /transfer/fee
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @param from
     * @param to
     * @param amount
     * @param amount
     * @param type
     * @return
     */
    public Result<TransferFeeList> fee(String appUserId, String coinName, String currecyType, String from, String to, String amount, String type) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        data.put("from", from);
        data.put("to", to);
        data.put("amount", amount);
        data.put("type", type);
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<TransferFeeList> r = new Result<>();
        StdResultHandler<TransferFeeList> handler = new StdResultHandler<TransferFeeList>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<TransferFeeList>>() { };
            }
        };
        try {
            return path("fee").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * ###4.3 交易历史记录
     * <p>
     * 交易历史记录
     * <p>
     * 接口地址
     * <p>
     * /transfer/history
     *
     * @param appUserId
     * @param coinName
     * @param currecyType
     * @param page
     * @param size
     * @return
     */
    public Result<TransferHistoryList> history(String appUserId, String coinName, String currecyType, Integer page, Integer size) {
        HashMap<String, String> data = new HashMap<>();
        data.put("coin", coinName);
        data.put("page", page.toString());
        data.put("size", size.toString());
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType == null ? CURRENCY_TYPE_CNY : currecyType);
        Result<TransferHistoryList> r = new Result<>();
        StdResultHandler<TransferHistoryList> handler = new StdResultHandler<TransferHistoryList>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<TransferHistoryList>>() { };
            }
        };
        try {
            return path("history").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }
}
