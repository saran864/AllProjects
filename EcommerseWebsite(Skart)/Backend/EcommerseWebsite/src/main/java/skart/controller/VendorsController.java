package skart.controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import skart.DAO.CartDAO;
import skart.DAO.ProductDAO;
import skart.DAO.VendorDAO;
import skart.model.Response;
import skart.model.Vendor;

public class VendorsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    PrintWriter out=response.getWriter();
		    Gson gson=new Gson();
		    InputStream productPdf = null;
		    Vendor vendor=null;
		 
		    System.out.println(request.getParameter("name"));
		    if (request.getPart("pdfFile") != null) {
		      productPdf = request.getPart("pdfFile").getInputStream();
		      vendor=new Vendor(request.getParameter("name"),request.getParameter("companyname"),Integer.parseInt(request.getParameter("age")),request.getParameter("username"),request.getParameter("password"),request.getParameter("address"),request.getParameter("role"),productPdf);
		  Verfication vf=new Verfication();
	    if(VendorDAO.insertVendors(vendor))
		 { 
	    	vf.vendorsEmailVerification(vendor);
		   String jsonResponse = gson.toJson(new Response("Success", request.getParameter("name")));
		   out.println(jsonResponse);
		 }
		  else {
			  String jsonResponse = gson.toJson(new Response("Failure", request.getParameter("name"))); 
				 out.println(jsonResponse);
				
		  }
		    }
	}
		
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		if (VendorDAO.showVendors() != null) {
			out.println(VendorDAO.showVendors());

		} else {

			String jsonResponse = gson.toJson(new Response("failure", null));
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
		
		if(VendorDAO.approveVendorStatus(updadedData))
		{  if(Verfication.vendorMail(updadedData))
		{
			String jsonResponse = gson.toJson(new Response("Success", "Vendor  Approved Successfully"));
			out.println(jsonResponse);
		}
			
		}
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Gson gson=new Gson();
		
		String vendorId=request.getParameter("vendorId");
		String mailText=request.getParameter("mailText");
		
	JSONObject json=new JSONObject();
	json.put("mailText", mailText);
	json.put("vendorId", vendorId);
	
		if(VendorDAO.canceVendor(vendorId))
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

