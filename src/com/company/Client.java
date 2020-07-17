package com.company;

//import

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class Client {

    private int port;
    private String server_address;
    private String user;
    private String pass;
    private FTPClient apacheFTPClient;

    public void startConnection(String hostname, String username, String password) throws IOException {
        apacheFTPClient = new FTPClient();
        port = 22;
        server_address = hostname;
        user = username;
        pass = password;

        apacheFTPClient.connect(hostname, port);
    }
}
