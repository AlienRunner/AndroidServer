import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	//userString is the received string from client from class named databaseHandler
	//method setAndFetch needs to send a string to server which is picked up and sent here
	public void updateDatabase(String userString){
		StringBuilder sb = new StringBuilder(userString);
		sb.substring(1, sb.length());
		userString = sb.toString();
		userString = userString.replace("[", "");
		userString = userString.replace("]", "");
		String[] user = userString.split(",");
		try {
			String sql = "update users set xcoord=?,ycoord=? where userId="+"'"+user[0]+"'";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1,Double.parseDouble(user[1])); 
			preparedStatement.setDouble(2,Double.parseDouble(user[2]));
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//uppdatera om usern finns
	}
	
	public void insertIntoDatabase(String userString){
		StringBuilder sb = new StringBuilder(userString);
		sb.substring(1, sb.length());
		userString = sb.toString();
		userString = userString.replace("[", "");
		userString = userString.replace("]", "");
		String[] user = userString.split(",");
		try {
			String sql = "insert into table users set userId=?,xcoord=?,ycoord=?,race=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,user[0]); 
			preparedStatement.setDouble(2,Double.parseDouble(user[1]));
			preparedStatement.setDouble(3,Double.parseDouble(user[2]));
			preparedStatement.setString(4,user[3]);
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
public String[] JsonToUser(){
	String s = "{menu:{\"1\":\"sql\", \"2\":\"android\", \"3\":\"mvc\"}}";
	 
	String[] b = s.split(",");
	return b;
	}
	
	
	private ArrayList<String> getUserInformation() throws SQLException {
		statement = con.createStatement();
		resultSet = statement
				.executeQuery("select userId,xcoord,ycoord,race from users");
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
			String race = resultSet.getString(4);
			list.add(userId);
			list.add(xCoord);
			list.add(yCoord);
			list.add(race);

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
