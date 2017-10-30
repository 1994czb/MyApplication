package com.example;

/**
 * Created by Administrator on 2017/10/25.
 */

public enum DanLiEnum {
    INTANCE {
        @Override
        String getstring() {
            return null;
        }
    };

    private DanLi danLi;
    DanLiEnum(){
        danLi=new DanLi();
    }

    public DanLi getDanLi(){
        return danLi;
    }
    abstract String getstring();


}
