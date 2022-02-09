package testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DatabaseConnection {

	@Test
	public void dbConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://root@localhost/superhero");  //"jdbc:mysql://root@localhost/superhero","root","root"
		Statement statement=  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = statement.executeQuery("SELECT * FROM `tbl_login`");
		
		while(result.next()) {
			System.out.println("Name "+result.getString(2)+" Password"+result.getString(3));
		}
		
		/*
		boolean b = result.last(); 
		int count = result.getRow(); 
		result.beforeFirst();
		int columncount = result.getMetaData().getColumnCount();

		System.out.println(count);
		System.out.println(columncount);
		
		result.absolute(2);
		System.out.println(result.getString(3));
		*/
	}
	
}
