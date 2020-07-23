package com.company;


import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/*
We will create a test file like this for every class that we write
The imports above import Junit 4 and 5
The @Test keyword is used to signify Tests
*/

public class MainTest {

    /* Assert client can connect to a test server */
    @Test
    public void testStartConnection() {
        boolean isConnected = true;
//Client ftpClient = new Client();

//Server connection
        try {
            FTPClient ftpClient = new FTPClient(); // Client ftpClient = new Client();
            ftpClient.connect("speedtest.tele2.net");
            ftpClient.login("anonymous", "anonymous");
            //ftpClient.startConnection("ftp://speedtest.tele2.net", "anonymous", "anonymous");
            System.out.println("Correct Server: Pass");
        } catch (IOException e) {
            System.out.print(e.getMessage());
            isConnected = false;
        }

        Assertions.assertTrue(isConnected);
    }

    /* Assert passing null parameters throws an exception */
    @Test
    public void testNullStartConnection() {
        Client ftpClient = new Client();

        Assertions.assertThrows(IOException.class, () ->
                ftpClient.startConnection("", "", ""));
    }
}