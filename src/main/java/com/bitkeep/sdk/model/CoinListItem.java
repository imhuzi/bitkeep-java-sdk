package com.bitkeep.sdk.model;

import lombok.Data;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/23
 */
@Data
public class CoinListItem {
    private String name;
    private String coin;
    /**
     * //  货币图标 url
     */
    private String icon;
    /**
     * // 该用户持有当前币种的数量
     */
    private Double amount;
    /**
     * 该用户持有当前币种的法币价格（根据请求header头，货币币种，得到法币价格）
     */
    private Double price;
    /**
     * 当前行情
     */
    private Double ticker;
    /**
     * 涨跌幅
     */
    private Double margin;
    /**
     * 该用户的区块地址
     */
    private String address;
}
