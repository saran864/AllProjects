package skart.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import skart.DAO.AuthenticationDAO;
import skart.DAO.UserDAO;
import skart.DAO.VendorDAO;
import skart.model.Response;
import skart.model.SkartUser;

public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson =new Gson();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String jsonString = request.getReader().lines().collect(Collectors.joining());
		JSONObject json = new JSONObject(jsonString);
		System.out.println(json);
		String name = json.getString("name");
		int   age = Integer.parseInt(json.getString("age"));
		String username = json.getString("username");
		String password = json.getString("password");
		String role = json.getString("role");
		String  userotp= json.getString("userotp");
		String  address= json.getString("address");
		System.out.println(name);
		System.out.println(age);
		System.out.println(password);
		System.out.println(username);
		System.out.println(role);
		System.out.println(userotp);
		System.out.println(address);
		Cookie[] cookies = request.getCookies();
		String   otp = null;
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("otp")) {
		             otp= cookie.getValue();
		            break;
		        }
		    }
		}
		SkartUser skartuser=new SkartUser(name,age,username,password,address,role);
		  if (otp.equals(userotp)) {

			if (UserDAO.register(skartuser)) {
           
				String jsonResponse = gson.toJson(new Response("Success",skartuser.getUsername()));
				out.println(jsonResponse);
			}else {
				String jsonResponse = gson.toJson(new Response("Failiure", skartuser.getUsername()));
				out.println(jsonResponse);
			}
		}	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		if(AuthenticationDAO.credentialCheck(username, password)!=null){	
			JSONObject userData=AuthenticationDAO.credentialCheck(username, password);
			if(userData.getString("role").equals("vendor"))
			  {
				if(VendorDAO.checkVendorStatus(username))
				{
					out.println(userData);
				}else
				{
					String jsonResponse = gson.toJson(new Response("unAuthorized","Admin not verified your Profile"));
					out.println(jsonResponse);
				}
			}
			else
			{
				out.println(userData);
			}	
		} else {
			String jsonResponse = gson.toJson(new Response("Failure",username));
			out.println(jsonResponse);
		}

	}

}