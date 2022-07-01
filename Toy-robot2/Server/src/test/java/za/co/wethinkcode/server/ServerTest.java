//package za.co.wethinkcode.server;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import org.json.simple.JSONObject;
//import org.junit.jupiter.api.Test;
//
//public class ServerTest {
//
//    @Test
//    public void testReadFromClient(){
//        ListenForClients lfc = startListening();
//
//        try{
//            Socket clientSocket = new Socket("localhost", lfc.getPort());
//            Thread.sleep(100);
//            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
//
//            Socket serverSocket = lfc.getSocket();
//            SimpleServer testServer = new SimpleServer(serverSocket, null);
//
//            out.writeObject(generateJSON());
//            JSONObject read = testServer.readFromClient();
//
//            assertEquals("pixies", read.get("robot"));
//            assertEquals("forward", read.get("command"));
//            assertEquals("12", ((String[]) read.get("arguments"))[0]);
//            clientSocket.close();
//            serverSocket.close();
//        }catch(IOException | InterruptedException e){
//            System.out.println("Failed to connect to server.");
//            assertTrue(false);
//        }
//    }
//
//    @Test
//    public void testWriteToClient(){
//        ListenForClients lfc = startListening();
//
//        try{
//            Socket clientSocket = new Socket("localhost", lfc.getPort());
//            Thread.sleep(100);
//            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
//            // out.flush();
//            // ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//
//            Socket serverSocket = lfc.getSocket();
//            SimpleServer testServer = new SimpleServer(serverSocket, null);
//
//            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//
//            testServer.respondToClient(generateJSON());
//            JSONObject read = (JSONObject) in.readObject();
//
//            assertEquals("OK", read.get("result"));
//            assertEquals("Done", ((JSONObject)read.get("data")).get("message"));
//            clientSocket.close();
//            serverSocket.close();
//        }catch(IOException | ClassNotFoundException | InterruptedException e){
//            System.out.println("Failed to connect to server.");
//            assertTrue(false);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private String generateJSON(){
//        String result = new JSONObject();
//
//        result.put("robot", "pixies");
//        result.put("command", "forward");
//        result.put("arguments", new String[]{"12"});
//
//        return result;
//    }
//
//    private ListenForClients startListening(){
//        ListenForClients listener = new ListenForClients();
//        Thread thread = new Thread(listener);
//        thread.start();
//
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException ignored) {}
//        return listener;
//    }
//}
//
//class ListenForClients implements Runnable{
//    private Socket connectedSocket;
//    private int port;
//
//    public void run(){
//        listen();
//    }
//
//    private void listen(){
//        try{
//            ServerSocket ss = new ServerSocket(0);
//            this.port = ss.getLocalPort();
//            this.connectedSocket = ss.accept();
//
//            while(!connectedSocket.isClosed()){}
//            ss.close();
//        }catch (IOException e){
//            System.out.println("Cannot listen on port. Server not started.");
//        }
//    }
//
//    public Socket getSocket(){
//        return this.connectedSocket;
//    }
//
//    public int getPort(){
//        return this.port;
//    }
//}
