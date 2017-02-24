package com.urise.webapp;

/**
 * Created by viktoriyasidenko on 2/24/17.
 */
public class TestSingleton {
    private static TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }
}
