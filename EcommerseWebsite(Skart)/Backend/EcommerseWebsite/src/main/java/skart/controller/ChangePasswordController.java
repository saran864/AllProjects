package skart.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import skart.model.Response;

public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("appication/json");
		String username = request.getParameter("username");
		String userotp = request.getParameter("otp");
		System.out.println(username + " " + userotp);
		Gson gson = new Gson();
		Cookie[] cookies = request.getCookies();
		String otp = null;
		try {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("otp")) {
					otp = cookie.getValue();
					break;
				}
		}}
		System.out.println(otp);
		if (otp.equals(userotp)) {
			String jsonResponse = gson.toJson(new Response("Success", username));
			out.println(jsonResponse);
			System.out.println(jsonResponse);
		} else {
			String jsonResponse = gson.toJson(new Response("Failure", username));
			out.println(jsonResponse);
			System.out.println(jsonResponse);
		}
	}catch(NullPointerException e) {
		
		String jsonResponse = gson.toJson(new Response("Session timeout", username));
		out.println(jsonResponse);
	}

	}
}
