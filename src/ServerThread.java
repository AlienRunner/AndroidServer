//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketAddress;
//import java.util.HashMap;
//
//
//public class ServerThread extends Thread {
//        ServerSocket serverSocket;
//        String incomingCoordinates;
//        HashMap<SocketAddress, Coords> map = new HashMap<SocketAddress, Coords>();
//        String ip;
//        
//        public ServerThread(){
//                
//        }
//        
//        public void run(){
//                
//                /* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	/* DEN HÄR KLASSEN BEHÖVS INTE LÄNGRE MEN SPARAR DEN JUST-IN-CASE  */
//        	
//                try{
//                        System.out.println("Starting Socket thread...");
//                        
//                        serverSocket = new ServerSocket(21101); //implements the server side of the connection 
//                        System.out.println("ServerSocket created, waiting for android device...");
//                        
//                        Socket socket = serverSocket.accept(); //implements the client side of the connection
//                        System.out.println("Someone connected");
//                        System.out.println(socket.getRemoteSocketAddress());
//                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//                        while (true){
//                                
//                                
//                                while ((incomingCoordinates = in.readLine()) != null && socket.isConnected()){
//                                        SocketAddress ipAddress = socket.getRemoteSocketAddress();
//                                        if(map.containsKey(ipAddress) == false){
//                                                map.put(ipAddress, new Coords(incomingCoordinates));
//                                        }
//                                        System.out.println("Coordinates recieved: " + incomingCoordinates + ". Answering...");
//                                        
//                               // send a message
//                    String outgoingMsg = "Server reply: Hi I just received this message: \"" + incomingCoordinates
//                            + "\"."
//                            + System.getProperty("line.separator");
//                    out.write(outgoingMsg);
//                    out.flush();
//                    
//
//                    System.out.println("Message sent: " + outgoingMsg);
//                                }
//                                if (socket.isConnected()){
//                                        System.out.println("Socket still connected");
//                                }else {
//                                        System.out.println("Socket not connected");
//                                }
//                        }
//                        
//                } catch (Exception e){
//                        System.out.println("Error: " + e.getMessage());
//                        e.printStackTrace();
//                }
//        }
//}
