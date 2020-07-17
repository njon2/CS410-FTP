package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //Main method
    public static void main(String args[]) throws IOException
    {
        //Initialize necessary objects
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client ftpClient = new Client();

        //Variables for storing connection information
        String hostname;
        String username;
        String password;

        //Get the hostname
        System.out.println("Please enter the FTP Server hostname:");
        hostname = reader.readLine();
        //Get the username
        System.out.println("Please enter your username:");
        username = reader.readLine();
        //Get the password
        System.out.println("Please enter your password:");
        password = reader.readLine();

        //Sanity Check
        System.out.println("You entered: " + hostname + " for hostname.");
        System.out.println("You entered: " + username + " for username.");
        System.out.println("You entered: " + password + " for password.");

        //Next step is to probably do something like this...
        //Client.startConnection(hostname, username, password);
    }
}
