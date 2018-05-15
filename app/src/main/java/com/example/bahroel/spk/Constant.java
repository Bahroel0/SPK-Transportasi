package com.example.bahroel.spk;

public class Constant {

    private static Constant mInstance= null;

    public int id;

    protected Constant(){}

    public static synchronized Constant getInstance() {
        if(null == mInstance){
            mInstance = new Constant();
        }
        return mInstance;
    }

}
