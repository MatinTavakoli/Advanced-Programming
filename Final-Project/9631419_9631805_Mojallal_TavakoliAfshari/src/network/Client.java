package network;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            ObjectInputStream toClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            for (int i = 0;; i++) {
                String command = scanner.nextLine();
                if (!command.equals("Over")) {
                    printStream.println(command);
                    Scanner scanner1=new Scanner(socket.getInputStream());
                    String serverCommand=scanner1.nextLine();
                    if (!serverCommand.equals("Over")) {
                        //System.out.println(userCommand);
                    } else {
                        System.out.println("The game is over!");
                        socket.close();
                        System.exit(0);
                    }
                }
                else {
                    printStream.println("Over");
                    System.out.println("The game is over!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
