import java.sql.*;
import java.util.ArrayList;
import com.google.gson.*;

public class DatabaseHandler implements DatabaseHandlerInterface {

	private DatabaseStarter dbs;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private Connection con;
	private ResultSet resultSet = null;
	private ArrayList<String> list;
	private Gson gson;

	public DatabaseHandler() {
		dbs = new DatabaseStarter();
		con = dbs.createConnection();
		list = new ArrayList<String>();
		gson = new Gson();
	}

	@Override
	public String setAndFetch(double x, double y, String userId) {
		try {
			String sql = "update users set xcoord=?,ycoord=? where userId="+"'"+userId+"'";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1,x); 
			preparedStatement.setDouble(2,y);
			preparedStatement.executeUpdate();
			list = getUserInformation();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s  = gson.toJson(list);
		return s;
	}
	

	private ArrayList<String> getUserInformation() throws SQLException {
		statement = con.createStatement();
		resultSet = statement
				.executeQuery("select userId,xcoord,ycoord from users");
		ArrayList<String> userArrayList = writeResultSet(resultSet);
		return userArrayList;
	}

	private ArrayList<String> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set

		ArrayList<String> list = new ArrayList<String>();
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String userId = resultSet.getString(1);
			String xCoord = resultSet.getString(2);
			String yCoord = resultSet.getString(3);
			list.add(userId);
			list.add(xCoord);
			list.add(yCoord);

		}
		return list;

		// Date date = resultSet.getDate("datum");
		// String comment = resultSet.getString("comments");
		// System.out.println("User: " + userId);
		// System.out.println("Website: " + website);
		// System.out.println("Summary: " + summary);
		// System.out.println("Date: " + date);
		// System.out.println("Comment: " + comment);
	}
}
