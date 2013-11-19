import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

        /**
         * @param args
         */
        public static void main(String[] args) throws IOException{
        	ServerSocket serverSocket = null;
        	
        	boolean listeningSocket = true;
        	try {
                System.out.println("Starting Socket thread...");
        		serverSocket = new ServerSocket(21101);
        		System.out.println("ServerSocket created, waiting for android device...");
        	}catch (IOException e){
        		System.err.println("Could not listen on port: 21101");
        	}
        	
        	while(listeningSocket){
        		Socket clientSocket = serverSocket.accept();
        		MiniServer mini = new MiniServer(clientSocket);
        		mini.start();
        	}
        	serverSocket.close();
        		//ServerThread server = new ServerThread();
        		//server.run();
        }

}