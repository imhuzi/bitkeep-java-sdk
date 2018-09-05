package com.bitkeep.sdk.model;

import lombok.Data;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/22
 */
@Data
public class CoinDetail extends NoneData{
    private String name;
    private String coin;
    private Double amount;
    private String address;
}
