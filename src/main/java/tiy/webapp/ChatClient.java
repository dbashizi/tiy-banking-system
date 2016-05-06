package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Dominique on 5/4/2016.
 */
public class ChatClient {
    final String SERVER_HOST = "localhost";
//    final String SERVER_HOST = "172.168.4.9";
    final int SERVER_PORT = 8005;
    public static final String TX_ERROR_MSG = "TX_ERROR::Error sending message - check logs";

    public String sendMessage(String messageToSend) {
        try {
            // connect to the server on the target port
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(messageToSend);
            String serverResponse = in.readLine();

            return serverResponse;
        } catch (IOException networkException) {
            System.out.println("Unable to send message: " + messageToSend +
                    " to " + SERVER_HOST + " on port " + SERVER_PORT);
        }

        return TX_ERROR_MSG;
    }

    public ArrayList<String> sendCommand(String commandToSend) {
        try {
            // connect to the server on the target port
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(commandToSend);
            String serverResponse = in.readLine();

            ArrayList<String> serverResponseArray = new ArrayList<String>();

            while (serverResponse != null && !serverResponse.equals("server-done") && !serverResponse.equals("end-transmission")) {
                serverResponseArray.add(serverResponse);
                serverResponse = in.readLine();
            }

            return serverResponseArray;

        } catch (IOException networkException) {
            System.out.println("Unable to send message: " + commandToSend +
                    " to " + SERVER_HOST + " on port " + SERVER_PORT);
        }

        return null;
    }
}
