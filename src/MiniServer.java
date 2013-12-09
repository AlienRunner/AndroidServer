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
	private HashMap<String, SocketAddress> map;
	private DatabaseStarter dbs;
	private DatabaseHandler dbh;
	private String jsonList;
	private String currUser;

	public MiniServer(Socket socket) {
		super("MiniServer");
		this.socket = socket;
		System.out.println("MiniSocket CREATED!");
		dbs = new DatabaseStarter();
		dbs.createConnection();
		System.out.println("DB CREATED!");
		map = new HashMap<String, SocketAddress>();
		dbh = new DatabaseHandler();
	}

	public void run() {
		System.out.println("Someone connected");
		System.out.println(socket.getRemoteSocketAddress());
		SocketAddress ipAddress = socket.getRemoteSocketAddress();
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
//			while (true) {
				while ((incomingMessage = in.readLine()) != null) {
					User user = decode(incomingMessage);
					if (map.containsKey(user.getUserId()) == false) {
						this.currUser = user.getUserId();
						map.put(currUser, ipAddress);
						dbh.activeUser(user);
						try {
							System.out.println("Trying to add username");
							if (dbh.updateDatabase(user)) {
								System.out.println("UPDATED DB!");
								jsonList = dbh.setAndFetch(user);
							} else {
								jsonList = "Couldn't update db!";
							}
						} catch (Exception e) {
							e.printStackTrace();
							jsonList = "Failed to update!";
						}

					} else {
						jsonList = "Username already in use!";
					}
					System.out.println("ClientInfo recieved: "
							+ incomingMessage + ". Answering...");
					out.write(jsonList + System.getProperty("line.separator"));
					out.flush();
					System.out.println("Message sent: " + jsonList
							+ System.getProperty("line.separator"));
				}
				socket.close();
				
					System.out.println("Closing connection!" + ipAddress);
					map.remove(this.currUser);
					dbh.inactiveUser(this.currUser);
					System.out.println("Socket closed!");
				
//			}

		} catch (Exception e) {
			System.out.println("BufferedWriter/Reader - Error: "
					+ e.getMessage());
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
