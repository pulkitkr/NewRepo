package com.crm.comcast.SDET32.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;


public class SampleProgramForExecuteQuery {

	public static void main(String[] args) throws SQLException {
		
		//1. register the Database
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//2. Get the connection to the Database
		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		
		//3. Create Sql Statement
		Statement statement = connection.createStatement();
		
		//4. Execute SQL Query
		ResultSet result= statement.executeQuery("select * from student;");
		
		while(result.next()) {
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		}
		//5. Close the Database
		connection.close();
	}

}
