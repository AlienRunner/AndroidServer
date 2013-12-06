import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;


public class MiniServer extends Thread {
	private Socket socket = null;
    String incomingMessage;
    HashMap<SocketAddress, Coords> map = new HashMap<SocketAddress, Coords>();
    String ip;
	DatabaseStarter dbs = new DatabaseStarter();
	DatabaseHandler dbh = new DatabaseHandler();
	private String t;

	
	public MiniServer(Socket socket){
		super("MiniServer");
		this.socket = socket;
		dbs.createConnection();
		
		t = dbh.setAndFetch(40, 30, "Johan");
	}
	
	public void run(){
		System.out.println("Someone connected");
        System.out.println(socket.getRemoteSocketAddress());
        
        try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 while (true){
                 
                 
                 while ((incomingMessage = in.readLine()) != null && socket.isConnected()){
                	 try{
                			dbh.updateDatabase(incomingMessage);
                	 }catch (Exception e){
                		 System.out.println("Username already exist");
                		 e.printStackTrace();
                	 }
                	 
                         SocketAddress ipAddress = socket.getRemoteSocketAddress();
                         if(map.containsKey(ipAddress) == false){
                                 map.put(ipAddress, new Coords(incomingMessage));
                         }
                         System.out.println("Coordinates recieved: " + incomingMessage + ". Answering...");
                         
                         // send a message
                         String outgoingMsg = t  + System.getProperty("line.separator");
                         
                         out.write(outgoingMsg);
                         out.flush();
     

                         System.out.println("Message sent: " + outgoingMsg);
                 }
                 
                 if (socket.isConnected()){
                         //System.out.println("Socket still connected");
                 }else {
                         System.out.println("Socket not connected");
                 }
         }
         
        } catch (Exception e){
        	System.out.println("Error: " + e.getMessage());
        	e.printStackTrace();
        }
	}
        

}

