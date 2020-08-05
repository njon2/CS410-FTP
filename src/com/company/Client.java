/*
This is the client class.
Its purpose is to hold the client information
and interact with a server.
 */

package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTP;

import java.io.*;
import java.util.Collection;

public class Client {
    private boolean loggedIn;
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
        System.out.println("Reading file names: ");
        FTPFile[] files = apacheFTPClient.listFiles();
        System.out.println("Starting printing file names: ");
        for (FTPFile file : files) {
            System.out.println("File :" + file.getName());
        }
    }

    public boolean logoff() {
        if (loggedIn) {
            try {
                return apacheFTPClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            loggedIn = false;
            return true;
        }
        return false;
    }

    public void enterLocalPassiveMode() {
        apacheFTPClient.enterLocalPassiveMode();
    }

    /*retrieves a file from the remote server and downloads to C:/Users/Default/Downloads*/
    public boolean get(String filePath) throws IOException {

        //apacheFTPClient.enterLocalPassiveMode();
        apacheFTPClient.setFileType(FTP.BINARY_FILE_TYPE);

        //File downloadFile = new File("C/Users/Omar/Desktop/Testing" + filePath);
        File downloadFile = new File("C:/Users/Omar/Desktop/Testing/" + filePath);
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        boolean success = apacheFTPClient.retrieveFile('/' + filePath, outStream);

        outStream.close();

        if (success) {
            System.out.println(filePath + " has been downloaded\n");
            return true;
        }

        System.out.println("File download failed: Please check the path name\n");
        return false;

    }

    public File[] readFiles(String directory) {
        File dir = new File(directory);
        File[] list = dir.listFiles();
        if (list == null) {
            System.out.println("Directory does not exist");
        } else {
            System.out.println(dir);
            for (File file : list) {
                if (file.isFile())
                    System.out.println("File is " + file.getName());
                else if (file.isDirectory())
                    System.out.println("Directory is " + file.getName());
            }
        }
        return list;
    }

    public void ListLocalFilesDir() {
        File dir;
        dir = new File("/Users/Omar/Desktop/Testing");
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