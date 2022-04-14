package com.crm.comcast.SDET32.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleProgramForExecuteUpdateTest {
	
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		
		Statement statement = connection.createStatement();
		int result=statement.executeUpdate("insert into student values(102,'angjd',98);");
		if(result==1) {
			System.out.println("The table is Updated ");
		}
		else System.out.println("The table has not been created");
		}
		catch(Exception e) {
		
		}
		finally {
		connection.close();
		System.out.println("Database has been closed successfully");
		}
	}

}
