package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String em = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","ankur1");
			
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from reg4 where email='"+em+"' and pass='"+pass+"'");
		
			out.println("....................................................................................................................................");
			out.printf("%20s %20s %20s %20s %20s %20s", "First Name","Last Name","E-Mail", "Contact", "Gender", "Registerd on");
			out.println();
			out.println("....................................................................................................................................");
			out.println();

			while (rs.next()) {

				out.printf("%20s %20s %20s %20s %20s %20s", rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(6), rs.getString(7));
				out.println();
			}
			out.println(".....................................................................................................................................");



		} catch (Exception e) {
			out.println(e);
		}
		
		
		
	}

}
