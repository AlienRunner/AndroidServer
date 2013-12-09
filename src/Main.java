import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	ServerSocket serverSocket = null;
	private static int port = 21101;
	private static int maxConnections = 0;

	/**
	 * @param args
	 * @throws JSONException
	 */
	public static void main(String[] args) throws IOException, JSONException {

		int i = 0;

		try {
			ServerSocket listener = new ServerSocket(port);

			while ((i++ < maxConnections) || (maxConnections == 0)) {
				Socket clientSocket = listener.accept();
				MiniServer mini = new MiniServer(clientSocket);
				mini.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
	// boolean listeningSocket = true;
	// try {
	// System.out.println("Starting Socket thread...");
	// serverSocket = new ServerSocket(21101);
	// System.out.println("ServerSocket created, waiting for android device...");
	// }catch (IOException e){
	// System.err.println("Could not listen on port: 21101");
	// }
	//
	// while(listeningSocket){
	// Socket clientSocket = serverSocket.accept();
	// MiniServer mini = new MiniServer(clientSocket);
	// mini.start();
	// }
	// serverSocket.close();
	// //ServerThread server = new ServerThread();
	// //server.run();
	// }

}