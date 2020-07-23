/*
This is the client class.
Its purpose is to hold the client information
and interact with a server.
 */

package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.IOException;

public class Client {

    private int port;
    private String server_address;
    private String user;
    private String pass;
    private FTPClient apacheFTPClient;

    /* Connect to a server or throw an exception if the connection fails */
    public void startConnection(String hostname, String username, String password) throws IOException {
        apacheFTPClient = new FTPClient();
        port = 22;
        server_address = hostname;
        user = username;
        pass = password;

        apacheFTPClient.connect(hostname, port);
        apacheFTPClient.login(username, password);

        int reply = apacheFTPClient.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)){
            apacheFTPClient.disconnect();
            throw new IOException("Negative reply from FTP server, aborting, id was " + reply);
        }
    }
}
