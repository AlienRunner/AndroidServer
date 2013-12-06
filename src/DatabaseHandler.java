import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.*;

public class DatabaseHandler {

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

	public boolean updateDatabase(User user) {
		if(checkIfExist(user)){	
			try {
				String sql = "update users set xcoord=?,ycoord=? where userId="
					+ "'" + user.getUserId() + "'";
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setDouble(1,user.getxCoord());
				preparedStatement.setDouble(2,user.getyCoord());
				preparedStatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			insertIntoDatabase(user);
			return true;
		}
	}
	
	public boolean checkIfExist(User user) {
		try {
			String sql = "Select * from users where userId="
				+ "'" + user.getUserId() + "'";
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void insertIntoDatabase(User user) {
		try {
			String sql = "insert into table users set userId=?,xcoord=?,ycoord=?,race=?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setDouble(2, user.getxCoord());
			preparedStatement.setDouble(3, user.getyCoord());
			preparedStatement.setString(4, user.getRace());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String setAndFetch(User user) {
		try {
			String sql = "update users set xcoord=?,ycoord=? where userId="
					+ "'" + user.getUserId() + "'";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDouble(1, user.getxCoord());
			preparedStatement.setDouble(2, user.getyCoord());
			preparedStatement.executeUpdate();
			list = getUserInformation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = gson.toJson(list);
		return s;
	}

	private ArrayList<String> getUserInformation() throws SQLException {
		statement = con.createStatement();
		resultSet = statement.executeQuery("select userId,xcoord,ycoord,race from users");
		ArrayList<String> userArrayList = writeResultSet(resultSet);
		return userArrayList;
	}

	private ArrayList<String> writeResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		while (resultSet.next()) {
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
	}
}
