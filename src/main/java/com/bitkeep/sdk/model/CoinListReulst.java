package com.bitkeep.sdk.model;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/6
 */
@Data
public class CoinListReulst {

    private Integer total;
    private ArrayList<CoinListItem> items;

}
