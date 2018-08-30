package com.bitkeep.sdk.model;

import lombok.Data;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/23
 */
@Data
public class TransferHistoryItem {
    /**
     * out:转账 in:充值
     */
    private String type;
    /**
     * 从哪个地址转账
     */
    private String from;
    /**
     * 转账到哪个地址
     */
    private String to;
    /**
     * 转账流水时间
     */
    private Long createdAt;
    /**
     * 转账金额
     */
    private String amount;
    /**
     * success 转账成功，failed 转账失败， pending 等待
     */
    /*private String status;*/
    /**
     * 转账ID
     */
    private String transferId;
}
