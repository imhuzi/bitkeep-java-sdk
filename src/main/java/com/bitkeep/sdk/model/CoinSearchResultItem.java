package com.bitkeep.sdk.model;

import lombok.Data;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/6
 */
@Data
public class CoinSearchResultItem  extends NoneData{
    private String coin;
    private String name;
    private String cnName;
    private String enName;
    private Integer fixed;
    private String enable;
    private String icon;
}
