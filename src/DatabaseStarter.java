import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStarter {
	private String driverName = "com.mysql.jdbc.Driver";
	private Connection con = null;

	public DatabaseStarter() {
		try {
			// Loading Driver for MySql
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	public Connection createConnection() {
		try {
			String connectionUrl = "jdbc:mysql://localhost:3306/alienrunner_db?";
			String userName = "root";
			String userPass = "";
			con = DriverManager
					.getConnection(connectionUrl, userName, userPass);
			System.out.println("*" + ""
					+ "****** Connection created successfully........");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public void closeConnection() {
		try {
			this.con.close();
			System.out
					.println("******* Connection closed Successfully.........");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}