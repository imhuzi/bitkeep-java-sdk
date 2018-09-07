package com.bitkeep.sdk;

import static com.bitkeep.sdk.constant.BitKeepConstants.CURRENCY_TYPE_CNY;
import static com.bitkeep.sdk.constant.BitKeepConstants.TRANSFER_TYPE_ADDRESS_TO_ADDRESS;
import com.bitkeep.sdk.model.Account;
import com.bitkeep.sdk.model.CoinDetail;
import com.bitkeep.sdk.model.CoinListReulst;
import com.bitkeep.sdk.model.CoinSearchResultItem;
import com.bitkeep.sdk.model.NoneData;
import com.bitkeep.sdk.model.Result;
import com.bitkeep.sdk.model.TransferFeeList;
import com.bitkeep.sdk.model.TransferHistoryList;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/9
 */
public class AccountApiTest {

    BitKeepClient client = new BitKeepClient("ucmFg6lU7MX15dtHwZaGAhnIEjQfKeky", "QMdqcF4Eh3EXsVxYab8mMaH8CV606gYNfbSz9jKlicKLDPpUgt97WArftTmI1d0H").init();

    @Test
    public void testInit() {
//        Result<NoneData> result = client.account().init("123456");
//        System.out.println(result.getData());
//        Result<Account> balanceResult = client.account().balance("123456", CURRENCY_TYPE_CNY);
//        System.out.println(balanceResult.getData().getAmount());
//        Result<CoinDetail> addResult = client.coin().add("123456", "ETH", CURRENCY_TYPE_CNY);
//        System.out.println(addResult.getData());
//        Result<NoneData> removeResult = client.coin().remove("123456", "ETH", CURRENCY_TYPE_CNY); // NG
//        System.out.println(removeResult.getData());
//        Result<ArrayList<CoinSearchResultItem>> searchResult = client.coin().search("123456", "b", CURRENCY_TYPE_CNY);
//        System.out.println(searchResult.getData());
//        Result<CoinListReulst> listResult = client.coin().list("123456", 1, 10, CURRENCY_TYPE_CNY);
//        System.out.println(listResult.getData().getTotal());

        Result<CoinDetail> detailResult = client.coin().detail("124556", "ETH", CURRENCY_TYPE_CNY);
        CoinDetail reulst = detailResult.getData();
        System.out.println(reulst);
//
//        Result<TransferFeeList> feeResult = client.transfer().fee("123456", "ETH", CURRENCY_TYPE_CNY, "0x644f906E0e9867AC623926391a23B227609a2683", "0x3dA2E46cd0656ae45176bBD1b4823db16269c795", "10", TRANSFER_TYPE_ADDRESS_TO_ADDRESS);
//        System.out.println(feeResult.getData());
//        Result<NoneData> createResult = client.transfer().create("123456", "ETH", CURRENCY_TYPE_CNY, "0x644f906E0e9867AC623926391a23B227609a2683", "0x3dA2E46cd0656ae45176bBD1b4823db16269c795", "10", "BKB", "0", "888888890");
//        System.out.println(createResult.getData());
//        Result<NoneData> createWithUserIdResult = client.transfer().createWithUserId("123456", "ETH", CURRENCY_TYPE_CNY, "0x3dA2E46cd0656ae45176bBD1b4823db16269c795", "10", "BKB", "0", "888888891");
//        System.out.println(createWithUserIdResult.getData());
//        Result<NoneData> createWithUsersResult = client.transfer().createWithUsers("123456", "ETH", CURRENCY_TYPE_CNY, "123457", "10", "BKB", "0", "888888892");
//        System.out.println(createWithUsersResult.getData());
//        Result<TransferHistoryList> historyResult = client.transfer().history("123456", "ETH", CURRENCY_TYPE_CNY, 1, 10);
//        System.out.println(historyResult.getData());
    }

}



