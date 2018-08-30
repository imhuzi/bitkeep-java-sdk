package com.bitkeep.sdk.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TransferHistoryList {
    private Integer total;
    private ArrayList<TransferHistoryItem> items;
}
