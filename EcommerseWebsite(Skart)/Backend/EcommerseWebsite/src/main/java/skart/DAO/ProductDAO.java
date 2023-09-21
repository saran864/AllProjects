package skart.DAO;

import skart.controller.*;
import skart.model.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductDAO {

	public static boolean insertProducts(Products product) {
		try {
			int productId = 0;
			PreparedStatement getQuery = Jdbc.getInstance()
					.prepareStatement("insert into Products values(?,?,?,?,?,?,?,?,?,?,?)");
			getQuery.setInt(1, productId);
			getQuery.setString(2, product.getProductImageUrl());
			getQuery.setString(3, product.getProductName());
			getQuery.setString(4, product.getProductDesc());
			getQuery.setInt(5, product.getProductPrice());
			getQuery.setInt(6, product.getProductCount());
			getQuery.setString(7, product.getVendorId());
			getQuery.setInt(8, 0);
			getQuery.setString(9, product.getProductCategory());
			getQuery.setInt(10, product.getOffer());
			getQuery.setInt(11, product.getProductCount());
			getQuery.executeUpdate();
			getQuery.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateProducts(JSONObject product) {
		try {
			String sql = "UPDATE Products SET productImageUrl = ?, productDecription = ?, prodcuctCategory = ?, productCount = ?, productName = ?, productPrice = ? ,totalQuantity = totalQuantity + ?,offer=? WHERE productId = ?";
			PreparedStatement statement = Jdbc.getInstance().prepareStatement(sql);
			statement.setString(1, product.getString("productImageUrl"));
			statement.setString(2, product.getString("productDesc"));
			statement.setString(3, product.getString("productCategory"));
			statement.setInt(4, product.getInt("productCount"));
			statement.setString(5, product.getString("productName"));
			statement.setInt(6, product.getInt("productPrice"));
			statement.setInt(7, product.getInt("productCount"));
			statement.setInt(8, product.getInt("offer"));
			statement.setInt(9, product.getInt("productNo"));

			statement.executeUpdate();
			statement.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static JSONArray productsList() {
		JSONArray jsonArray = new JSONArray();

		try {
			PreparedStatement stmt = Jdbc.getInstance().prepareStatement("select * from Products order by rand()");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("productNo", rs.getInt(1));
				jsonObject.put("productImageUrl", rs.getString(2));
				jsonObject.put("productName", rs.getString(3));
				jsonObject.put("productDesc", rs.getString(4));
				jsonObject.put("productCount", rs.getInt(6));
				jsonObject.put("vendorName", rs.getString(7));
				jsonObject.put("soldProduct", rs.getInt(8));
				jsonObject.put("productCategory", rs.getString(9));
				jsonObject.put("offer", rs.getInt(10));
				jsonObject.put("productPrice", ((100 - rs.getInt(10)) * rs.getInt(5)) / 100);
				if (rs.getInt(10) == 0) {
					jsonObject.put("productPrice", ((100 - rs.getInt(10)) * rs.getInt(5)) / 100);
					jsonObject.put("Price", "No Offers Available");
				} else {
					jsonObject.put("Price", rs.getInt(5));
				}

				jsonArray.put(jsonObject);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
		return jsonArray;
	}

	public static int getproductCost(int productId) {

		int price = 0;
		try {
			PreparedStatement stmt = Jdbc.getInstance()
					.prepareStatement("select  productPrice,offer from Products where productId=?");
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				price = (100 - rs.getInt(2)) * rs.getInt(1) / 100;

			}
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return price;

	}

	public static LinkedHashMap<String, Integer> topFiveProducts() {

		LinkedHashMap<String, Integer> topproducs = new LinkedHashMap<>();
		try {
			PreparedStatement ps = Jdbc.getInstance().prepareStatement(
					" select  productName,soldProduct from Products order by soldProduct  desc limit 5");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				topproducs.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(topproducs);
		return topproducs;

	}

	public static JSONArray vendorProductsList(String vendorId) {
		JSONArray jsonArray = new JSONArray();
		try {
			PreparedStatement stmt = Jdbc.getInstance().prepareStatement("select * from Products where vendorId=?");
			stmt.setString(1, vendorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("productNo", rs.getInt(1));
				jsonObject.put("productImageUrl", rs.getString(2));
				jsonObject.put("productName", rs.getString(3));
				jsonObject.put("productDesc", rs.getString(4));
				jsonObject.put("productPrice", rs.getInt(5));
				jsonObject.put("productCount", rs.getInt(6));
				jsonObject.put("soldProduct", rs.getInt(8));
				jsonObject.put("productCategory", rs.getString(9));
				jsonObject.put("offer", rs.getString(10));

				jsonArray.put(jsonObject);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
		return jsonArray;
	}

}
