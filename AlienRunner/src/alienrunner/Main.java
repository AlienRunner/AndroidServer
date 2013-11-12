package alienrunner;

public class Main {

	public static void main(String[] args){
		ServerThread server = new ServerThread();
		server.run();
	}
}
