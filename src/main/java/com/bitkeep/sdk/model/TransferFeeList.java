package com.bitkeep.sdk.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TransferFeeList {
    /**
     * 如果items为[] 表示无法转账到链上，可在应用内部交易
     */
    private ArrayList<TransferFeeItem> items;

}
