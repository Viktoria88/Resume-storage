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

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        try(FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        File project = new File("/Users/viktoriyasidenko/Documents/Projects/U-RiseLvl2/TestTask/resume-storage");
//        String[] pack = project.list();
//        if(pack != null){
//            for (String name : pack){
//                System.out.println(name);
//                return name;
//            }
//        }
//        return;
//

    }


//    public static File recursive (){
//        File project = new File("/Users/viktoriyasidenko/Documents/Projects/U-RiseLvl2/TestTask/resume-storage");
//        String[] pack = project.list();
//
//        if(namePackage.isDirectory()){
//            String[] direct = namePackage.list();
//            if (direct != null){
//                for (String name : direct){
//                    System.out.println(name);
//                    return namePackage;
//                }
//            }
//        }
//        return project;
//    }
}
