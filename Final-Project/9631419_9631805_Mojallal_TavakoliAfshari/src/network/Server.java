package network;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (!serverSocket.isClosed()) {
                Socket userSocket = serverSocket.accept();
                ObjectInputStream toServerCommands = new ObjectInputStream(userSocket.getInputStream());
                ObjectOutputStream toClientCommands = new ObjectOutputStream(userSocket.getOutputStream());
                for (int i = 0; ; i++) {
                    Scanner scanner = new Scanner(userSocket.getInputStream());
                    String userCommand = scanner.nextLine();
                    if (!userCommand.equals("Over")) {
                        //System.out.println(userCommand);
                        Scanner scanner1=new Scanner(System.in);
                        PrintStream printStream=new PrintStream(scanner1.nextLine());
                    } else {
                        serverSocket.close();
                        System.exit(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
