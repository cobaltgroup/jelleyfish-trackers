package com.cobalt.edu.twitter;

// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

public class TwitterSearchServlet extends HttpServlet
{

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException 
	{
	   String searchTerm = request.getParameter("car"); 
	   InfoFromTwitter twitterObj = new InfoFromTwitter();
      ArrayList<String> tweetList = twitterObj.getFullReport(searchTerm);
      PrintWriter out = response.getWriter();
      String json = new Gson().toJson(tweetList);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.write(json);
   }
   
}