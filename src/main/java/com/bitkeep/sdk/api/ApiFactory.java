/**
 *
 */
package com.bitkeep.sdk.api;

import com.bitkeep.sdk.BitKeepClient;

/**
 * 接口工厂类
 *
 * @author dzh
 * @date Nov 23, 2016 1:29:44 PM
 * @since 1.2.0
 */
public class ApiFactory {

    private BitKeepClient clnt;

    public ApiFactory(BitKeepClient clnt) {
        this.clnt = clnt;
    }

    public <T extends BitkeepApi> T api(String name) {
        T t = null;
        switch (name) {
            case AccountApi.NAME:
                t = (T) new AccountApi();
                break;
            case CoinApi.NAME:
                t = (T) new CoinApi();
                break;
            case TransferApi.NAME:
                t = (T) new TransferApi();
                break;
        }
        if (t != null) {
            t.init(clnt);
        }
        return t;
    }

}
