# RecipeNet

RecipeNet is a simple UDP client-server application for searching recipes using the Edamam API.

## Components

- **UDPClient.java**: Represents the UDP client. Sends recipe queries to the server and displays the search results.
- **UDPServer.java**: Represents the UDP server. Listens for incoming queries, performs recipe searches, and sends results back to clients.
- **recipeSearch() method**: In the `UDPServer` class. Performs recipe searches using the Edamam API.

## Usage

1. Ensure you have Java installed on your system.
2. Compile the Java files using `javac UDPClient.java` and `javac -cp ".;lib\org.json.jar" UDPServer.java`.
3. Create a `lib` folder in the project directory.
4. Download the `org.json.jar` file and place it in the `lib` folder.
5. Start the server by running `java UDPServer` by the command `java -cp ".;lib\org.json.jar" UDPServer`.
6. Start the client by running `java UDPClient`.
7. Enter a recipe query when prompted.
8. View the search results displayed by the client.

   
## Note

- This project uses UDP (User Datagram Protocol) for communication, which is connectionless and unreliable. It's suitable for applications where occasional packet loss is acceptable.
- Ensure your firewall settings allow communication over the specified port (default port: 12000).
