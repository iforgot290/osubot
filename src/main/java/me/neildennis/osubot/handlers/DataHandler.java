package me.neildennis.osubot.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("unused")
public class DataHandler {

	private Connection conn = null;
	
	private String address;
	private String database;
	private String user;
	private String password;
	
	public DataHandler(String address, String database, String user, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this.address = address;
		this.database = database;
		this.user = user;
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://"+address+"/"+database+"?user="+user+"&password="+password);
	}
	
	private void query(String query) throws SQLException{
		Statement statement = conn.createStatement();
		statement.execute(query);
	}

}
