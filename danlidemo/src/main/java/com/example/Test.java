package com.example;

public class Test {
    public static void main(String[] strings) {
//        DanLi danLi = DanLi.getDanLi();
//        DanLi danLi1 = DanLi.getDanLi();
//        if (danLi == danLi1) {
//            System.out.println("一样");
//        } else {
//            System.out.println("不一样");
//        }


        DanLi danLi = DanLiEnum.INTANCE.getDanLi();
        DanLi danLi2 = DanLiEnum.INTANCE.getDanLi();
        if (danLi==danLi2){
            System.out.println("一样");
        } else {
            System.out.println("不一样");
        }

    }




}
