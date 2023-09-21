package skart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import skart.controller.Jdbc;
import skart.model.DeliveryAddress;
import skart.model.SkartOrder;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderDAO {
	static int orderId = 0;

	public static boolean  placeOrder(SkartOrder skorder) {
		
		try {
			PreparedStatement getQuery = Jdbc.getInstance().prepareStatement("insert into customerOrders values(?,?,?,?,?,?)");
			getQuery.setInt(1, orderId);
			getQuery.setString(2, skorder.getCustomer_id());
			getQuery.setInt(3, skorder.getProduct_id());
			getQuery.setInt(4, skorder.getQuantity());
			getQuery.setString(5, skorder.getOrder_date());
			getQuery.setInt(6, skorder.getQuantity()*ProductDAO.getproductCost(skorder.getProduct_id()));	
			getQuery.executeUpdate();
			PreparedStatement getQuery2 = Jdbc.getInstance()
					.prepareStatement("update  Products set  productCount=productCount-" + skorder.getQuantity()+",soldProduct=soldProduct+" + skorder.getQuantity() + " where productId=?");
			getQuery2.setInt(1, skorder.getProduct_id());
			getQuery2.executeUpdate();
			getQuery.close();
	        return true;

		} catch (Exception e) {
			e.printStackTrace();
		
			System.out.println("something went wrong");
			return false;
		}
		
	}
	
	
	
	public static boolean  placeOrder(JSONObject skorder) {
		
		try {
		    PreparedStatement getQuery = Jdbc.getInstance().prepareStatement("insert into customerOrders values(?,?,?,?,?,?)");
		    JSONArray cartItems = skorder.getJSONArray("cartItem");
		    
		    for (int i = 0; i < cartItems.length(); i++) {
		        JSONObject cartItem = cartItems.getJSONObject(i);

		        getQuery.setInt(1, orderId);
		        getQuery.setString(2, skorder.getString("customer_id"));
		        getQuery.setInt(3, cartItem.getInt("product_id"));
		        getQuery.setInt(4, cartItem.getInt("quantity"));
		        getQuery.setString(5, skorder.getString("order_date"));
		        getQuery.setInt(6, cartItem.getInt("quantity") * ProductDAO.getproductCost(cartItem.getInt("product_id")));

		     
		        getQuery.executeUpdate();

		    
		        PreparedStatement getQuery2 = Jdbc.getInstance().prepareStatement("update Products set productCount = productCount - ?, soldProduct = soldProduct + ? where productId = ?");
		        getQuery2.setInt(1, cartItem.getInt("quantity"));
		        getQuery2.setInt(2, cartItem.getInt("quantity"));
		        getQuery2.setInt(3, cartItem.getInt("product_id"));
		        getQuery2.executeUpdate();
		       System.out.println("order placed successully");
		    }

		    getQuery.close();
		    return true;

		} catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("Something went wrong");
		    return false;
		}
		
	}

	public static void deliveyAddress(DeliveryAddress delivery) {
		try {
			System.out.println("delivery");
			PreparedStatement getQuery = Jdbc.getInstance()
					.prepareStatement("insert into DeliveryAddress values(?,?,?,?,?,?)");
			getQuery.setInt(1, orderId);
			getQuery.setString(2, delivery.getStreetAddress());
			getQuery.setString(3, delivery.getTown_city());
			getQuery.setString(4, delivery.getState_country());
			getQuery.setInt(5, delivery.getPostcode());
			getQuery.setLong(6, delivery.getmobno());
			getQuery.executeUpdate();
			getQuery.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static JSONArray showOrders(String id) {
		String query = null;
		JSONArray jsonArray = new JSONArray();
			query = "select  concat( 'skart_',LPAD(orderId, 3, 0)) as  orderId,p.productImageUrl,p.productName,p.productCount,c.Quantity,c.totalcost,c.orderDate,c.productId from Products as p,customerOrders as c where c.productId=p.productId and c.customerId=?";
		try {

			PreparedStatement ps = Jdbc.getInstance().prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("OrderId", rs.getString(1));
				jsonObject.put("productImageUrl", rs.getString(2));
				jsonObject.put("productName", rs.getString(3));
				jsonObject.put("Quanity", rs.getInt(5));
				jsonObject.put("totalCost", rs.getInt(6));
				jsonObject.put("orderDate", rs.getString(7));
				jsonObject.put("productId", rs.getString(8));
				jsonArray.put(jsonObject);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return jsonArray;

	}
	
	public static  boolean cancelOrder(String customer_id,int order_id)
	{
		boolean check=false;
	    int quantity=0;
	    int productId = 0;

	try {
		PreparedStatement getQuery1 = Jdbc.getInstance().prepareStatement("select quantity,productId from customerOrders where orderId=? and customerId=?");
		getQuery1.setInt(1,order_id);
		getQuery1.setString(2,customer_id);
		ResultSet rs =  getQuery1.executeQuery();
		while (rs.next()) {
			quantity=rs.getInt(1);
			productId=rs.getInt(2);
		}
		PreparedStatement getQuery2 = Jdbc.getInstance().prepareStatement("delete from customerOrders where orderId=? and customerId=?");
		getQuery2.setInt(1,order_id);
		getQuery2.setString(2,customer_id);
		getQuery2.executeUpdate();
		PreparedStatement getQuery3 = Jdbc.getInstance()
				.prepareStatement("update  Products set  productCount=productCount+" + quantity+",soldProduct=soldProduct-" +quantity + " where productId=?");
		getQuery3.setInt(1, productId);
		getQuery3.executeUpdate();
		 check=true;

	}catch(Exception e)
	{
		e.printStackTrace();
		check=false;
	}
	return check;
	
	}
	
}
