package com.ilyass.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket server = new ServerSocket(3030)) {

            Socket socket = server.accept();
            ServerThread serverThread = new ServerThread(socket,threadList);
            threadList.add(serverThread);
            serverThread.start();

        }
        catch (Exception e ){

            System.err.println("error in main server :" + e.getStackTrace());
        }

    }
}
