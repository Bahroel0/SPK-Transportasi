package com.example.bahroel.spk.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WarehouseDestination extends RealmObject{

    @PrimaryKey
    private long id;

    private String DestinationName;

    private String DestinationAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getDestinationAmount() {
        return DestinationAmount;
    }

    public void setDestinationAmount(String destinationAmount) {
        DestinationAmount = destinationAmount;
    }
}
