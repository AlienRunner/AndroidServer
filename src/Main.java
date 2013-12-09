import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	private static int port = 21101;
	private static int maxConnections = 0;
	private static DatabaseHandler dbh;

	/**
	 * @param args
	 * @throws JSONException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		dbh = new DatabaseHandler();
		dbh.resetUserStatuses();
		int i = 0;

		try {
			ServerSocket listener = new ServerSocket(port);
			while ((i++ < maxConnections) || (maxConnections == 0)) {
				Socket clientSocket = listener.accept();
				MiniServer mini = new MiniServer(clientSocket, dbh);
				mini.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
}