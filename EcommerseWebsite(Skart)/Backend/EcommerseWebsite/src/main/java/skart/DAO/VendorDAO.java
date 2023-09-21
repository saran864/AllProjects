package skart.DAO;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;


import skart.controller.Jdbc;
import skart.model.Vendor;

public class VendorDAO {

	public static boolean insertVendors(Vendor vendor)
	 {
		PreparedStatement getQuery=null;
		PreparedStatement getQuery1=null;
	 
			try {
				int supId = 0;
				 getQuery = Jdbc.getInstance().prepareStatement("insert into Users values(?,?,?,?,?,?,?)");
				Jdbc.getInstance().setAutoCommit(false);
				getQuery.setInt(1, supId);
				getQuery.setString(2,vendor.getName());
				getQuery.setInt(3, vendor.getAge());
				getQuery.setString(4,vendor.getUserName());
				getQuery.setString(5, vendor.getPassword());
				getQuery.setString(6, vendor.getAddress());
				getQuery.setString(7, vendor.getRole());
				getQuery.executeUpdate();
				getQuery1 = Jdbc.getInstance().prepareStatement("insert into Vendor values(?,?,?,?)");
				getQuery1.setString(1,vendor.getUserName());
				getQuery1.setString(2,vendor.getCompanyName());
				getQuery1.setString(3,"NotVerified");
				getQuery1.setBlob(4,vendor.getFile());
				getQuery1.executeUpdate();
				Jdbc.getInstance().commit();
				System.out.println("successfully Registered");
				return true;
			
			} catch (SQLException e) {
		        if (Jdbc.getInstance() != null) {
		            try {
		                Jdbc.getInstance().rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                return false;
		            }
		        }
		        e.printStackTrace();
		        return false;
		    } 
	 }
	public static JSONArray showVendors()
	{
		
		 JSONArray jsonArray = new JSONArray();
		PreparedStatement ps;
		try {
			ps = Jdbc.getInstance().prepareStatement("select * from Vendor");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 String vendorID = rs.getString("vendorID");
		            String companyName = rs.getString("companyName");
		            String VerifyStatus = rs.getString("VerifyStatus");
		            Blob productFile = rs.getBlob("productFile");
		            JSONObject jsonObject = new JSONObject();
		            jsonObject.put("vendorID", vendorID);
		            jsonObject.put("companyName", companyName);
		            jsonObject.put("VerifyStatus", VerifyStatus);
		            if (productFile != null) {
		               byte[] bytes = productFile.getBytes(1, (int)productFile.length());
		               jsonObject.put("productFile", bytes);
		            }
		            jsonArray.put(jsonObject);
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return jsonArray;
		
		
	}
	public static boolean checkVendorStatus(String username)
	{
		boolean check=false;
		PreparedStatement ps;
		try {
		ps = Jdbc.getInstance().prepareStatement("select VerifyStatus from Vendor where vendorID=?");
		ps.setString(1, username);
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()) {
			if(resultSet.getString(1).equals("Verified"))
				check=true;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return check;	
		
	}
	 
	public static boolean approveVendorStatus(JSONObject updateData )
	{
		PreparedStatement ps;
		try {
		ps = Jdbc.getInstance().prepareStatement("UPDATE Vendor SET VerifyStatus = ? WHERE vendorID = ?");
		ps.setString(1, "Verified");
		ps.setString(2, updateData.getString("vendorId"));
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
		    
		    return true;
		} else {
		   
		    return false;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean canceVendor(String vendorId )
	{
		boolean check=false;
		PreparedStatement ps;
		try {
		ps = Jdbc.getInstance().prepareStatement("delete from  Vendor  where vendorID=?");
		ps.setString(1, vendorId);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return check;
			
	}
	
	
	
	
	
}
