package com.example;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/11/15.
 */
//// // TODO: 2017/11/15 迭代模式
public class MyIterator {

    public static void print(Collection coll){
        Iterator it = coll.iterator();
        while(it.hasNext()){
            String str = (String)it.next();
            System.out.println(str);
        }
    }


}
