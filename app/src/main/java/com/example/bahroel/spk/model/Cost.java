package com.example.bahroel.spk.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cost extends RealmObject {
    @PrimaryKey
    private long id;
    private String SourceName;
    private String DestinationName;
    private int Cost;

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

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
