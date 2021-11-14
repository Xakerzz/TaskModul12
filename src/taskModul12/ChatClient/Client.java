package taskModul12.ChatClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Client implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintStream out;
    private ChatClient chatClient;
    private int flag;
    private String userName;


    public Client(Socket socket, ChatClient chatClient, int flag, String userName) {
        this.socket = socket;
        this.chatClient = chatClient;
        this.flag = flag;
        this.userName = userName;
        new Thread(this).start();

    }

    public int getFlag() {
        return flag;
    }

    public String getUserName() {
        return userName;
    }

    public void receive(String message, String name) {

        out.println(getTimeSend() + " " + name + " " + message);

    }

    public String getTimeSend() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        calendar.getTime();
        return dateFormat.format(calendar.getTime());
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();

            OutputStream os = socket.getOutputStream();


            in = new Scanner(is);

            out = new PrintStream(os);


            out.println("Welcome to the chat " + this.userName);

            String input = in.nextLine();

            while (!input.equals("bye")) {
                chatClient.sendAll(input, getFlag(), getUserName());
                input = in.nextLine();


            }
            /*Пробывал добавить время отправки тому кто отправляет, но не получилось ,отображает не время отправки ,а время вызова метода getTimeSend*/

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
