package skart.controller;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import skart.model.Response;
import skart.DAO.UserDAO;

public class UserController extends HttpServlet {

		private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		if (UserDAO.showUsers()!=null) {
			out.println(UserDAO.showUsers());
			
		}
		else {
			out.println(UserDAO.showUsers());
			String jsonResponse = gson.toJson(new Response("failure", null));
			out.println(jsonResponse);
		}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
    	response.setContentType("application/json; charset=UTF-8");
    	  Gson gson=new Gson();
    	  PrintWriter out =response.getWriter();
    		response.setContentType("application/json; charset=UTF-8");
    		String jsonString = request.getReader().lines().collect(Collectors.joining());
    		JSONObject json = new JSONObject(jsonString);
          String username = json.getString("username");
			Verfication vf = new Verfication();
			String otp = vf.getRandomNumberString();
			vf.emailVerification(username, otp);
			 response.setContentType("application/json");
			String jsonResponse = gson.toJson(new Response( "Success" ,username));
		   Cookie userCookie = new Cookie("otp", otp);
		   userCookie.setMaxAge(60);
		  response.addCookie(userCookie);
		  System.out.println(otp);
			out.println(jsonResponse);
			
			

	}

}
