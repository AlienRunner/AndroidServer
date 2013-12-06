import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStarter {
	String driverName = "com.mysql.jdbc.Driver";
	Connection con = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

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
			/*
			 * statement = con.createStatement(); resultSet = statement
			 * .executeQuery("select userId,xcoord,ycoord from users");
			 * writeResultSet(resultSet);
			 */
			// System.out.println(con.prepareStatement("show database;"));
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString(1);
			String xCoord = resultSet.getString(2);
			String yCoord = resultSet.getString(3);
			// String website = resultSet.getString("webpage");
			// String summary = resultSet.getString("summary");
			// Date date = resultSet.getDate("datum");
			// String comment = resultSet.getString("comments");
			System.out.println("User: " + user + xCoord + yCoord);
			// System.out.println("Website: " + website);
			// System.out.println("Summary: " + summary);
			// System.out.println("Date: " + date);
			// System.out.println("Comment: " + comment);
		}
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