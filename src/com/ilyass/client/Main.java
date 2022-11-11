package com.ilyass.client;

import com.ilyass.client.ClientThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Socket socket = new Socket("localhost", 3030);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in) ;
        String userinput ;
        String response ;
        String clientName =  "empty" ;
        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();

        do {
            if(clientName.equals("empty")) {
                System.out.println("enter your name");
                userinput = scanner.nextLine();
                clientName = userinput ;
                output.println(userinput);
                if(userinput.equals("exit")) break;

            }
            else {
                String message = "(" + clientName + ")" + " message : " ;
                System.out.println(message);
                userinput = scanner.nextLine();
                output.println(message + userinput);
                if(userinput.equals("exit")) break;
            }
        }while (!userinput.equals("exit"));

    }
}
