package com.company;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;

public class ListLocalFilesDir {
    public static void main(String[] args) {
        File dir;
        dir = new File("test");
        File[] list = dir.listFiles();

        if (list == null) {
            System.out.println("Directory does not exist");
        }
    }
}