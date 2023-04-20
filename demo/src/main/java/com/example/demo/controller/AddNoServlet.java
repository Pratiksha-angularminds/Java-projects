package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddNoServlet")
public class AddNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AddNoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Entered into Servlet.");
		String number1=request.getParameter("no1");
		String number2=request.getParameter("no2");
		
		
		int no1=Integer.parseInt(number1);
		int no2=Integer.parseInt(number2);
		
		int sum=no1+no2;
		
		String result=String.valueOf(sum);
		
		request.setAttribute("result",result);
		RequestDispatcher rd=request.getRequestDispatcher("/views/add.jsp");
		System.out.println(no1 + no2);
		rd.forward(request, response);
		
		response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<h1>hello</h1>");
	  
	}

}
