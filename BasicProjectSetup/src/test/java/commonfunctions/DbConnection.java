package commonfunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet result = null;
	
	public static void establishConnection(String DBName) throws SQLException {
		try {
			Class.forName(DBName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void connectingDBwithoutAuth(String DBUrl, String Query) throws SQLException {
		connection = DriverManager.getConnection(DBUrl);  
		statement=  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		result = statement.executeQuery(Query);
	}
	
	public static void connectingDBwithAuth(String DBUrl, String userName,String password,String Query) throws SQLException {
		connection = DriverManager.getConnection(DBUrl,userName,password); 
		statement=  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		result = statement.executeQuery(Query);
	}
	
	public static String getResult(int row, int column) throws SQLException {
		String value = null;
		result.absolute(row);
		value = result.getString(column);
		//result.beforeFirst();
	return value;	
	}
	
	
	
}
