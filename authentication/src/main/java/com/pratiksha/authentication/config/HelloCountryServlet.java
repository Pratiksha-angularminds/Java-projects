package com.pratiksha.authentication.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/country/*", loadOnStartup = 1)
public class HelloCountryServlet extends HttpServlet   
{
    private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("hi1------------------");
	    doGet(request,response);
	}
        
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		System.out.println("hi==============");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
	        out.println("<h3>Hello India!</h3>");	
	}
}