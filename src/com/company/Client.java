/*
This is the client class.
Its purpose is to hold the client information
and interact with a server.
 */

package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTP;
import java.io.*;

public class Client {
    private boolean loggedIn;
    private int port;
    private String server_address;
    private String user;
    private String pass;
    private FTPClient apacheFTPClient;

    public Client() throws IOException {
    }

    /* Connect to a server or throw an exception if the connection fails */
    public void startConnection(String hostname) throws IOException {
        //apacheFTPClient = new FTPClient();
        apacheFTPClient = new FTPClient();
        server_address = hostname;

        apacheFTPClient.connect(hostname);

        int reply = apacheFTPClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            apacheFTPClient.disconnect();
            apacheFTPClient = null;
            throw new IOException("Negative reply from FTP server, aborting, id was " + reply);
        }
    }

    /* Login to a connected server */
    public void login(String username, String password) throws IOException {
        apacheFTPClient.login(username, password);
        loggedIn = true;
    }

    /* Print the name of every file in the directory */
    public void list() throws IOException {
        FTPFile[] files = apacheFTPClient.listFiles();
        for (FTPFile file : files) {
            System.out.println(file.getName());
        }
    }

    public boolean logoff() {
        if (loggedIn) {
            try {
                apacheFTPClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            loggedIn = false;
            return true;
        }
        return false;
    }

    public boolean get(String filePath) throws IOException {

        apacheFTPClient.enterLocalPassiveMode();
        apacheFTPClient.setFileType(FTP.BINARY_FILE_TYPE);

        /*String remoteFile1 = "1KB.zip";
        File downloadFile1 = new File("C:/Users/Benja/Downloads/1KB.zip");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = apacheFTPClient.retrieveFile(remoteFile1, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File #1 has been downloaded successfully.");
        }*/

        apacheFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
       //File toGet = new File(filePath);
        File downloadFile = new File("C:/Users/Benja/Downloads/" + filePath);

        //FileOutputStream fos = new FileOutputStream(filePath);
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        //FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        boolean success = apacheFTPClient.retrieveFile('/' + filePath, outStream);

        outStream.close();

        if(success) {
            System.out.println(filePath + " has been downloaded\n");
            return true;
        }

        System.out.println("File download failed: Please check the path name\n");
        return false;

    }
}

