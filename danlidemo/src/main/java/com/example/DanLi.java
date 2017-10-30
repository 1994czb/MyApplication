package com.example;

/**
 * Created by Administrator on 2017/10/25.
 * 单例模式：懒汉式，恶汉式
 */

public class DanLi {
    /*
    * 懒汉式：第一种（线程不安全，不建议使用）
    * */
//    public static DanLi danLi;
//    public static DanLi getDanLi(){
//        if (danLi==null){
//            danLi = new DanLi();
//        }
//        return danLi;
//    }

    /*
    * 懒汉式：第二种（加锁，线程安全，不建议使用）
    * */
//    public static DanLi danLi;
//
//    public static synchronized DanLi getDanLi() {
//        if (danLi == null) {
//            danLi = new DanLi();
//        }
//        return danLi;
//    }


    /*
    * 恶汉式：第一种（线程安全）
    * */
//    public static DanLi danLi = new DanLi();
//
//    public static DanLi getDanLi(){
//        return danLi;
//    }

    /*
    * 恶汉式：第二种（双重锁，线程安全）
    * */
//    public static DanLi danLi;
//
//    public static synchronized DanLi getDanLi(){
//        if (danLi==null){
//            synchronized (DanLi.class){
//                if (danLi==null){
//                    danLi=new DanLi();
//                }
//            }
//
//        }
//        return danLi;
//    }

    //静态内部类实现单例(又叫登记式)
    private static class DanLi1{
        private static final DanLi DANLI=new DanLi();
    }
    public static DanLi getDanLi(){
        return DanLi1.DANLI;
    }




}
