package skart.controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import skart.DAO.AuthenticationDAO;
import skart.DAO.CartDAO;
import skart.DAO.OrderDAO;
import skart.DAO.ProductDAO;
import skart.model.Response;
import skart.model.SkartOrder;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class OrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	static OrderDAO orders = new OrderDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("appication/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		System.out.println(jsonString);
		SkartOrder registerData = gson.fromJson(jsonString, SkartOrder.class);
		  if(OrderDAO.placeOrder(registerData))
		  {
			  
				String jsonResponse = gson.toJson(new Response("Success", " Your Order Placed Successfully"));
				out.println(jsonResponse);
		  }
		
	
		 
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		String username = request.getParameter("username");
		if (OrderDAO.showOrders(username)!=null) {
			out.println(OrderDAO.showOrders(username));
		
		} else {
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(new Response("Failure", username));
			out.println(jsonResponse);
		}
	
			 
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Gson gson=new Gson();
		String customerId=request.getParameter("customerId");
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		System.out.println(customerId);
		System.out.println(orderId);
		if(OrderDAO.cancelOrder(customerId, orderId))
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
