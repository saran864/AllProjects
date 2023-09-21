package skart.controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import skart.DAO.OrderDAO;
import skart.DAO.ProductDAO;
import skart.model.Products;
import skart.model.Response;
import skart.model.SkartUser;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			Gson gson = new Gson();
			String jsonString = request.getReader().lines().collect(Collectors.joining());
			Products productData = gson.fromJson(jsonString, Products.class);
			System.out.println(productData);
			ProductDAO.insertProducts(productData);

		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		if (ProductDAO.productsList()!=null) {
			out.println(ProductDAO.productsList());
		} else {
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(new Response("Failure", null));
			out.println(jsonResponse);
		}
		
		
	}
	
	
	
	

}
