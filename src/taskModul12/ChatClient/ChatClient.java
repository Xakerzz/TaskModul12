package taskModul12.ChatClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient {
    private  ArrayList<Client> clients = new ArrayList<>();
    private  String [] namesOfUsersList= {"Ronald", "Rowland", "Rufus", "Russell", "Sebastian", "Shahaf", "Simon", "Stephen", "Swaine", "Thomas",
            "Dickon", "Donald", "Dougie", "Douglas", "Earl", "Ebenezer", "Edgar", "Edmund", "Edward", "Edwin"};
    private  ServerSocket serverSocket;
    private  int flagId=1;


    public ChatClient() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    public  void sendAll(String massage,int flagId,String name){
        for (Client client:clients) {
if (client.getFlag()!=flagId)
    client.receive(massage,name);

        }
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Waiting.....");

                Socket socket = serverSocket.accept();

                System.out.println("Client connected- " + namesOfUsersList[flagId-1]);

                clients.add( new Client(socket, this,flagId++,namesOfUsersList[flagId-1]));



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        new ChatClient().run();

    }
}
