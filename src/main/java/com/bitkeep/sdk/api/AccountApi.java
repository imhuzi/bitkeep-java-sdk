package com.bitkeep.sdk.api;

import com.bitkeep.sdk.BitKeepClient;
import com.bitkeep.sdk.model.Account;
import com.bitkeep.sdk.model.HeadersParam;
import com.bitkeep.sdk.model.NoneData;
import com.bitkeep.sdk.model.Result;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;

/**
 * 账户相关接口
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class AccountApi extends BitkeepApi {

    public static final String NAME = "account";

    /**
     * Api名称,如AccountApi的name就是https://open.bitkeep.com/account/init里的user
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
     * 初始化账户
     *
     * @param appUserId
     * @return
     */
    public Result<NoneData> init(String appUserId) {
        return init(appUserId, CURRENCY_TYPE_CNY);
    }

    /**
     * 初始化账户
     *
     * @param appUserId
     * @param currecyType
     * @return
     */
    public Result<NoneData> init(String appUserId, String currecyType) {
        HashMap<String, String> data = new HashMap<>();
        data.put("appUserId", appUserId);
        data.put("appId", client().getConf().getClientId());
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currecyType);
        Result<NoneData> r = new Result<>();
        StdResultHandler<NoneData> handler = new StdResultHandler<NoneData>() {
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<NoneData>>() {
                };
            }
        };
        try {
            return path("init").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

    /**
     * 查询账户余额
     *
     * @param appUserId
     * @param currencyType
     * @return
     */
    public Result<Account> balance(String appUserId, String currencyType) {
        HashMap<String, String> data = new HashMap<>();
        HeadersParam headersParam = new HeadersParam();
        headersParam.setAppUserId(appUserId);
        headersParam.setCurrecy(currencyType);
        Result<Account> r = new Result<>();
        StdResultHandler<Account> handler = new StdResultHandler<Account>(){
            @Override
            public TypeReference getTypeReference() {
                return new TypeReference<Result<Account>>() {
                };
            }
        };
        try {
            return path("balance").post(data, headersParam, handler, r);
        } catch (Exception e) {
            return handler.catchExceptoin(e, r);
        }
    }

}
