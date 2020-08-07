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
    private FTPClient apacheFTPClient;

    /* Connect to a server or throw an exception if the connection fails */
    public void startConnection(String hostname) throws IOException {
        apacheFTPClient = new FTPClient();
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
    }

    /* Print the name of every file in the directory */
    public void list() throws IOException {
        FTPFile[] files = apacheFTPClient.listFiles();
        for (FTPFile file : files) {
            System.out.println(file.getName());
        }
    }


    /* Log off of client and exit program */
    public void logoff() {
        try {
            apacheFTPClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ListLocalFilesDir(String filePath) {
        File dir = new File(filePath);
        File[] list = dir.listFiles();
        if (list == null) {
            System.out.println("Directory does not exist");
        } else {
            System.out.println(dir);
            for (File file : list) {
                if (file.isFile())
                    System.out.println(file.getName());
                else if (file.isDirectory())
                    System.out.println(file.getName());
            }
        }
    }

    /*retrieves a file from the remote server and downloads to C:/Users/Default/Downloads*/
    public boolean get(String filePath) throws IOException {

        apacheFTPClient.enterLocalPassiveMode();
        apacheFTPClient.setFileType(FTP.BINARY_FILE_TYPE);

        File downloadFile = new File("C:/Users/Default/Downloads/" + filePath);
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        boolean success = apacheFTPClient.retrieveFile('/' + filePath, outStream);

        outStream.close();

        if(success) {
            System.out.println(filePath + " has been downloaded\n");
            return true;
        }

        System.out.println("File download failed: Please check the path name\n");
        return false;

    }

    public boolean put(String localPath, String remoteName){

        try {
            apacheFTPClient.setFileType(FTP.BINARY_FILE_TYPE); //ASCII_FILE_TYPE); //, FTP BINARY_FILE_TYPE
            //apacheFTPClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            apacheFTPClient.enterLocalPassiveMode();

            // APPROACH #1: uploads first file using an InputStream
            File localFile = new File(localPath);

            //String firstRemoteFile = "upload/test.zip";
            InputStream isStream = new FileInputStream(localFile);

            String remote =  "upload/" + remoteName;
            boolean success = apacheFTPClient.storeFile(remote, isStream);
            isStream.close();
            if (success) {
                System.out.println(localPath + " uploaded successfully\n");
                return true;
            }

            System.out.println("Error uploading " + localPath + "\n");
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}