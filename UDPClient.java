import java.io.*;
import java.net.*;

public class UDPClient{
public static void main(String[] args) throws IOException {
    InetAddress serverAddress = InetAddress.getByName("localhost");
    int serverPort = 12000;

    DatagramSocket clientSocket = new DatagramSocket();
    
    byte[] sendData;
    byte[] receiveData = new byte[2048];
    // The size of the receiveData array is set to 2048 bytes, indicating the maximum size of the data that can be received from the server in a single UDP packet

    System.out.print("Recipes for: ");
        // Assuming you're reading input from the user
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message = reader.readLine();
        sendData = message.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        
        String modifiedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Here You Go !!:\n " + modifiedMessage);

        clientSocket.close();
}
}