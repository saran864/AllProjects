package skart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import skart.DAO.ProductDAO;
import skart.model.Products;
import skart.model.Response;

public class VendorProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("appication/json");
		PrintWriter out = response.getWriter();
		String vendorId = request.getParameter("vendorId");
		ProductDAO.vendorProductsList(vendorId);
		out.print(ProductDAO.vendorProductsList(vendorId));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    Gson gson = new Gson();
		    PrintWriter out=response.getWriter();
			String jsonString = request.getReader().lines().collect(Collectors.joining());
			Products productData = gson.fromJson(jsonString, Products.class);
			System.out.println(productData);
			if(ProductDAO.insertProducts(productData))
			{
				String jsonResponse = gson.toJson(new Response("Success", "successfully Product added"));
				out.println(jsonResponse);
			}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("appication/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		System.out.println(jsonString);
		
		JSONObject updadedData=new JSONObject(jsonString);
		if(ProductDAO.updateProducts(updadedData))
		{
			String jsonResponse = gson.toJson(new Response("Success", "Product Updated Successfully"));
			out.println(jsonResponse);
			
		}
		
	

	}

}
