package skart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import skart.controller.Jdbc;
import skart.model.SkartCart;

public class CartDAO {
	
	
	public static void insertCart(SkartCart cart) {
		try {
			int cardNo=0;
			PreparedStatement ps=Jdbc.getInstance().prepareStatement("insert into Mycart values(?,?,?)");
			ps.setInt(1, cardNo);
			ps.setString(2,cart.getCustomer_id());
			ps.setInt(3,cart.getProduct_id());
			ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		
				
		}
	}
	
	public static JSONArray showCart(String username) {
		JSONArray dataArray = new JSONArray();
		PreparedStatement ps;
		try {
			ps = Jdbc.getInstance().prepareStatement(
					" select m.prodId,p.productImageUrl,p.productName,p.productPrice,p.productCount from Products as p,Mycart as m where m.prodId=p.productId and m.CusId= ?");
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
		
			while(resultSet.next()) {
				JSONObject data = new JSONObject();
			data.put("product_id", resultSet.getInt(1));
			data.put("product_image", resultSet.getString(2));
			data.put("product_name", resultSet.getString(3));
			data.put("product_price", resultSet.getInt(4));
			data.put("quantity_limit", resultSet.getInt(5));
			data.put("quantity", 1);
			dataArray.put(data);
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return dataArray;

	}
	
	public static boolean checkCart(SkartCart cart)
	{
		boolean check=false;
		PreparedStatement getQuery;
		try {
			
			getQuery = Jdbc.getInstance().prepareStatement("select prodId from  Mycart where  cusId=?");
		       getQuery.setString(1,cart.getCustomer_id());
		       ResultSet rs=getQuery.executeQuery();
		       while(rs.next())
		       {  
		    	   if(cart.getProduct_id()==rs.getInt(1))
		    	   check=true;   
		       }
		      
		} catch (SQLException e) {
		    
			e.printStackTrace();
			
			
		}
		return check;
		
	}
	
	
	
	
	public static  boolean deleteFromProduct(String customer_id,int product_id)
	{
		boolean check=false;
	
	PreparedStatement getQuery;
	try {
		
		getQuery = Jdbc.getInstance().prepareStatement("delete from Mycart where prodId=? and cusId=?");
		getQuery.setInt(1,product_id);
		getQuery.setString(2,customer_id);
		getQuery.executeUpdate();
		 check=true;

	}catch(Exception e)
	{
		e.printStackTrace();
		check=false;
	}
	return check;
	
	}
}
