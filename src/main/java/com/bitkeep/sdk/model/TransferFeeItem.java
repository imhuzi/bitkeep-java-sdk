package com.bitkeep.sdk.model;

import lombok.Data;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/23
 */
@Data
public class TransferFeeItem  extends NoneData{
    /**
     * 如果fee为0表示免手续费
     */
    private Integer fee;
    private String coin;
    /**
     * valuation为手续费的法币价值，此处valuation根据header头currency设置返回相应的法币价值
     */
    private Integer valuation;
}
