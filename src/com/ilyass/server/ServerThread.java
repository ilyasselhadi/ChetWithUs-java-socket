package com.ilyass.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket ;
    private ArrayList<ServerThread> threadList ;
    private PrintWriter output ;

    public ServerThread (Socket s , ArrayList<ServerThread> threads) throws IOException {

        this.socket =s ;
        this.threadList = threads;
    }
    @Override
    public void run() {

        try {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))    ;
        output = new PrintWriter(socket.getOutputStream(),true);
        while (true) {
            String outputString = input.readLine();
            if(outputString.equals("exit")) break;
            printToAllClient(outputString) ;
            System.out.println("server received : " + outputString);
        }
    }
        catch (Exception e) {
            System.err.println("error in ServerThread : " + e);
        }
    }

    private void printToAllClient(String msg) {
         for (ServerThread st : threadList ) {
             st.output.println(msg );
         }
    }

}
