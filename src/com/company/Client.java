/*
This is the client class.
Its purpose is to hold the client information
and interact with a server.
 */

package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.IOException;
import java.util.Collection;

public class Client {

    private int port;
    private String server_address;
    private String user;
    private String pass;
    private FTPClient apacheFTPClient;

    /* Connect to a server or throw an exception if the connection fails */
    public void startConnection(String hostname) throws IOException {
        apacheFTPClient = new FTPClient();
        server_address = hostname;

        apacheFTPClient.connect(hostname);

        int reply = apacheFTPClient.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)){
            apacheFTPClient.disconnect();
            apacheFTPClient = null;
            throw new IOException("Negative reply from FTP server, aborting, id was " + reply);
        }
    }

    /* Login to a connected server */
    public void login(String username, String password) throws IOException{
        apacheFTPClient.login(username, password);
    }

    /* Print the name of every file in the directory */
    public void list() throws IOException{
        FTPFile[] files = apacheFTPClient.listFiles();
        for(FTPFile file : files){
            System.out.println(file.getName());
        }
    }
}