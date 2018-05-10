package com.example.bahroel.spk.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WarehouseSource extends RealmObject {

    @PrimaryKey
    private long id;

    private String SourceName;

    private String SourceAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getSourceAmount() {
        return SourceAmount;
    }

    public void setSourceAmount(String sourceAmount) {
        SourceAmount = sourceAmount;
    }
}
