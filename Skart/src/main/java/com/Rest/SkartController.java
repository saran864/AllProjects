package com.Rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkartController {

	public void register(SkartUser register) {
		try {

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Skart", "root", "password");
			System.out.println("connected Successfuly");
			PreparedStatement getQuery = c.prepareStatement("insert into Register values(?,?,?,?,?)");
			getQuery.setString(1, register.getName());
			getQuery.setInt(2, register.getAge());
			getQuery.setString(3, register.getUsername());
			getQuery.setString(4, register.getPassword());
			getQuery.setString(5, register.getAddress());
			getQuery.executeUpdate();
			getQuery.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NotConnected");
		}

	}

	public void login(SkartUser login) throws SQLException {

	}

}
