package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by viktoriyasidenko on 12/23/16.
 */
public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        Method[] methods = r.getClass().getMethods();
        for (Method method : methods) {
            if(method.getName().equals("toString")) {
                System.out.println("Name: " + method.getName());
                System.out.println("Return types: " + method.getReturnType().getName());
            }
        }
        System.out.println();

        Method method = r.getClass().getMethod("toString");
        System.out.println(method);
        System.out.println(method.invoke(r));
    }
}