package com.cobalt.edu.twitter;

// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DbEditorServlet extends HttpServlet
{

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
   {
	   String searchTerm = request.getParameter("car");
	   String requestType= request.getParameter("type");
	   InfoFromDB db= new InfoFromDB();
	   if(requestType.equals("delete"))
	   {
		   db.deleteCar(searchTerm);
	   }
	   else if (requestType.equals("insert"))
	   {
		   String alias=request.getParameter("searchName");
		   db.insertCar(searchTerm,alias);
	   }
   }
   
}

