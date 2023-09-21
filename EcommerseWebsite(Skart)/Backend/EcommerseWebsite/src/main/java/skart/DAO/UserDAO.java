package skart.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import skart.controller.Jdbc;
import skart.model.SkartUser;

public class UserDAO {

	public  static boolean register (SkartUser register) {
		try {
            int passid = 0;
	
			PreparedStatement getQuery = Jdbc.getInstance().prepareStatement("insert into Users values(?,?,?,?,?,?,?)");
			getQuery.setInt(1, passid);
			getQuery.setString(2, register.getName());
			getQuery.setInt(3, register.getAge());
			getQuery.setString(4, register.getUsername());
			getQuery.setString(5, register.getPassword());
			getQuery.setString(6, register.getAddress());
			getQuery.setString(7, register.getRole());
			
			getQuery.executeUpdate();
			getQuery.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NotConnected");
			return false;
		}

	}
	
	
	
	
	public static JSONArray showUsers()
	{
		JSONArray dataArray = new JSONArray();
		try {

			PreparedStatement ps = Jdbc.getInstance().prepareStatement("select RegNo,RegisterName,Age,Username,Address from Users where role =?");
			ps.setString(1,"user");
			ResultSet resultset = ps.executeQuery();
		
        while(resultset.next())
        {
   
        	JSONObject jsonObject = new JSONObject();
			jsonObject.put("Regno", resultset.getString(1));
			jsonObject.put("name", resultset.getString(2));
			jsonObject.put("age", resultset.getString(3));
			jsonObject.put("mail", resultset.getString(4));
			jsonObject.put("address", resultset.getString(5));		
       dataArray.put(jsonObject);
		}
        return dataArray;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	
		
	}
	
	public  static boolean  validMail(String mailId) {
		boolean check=false;
		PreparedStatement getQuery;
		try {
			
			getQuery = Jdbc.getInstance().prepareStatement("select Username from  Users where  Username=?");

		       getQuery.setString(1,mailId);
		       ResultSet rs=getQuery.executeQuery();
		       while(rs.next())
		       {  
		    	   check=true;   
		       }
		      
		} catch (SQLException e) {
		    
			e.printStackTrace();
			
			
		}
		return check;


}
	
	
	public  static boolean updatePassword(SkartUser register) {
		try {
            int passid = 0;
			PreparedStatement getQuery = Jdbc.getInstance().prepareStatement("insert into Users values(?,?,?,?,?,?,?)");
			getQuery.setInt(1, passid);
			getQuery.setString(2, register.getName());
			getQuery.setInt(3, register.getAge());
			getQuery.setString(4, register.getUsername());
			getQuery.setString(5, register.getPassword());
			getQuery.setString(6, register.getAddress());
			getQuery.setString(7, register.getRole());
			
			getQuery.executeUpdate();
			getQuery.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NotConnected");
			return false;
		}

	}
		
	
}