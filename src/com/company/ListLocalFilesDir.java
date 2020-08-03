package com.company;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.IOException;

public class ListLocalFilesDir {
    public static void main(String[] args) {
        File dir;
        dir = new File("/Users/Omar/Desktop/Testing");//This can directly be called in line 10 //first occurence, can be removed
        File[] list = dir.listFiles();
        if (list == null) {
            System.out.println("Directory does not exist");
        } else {
            //list = dir.listFiles();
            System.out.println(dir);
            for (File file : list) {
                if (file.isFile())
                    System.out.println("File is " + file.getName());
                else if (file.isDirectory())
                    System.out.println("Directory is " + file.getName());
            }
        }
    }
}

