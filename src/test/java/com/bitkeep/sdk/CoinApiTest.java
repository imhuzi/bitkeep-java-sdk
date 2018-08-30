package com.bitkeep.sdk;

import com.bitkeep.sdk.model.Account;
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
    BitKeepClient client = new BitKeepClient("ucmFg16lU7MX15dtHwZaGAhnIEjQfKeky", "QMdqcF4Eh3EXsVxYab8mMaH8CV606gY4NfbSz9jKlicKLDPpUgt97WArftTmI1d0H").init();

    @Test
    public void testSearch() {
        Result<ArrayList<CoinSearchResultItem>> result = client.coin().search("123456", "B", null);
        System.out.println(result.getData());
    }
}
