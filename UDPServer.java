import java.io.*;
import java.net.*;
import java.net.http.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class UDPServer {
    public static void main(String[] args) throws IOException, InterruptedException,JSONException{
        int serverPort = 12000;

        DatagramSocket serverSocket = new DatagramSocket(serverPort);
        System.out.println("The server is ready to receive");

        byte[] receiveData = new byte[2048];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String modifiedMessage = recipeSearch(message);
            byte[] sendData = modifiedMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }
    public static String recipeSearch(String message) throws IOException, InterruptedException, JSONException {
        // Replace YOUR_APP_ID and YOUR_APP_KEY with your actual Edamam API credentials
        String appId = "8eb5f346";
        String appKey = "4997284633169cd5f865853e339d25ec";
    
        String encodedMessage = URLEncoder.encode(message, "UTF-8");
    String encodedType = URLEncoder.encode("public", "UTF-8");

    // Construct the request URL with the encoded parameters
    String url = "https://api.edamam.com/api/recipes/v2?q=" + encodedMessage + "&type=" + encodedType + "&app_id=" + appId + "&app_key=" + appKey;

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Accept", "application/json")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
    
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    JSONObject jsonResponse = new JSONObject(response.body());
    JSONArray hits = jsonResponse.getJSONArray("hits");

    // StringBuilder to store the result
    StringBuilder result = new StringBuilder();

    // Iterate over each hit and extract the recipe label
    for (int i = 0; i < hits.length(); i++) {
        JSONObject hit = hits.getJSONObject(i);
        JSONObject recipe = hit.getJSONObject("recipe");
        String label = recipe.getString("label");
        result.append(label).append("\n");
    }

    return result.toString();
}
}
