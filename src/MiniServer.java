import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

public class MiniServer extends Thread {
	private Socket socket = null;
	private String incomingMessage;
	private HashMap<SocketAddress, String> map;
	private String ip;
	private DatabaseStarter dbs;
	private DatabaseHandler dbh;
	private String jsonList;

	public MiniServer(Socket socket) {
		super("MiniServer");
		this.socket = socket;
		this.dbs.createConnection();
		this.map = new HashMap<SocketAddress, String>();
		this.dbs = new DatabaseStarter();
		this.dbh = new DatabaseHandler();
	}

	public void run() {
		System.out.println("Someone connected");
		System.out.println(socket.getRemoteSocketAddress());

		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				while ((incomingMessage = in.readLine()) != null && socket.isConnected()) {
					try {
						System.out.println("Trying to add username");
						User user = decode(incomingMessage);
						if(dbh.updateDatabase(user)){
							jsonList = dbh.setAndFetch(user);							
						}else{
							jsonList = "Couldn't update db!";
						}
					} catch (Exception e) {
						e.printStackTrace();
						jsonList = "Failed to update!";
					}
					SocketAddress ipAddress = socket.getRemoteSocketAddress();
					if (map.containsKey(ipAddress) == false) {
						map.put(ipAddress, incomingMessage);
					}
					System.out.println("ClientInfo recieved: " + incomingMessage + ". Answering...");
					out.write(jsonList + System.getProperty("line.separator"));
					out.flush();
					System.out.println("Message sent: " + jsonList + System.getProperty("line.separator"));
				}

				if (socket.isConnected() == false) {
					System.out.println("Socket closed!");
				}
			}

		} catch (Exception e) {
			System.out.println("BufferedWriter/Reader - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	// @return User-object
	private User decode(String clientMsg){
		clientMsg = clientMsg.replace("[", "");
		clientMsg = clientMsg.replace("]", "");
		String[] userInfo = clientMsg.split(",");
		User user = new User(userInfo[0], Double.parseDouble(userInfo[1]), Double.parseDouble(userInfo[2]), userInfo[3]);
		return user;
	}
}
