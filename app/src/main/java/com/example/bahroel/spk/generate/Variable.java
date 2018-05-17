package com.example.bahroel.spk.generate;

import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;

public class Variable {
    private WarehouseSource source;
    private WarehouseDestination desti;
    private Cost biaya;
    private int jumlah;

    public Variable() {
    }

    public Variable(WarehouseSource source, WarehouseDestination desti, Cost biaya, int jumlah) {
        this.source = source;
        this.desti = desti;
        this.biaya = biaya;
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public WarehouseSource getSource() {
        return source;
    }

    public void setSource(WarehouseSource source) {
        this.source = source;
    }

    public WarehouseDestination getDesti() {
        return desti;
    }

    public void setDesti(WarehouseDestination desti) {
        this.desti = desti;
    }

    public Cost getBiaya() {
        return biaya;
    }

    public void setBiaya(Cost biaya) {
        this.biaya = biaya;
    }
}
