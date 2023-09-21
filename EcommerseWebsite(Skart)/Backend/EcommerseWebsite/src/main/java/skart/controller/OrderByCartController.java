package skart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import skart.DAO.OrderDAO;
import skart.model.Response;


public  class OrderByCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("appication/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		JSONObject json=new JSONObject(jsonString);
	System.out.println(json);
		System.out.println(json.getString("customer_id"));
		System.out.println(json.getString("order_date"));
		JSONArray jsona= json.getJSONArray("cartItem");
		System.out.println(jsona);
		   if(OrderDAO.placeOrder(json)) {
			   String jsonResponse = gson.toJson(new Response("Success", " Your Order Placed Successfully"));
				out.println(jsonResponse);
		   }
		
	

		
	}

}
