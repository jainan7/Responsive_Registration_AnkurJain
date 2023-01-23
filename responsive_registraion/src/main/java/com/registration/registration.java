package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String fn = request.getParameter("fname");
		String ln = request.getParameter("lname");
		String em = request.getParameter("email");
		String ph = request.getParameter("phone");
		String pass = request.getParameter("pass");
		String gen = request.getParameter("gender");
		java.util.Date udate = new java.util.Date();
		long l = udate.getTime();
		java.sql.Date sqldate = new java.sql.Date(l);

		LocalDateTime now = LocalDateTime.now();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ankur1");

			PreparedStatement ps = con.prepareStatement("insert into reg4 values(?,?,?,?,?,?,?)");

			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setString(3, em);
			ps.setString(4, ph);
			ps.setString(5, pass);
			ps.setString(6, gen);
			ps.setDate(7, sqldate);

			int a = ps.executeUpdate();
			if (a > 0) {
				RequestDispatcher rd= request.getRequestDispatcher("success.html");
				rd.include(request, response);
			}

		} catch (Exception e) {

			out.println(e);

		}

	}

}
