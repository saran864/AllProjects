package skart.controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import skart.DAO.CartDAO;
import skart.model.Response;
import skart.model.SkartCart;

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
static Gson gson = new Gson();
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		SkartCart cartData = gson.fromJson(jsonString, SkartCart.class);
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		if(CartDAO.checkCart(cartData))
		{
			String jsonResponse = gson.toJson(new Response("Failure", "This Product Already in your cart"));
			out.println(jsonResponse);
		}
		else
		{
			 CartDAO.insertCart(cartData );
			String jsonResponse = gson.toJson(new Response("Success", "Succssfully added to your cart"));
			out.println(jsonResponse);
			 
		}
	  

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		String username = request.getParameter("username");
		
		if (CartDAO.showCart(username) != null) {
			out.println(CartDAO.showCart(username));
			//String jsonResponse = gson.toJson(new Response("success", null));
			//out.println(jsonResponse);

		} else {

			String jsonResponse = gson.toJson(new Response("failure", null));
			out.println(jsonResponse);
		}

	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String customerId=request.getParameter("customer_id");
		int productId=Integer.parseInt(request.getParameter("product_id"));
	
		if(CartDAO.deleteFromProduct(customerId,productId))
		{
			String jsonResponse = gson.toJson(new Response("success", "Successfully removed"));
			out.println(jsonResponse);
		}
	else{
			String jsonResponse = gson.toJson(new Response("Failed", "Something went wrong"));
			out.println(jsonResponse);
		}
			
		}
	
	
	

}
