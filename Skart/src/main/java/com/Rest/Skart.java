package com.Rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/Skart")
public class Skart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Skart() {
		super();

	}

	static SkartController sc = new SkartController();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		int a = Integer.parseInt(age);
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		String adds = request.getParameter("address");

		SkartUser userRegister = new SkartUser(name, a, uname, pwd, adds);
		sc.register(userRegister);
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);

		/*
		 * pw.print(new Gson().toJson(p)); pw.print("Welcome");
		 */

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		SkartUser login = new SkartUser(uname, pwd);

		Connection c;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Skart", "root", "password");
			PreparedStatement getQuery = c
					.prepareStatement("select Username from Register where Username=? and Password=?");
			getQuery.setString(1, login.getUsername());
			getQuery.setString(2, login.getPassword());
			ResultSet rs = getQuery.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("Welcom.jsp");
				rd.forward(request, response);
			} else {
				pw.println("<font color=red size=18>LoginFailed !!<font><br>");
				pw.println("<a href=Login.jsp>Try Again !!<a>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
