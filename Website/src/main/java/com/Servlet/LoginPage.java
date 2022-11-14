package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginPage() {
        super();
       
    }
    static String uname="saran864";
    static String pass="qaz@123%";
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		 
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		if(uname.equals(userName)&&pass.equals(password))
		{
			pw.println("Welcome "+userName);
			 
		}
		else 
		  
			pw.println("UserName or password Wrong!");
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();
		    pw.println(formatter.format(date)); 
	}
	 
	  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
