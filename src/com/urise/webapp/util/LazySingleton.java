package com.urise.webapp.util;

/**
 * Created by viktoriyasidenko on 3/17/17.
 */
public class LazySingleton {
    int i;
    volatile private static LazySingleton INSTANCE;

    double sin = Math.sin(13.);

    private LazySingleton() {
    }

//    public static synchronized LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    int i = 13;
//                    INSTANCE = new LazySingleton();
//                }
//            }
//
//        }
//        return INSTANCE;
//    }

    // Initialization on demand holder idiom

    private static class LazySingletonHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance(){
        return LazySingletonHolder.INSTANCE;
    }

}
