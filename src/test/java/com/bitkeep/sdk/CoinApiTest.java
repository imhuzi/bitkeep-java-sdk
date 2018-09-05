package com.bitkeep.sdk;

import com.bitkeep.sdk.model.CoinSearchResultItem;
import com.bitkeep.sdk.model.Result;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/22
 */
public class CoinApiTest {
    BitKeepClient client = new BitKeepClient("", "").init();

    @Test
    public void testSearch() {
        Result<ArrayList<CoinSearchResultItem>> result = client.coin().search("123456", "B", null);
        System.out.println(result.getData());
    }
}
