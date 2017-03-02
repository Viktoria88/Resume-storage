package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by viktoriyasidenko on 3/1/17.
 */
public class MainFile {
    public static void main(String[] args) {
        File filePath = new File("./.gitignore");
        try {
            System.out.println(filePath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("/Users/viktoriyasidenko/Documents/Projects/U-RiseLvl2/TestTask/resume-storage");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("printDirectoryRecursion");
        printDirectoryRecursion(dir);

    }

    public static void printDirectoryRecursion(File dir) {

        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("     File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    printDirectoryRecursion(file);
                }
            }
        }

    }
}