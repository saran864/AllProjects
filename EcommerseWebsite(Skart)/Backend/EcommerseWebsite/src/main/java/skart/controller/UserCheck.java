package skart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import skart.DAO.UserDAO;
import skart.model.Response;


@WebServlet("/UserCheck")
public class UserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	   
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		  Gson gson=new Gson();
    	  PrintWriter out =response.getWriter();
    		response.setContentType("application/json; charset=UTF-8");
    		String jsonString = request.getReader().lines().collect(Collectors.joining());
    		JSONObject json = new JSONObject(jsonString);
          String username = json.getString("username");
        
		if (UserDAO.validMail(username)) {
			String jsonResponse = gson.toJson(new Response("Failure",username));
			out.println(jsonResponse);
		} else {
			 response.setContentType("application/json");
			String jsonResponse = gson.toJson(new Response( "Success" ,username));
	
			out.println(jsonResponse);
			
			}

	}
}
