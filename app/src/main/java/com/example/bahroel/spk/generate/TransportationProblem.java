package com.example.bahroel.spk.generate;


import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;
import com.example.bahroel.spk.realm.RealmController;

import java.util.ArrayList;

import io.realm.RealmResults;

public class TransportationProblem {

    private RealmResults<WarehouseSource> warehouseSourceArrayList;
    private RealmResults<WarehouseDestination> warehouseDestinationArrayList;
    private RealmResults<Cost> costArrayList;

    private ArrayList<Variable> feasible= new ArrayList<>();

    public TransportationProblem(RealmResults<WarehouseSource> warehouseSourceArrayList, RealmResults<WarehouseDestination> warehouseDestinationArrayList, RealmResults<Cost> costArrayList) {
        this.warehouseSourceArrayList = warehouseSourceArrayList;
        this.warehouseDestinationArrayList = warehouseDestinationArrayList;
        this.costArrayList = costArrayList;

        for(int i=0; i < (getWarehouseSourceArrayList().size()*getWarehouseDestinationArrayList().size()-1); i++)
            feasible.add(new Variable(warehouseSourceArrayList.get(0),warehouseDestinationArrayList.get(0),costArrayList.get(0),0));
    }


    public RealmResults<WarehouseSource> getWarehouseSourceArrayList() {
        return warehouseSourceArrayList;
    }

    public void setWarehouseSourceArrayList(RealmResults<WarehouseSource> warehouseSourceArrayList) {
        this.warehouseSourceArrayList = warehouseSourceArrayList;
    }

    public RealmResults<WarehouseDestination> getWarehouseDestinationArrayList() {
        return warehouseDestinationArrayList;
    }

    public void setWarehouseDestinationArrayList(RealmResults<WarehouseDestination> warehouseDestinationArrayList) {
        this.warehouseDestinationArrayList = warehouseDestinationArrayList;
    }

    public RealmResults<Cost> getCostArrayList() {
        return costArrayList;
    }

    public void setCostArrayList(RealmResults<Cost> costArrayList) {
        this.costArrayList = costArrayList;
    }

    public ArrayList<Variable> getFeasible() {
        return feasible;
    }

    public void setFeasible(ArrayList<Variable> feasible) {
        this.feasible = feasible;
    }

    public ArrayList<Variable> leastCostRule(){
        int k =1;
        boolean []isSet = new boolean[getWarehouseSourceArrayList().size()*getWarehouseDestinationArrayList().size()-1];

        for(int i=0; i<getWarehouseSourceArrayList().size()*getWarehouseDestinationArrayList().size()-1; i++){
            isSet[i] = false;
        }

        Variable minCost = new Variable();
        while(k < getWarehouseSourceArrayList().size()*getWarehouseDestinationArrayList().size()-1){
            minCost.setBiaya(RealmController.getInstance().getCostObject().get(k));

            for(int m =1; m<isSet.length; m++){
                if(minCost.getBiaya().getCost() > RealmController.getInstance().getCostObject().get(m).getCost()){
                    minCost.setBiaya(RealmController.getInstance().getCostObject().get(m));
                }
            }

            WarehouseSource source = RealmController.getInstance().getwhsource(minCost.getBiaya().getSourceName());
            WarehouseDestination desti = RealmController.getInstance().getwhdestination(minCost.getBiaya().getDestinationName());
            minCost.setSource(source);
            minCost.setDesti(desti);
            int min = Math.min(Integer.valueOf(source.getSourceAmount()),Integer.valueOf(desti.getDestinationAmount()));

            feasible.get(k).setJumlah(min);
            feasible.get(k).setBiaya(minCost.getBiaya());
            feasible.get(k).setSource(minCost.getSource());
            feasible.get(k).setDesti(minCost.getDesti());

            source.setSourceAmount(String.valueOf(Integer.valueOf(minCost.getSource().getSourceAmount())-min));
            desti.setDestinationAmount(String.valueOf(Integer.valueOf(minCost.getDesti().getDestinationAmount())-min));
            k++;

            if(Integer.valueOf(source.getSourceAmount()) == 0){
                for(int p=0; p<warehouseDestinationArrayList.size(); p++){
                    isSet[p] = true;
                }
            }else{
                for(int p=0; p<warehouseSourceArrayList.size(); p++){
                    isSet[p] = true;
                }
            }

        }
        return feasible;
    }

    public int getResultGenerate(){
        int result = 0;
        for(int i=0; i< feasible.size(); i++){
            result += feasible.get(i).getJumlah() * feasible.get(i).getBiaya().getCost();
        }
        return result;
    }
}
