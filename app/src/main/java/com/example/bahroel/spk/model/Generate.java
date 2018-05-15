package com.example.bahroel.spk.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Generate extends RealmObject {
    @PrimaryKey
    private long id;

    private String DstName;

    private String SrcName;

    private int CostValue;

    private String Amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDstName() {
        return DstName;
    }

    public void setDstName(String dstName) {
        DstName = dstName;
    }

    public String getSrcName() {
        return SrcName;
    }

    public void setSrcName(String srcName) {
        SrcName = srcName;
    }

    public int getCostValue() {
        return CostValue;
    }

    public void setCostValue(int costValue) {
        CostValue = costValue;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
