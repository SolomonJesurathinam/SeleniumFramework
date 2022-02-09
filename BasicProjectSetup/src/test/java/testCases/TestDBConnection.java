package testCases;

import java.sql.SQLException;

import org.testng.annotations.Test;

import commonfunctions.DbConnection;

public class TestDBConnection {
DbConnection dbConnection;
	@Test
	public void test() throws SQLException {
	DbConnection.establishConnection("com.mysql.cj.jdbc.Driver");
	DbConnection.connectingDBwithoutAuth("jdbc:mysql://root@localhost/superhero","SELECT * FROM `tbl_login`");
	String value = DbConnection.getResult(3,2);
	String value1 = DbConnection.getResult(1,2);
	System.out.println(value);
	System.out.println(value1);
	}
}
