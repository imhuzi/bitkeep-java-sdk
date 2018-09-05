package com.bitkeep.sdk.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TransferHistoryList  extends NoneData{
    private Integer total;
    private ArrayList<TransferHistoryItem> items;
}
