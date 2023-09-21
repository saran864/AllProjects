package skart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import skart.controller.Jdbc;

public class AuthenticationDAO {

	public static JSONObject credentialCheck(String username, String password) {
		JSONObject json=null;
		try{
			Statement ps = Jdbc.getInstance().createStatement();
			ResultSet rs = ps.executeQuery("select RegisterName,Age,Address,role from Users where username= "+username+" and password="+password);
			
			
			while (rs.next()) {
				json =new JSONObject();
				json.put("name",rs.getString(1));
				json.put("age",rs.getString(2));
				json.put("address",rs.getString(3));
				json.put("role",rs.getString(4));	
			}
			return json;
		} catch (Exception e) {
			System.out.println();
			json=null;
			e.printStackTrace();
			return json;
		}

		

	}

}
