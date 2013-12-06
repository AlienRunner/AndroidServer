import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import org.json.JSONException;
import org.json.JSONObject;


public class Main {

        /**
         * @param args
         * @throws JSONException 
         */
        public static void main(String[] args) throws IOException, JSONException{
        	ServerSocket serverSocket = null;

//        	int x =3;
//        	int y =3;
//        	String userId = "hej";
//        	String t;
//        	DatabaseStarter dbs = new DatabaseStarter();
//        	dbs.createConnection();
//    		DatabaseHandler dbh = new DatabaseHandler();
//    		t = dbh.setAndFetch(40, 30, "Johan");
//    		String[] md = dbh.JsonToUser();
//    		System.out.println(t);
//    		System.out.println(md[1]);
//       	System.out.println("update users set xcoord="+x+",ycoord="+y+
//				" where userId="+"'"+userId+"'");

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