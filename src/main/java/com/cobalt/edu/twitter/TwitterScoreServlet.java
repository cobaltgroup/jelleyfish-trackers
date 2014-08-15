package com.cobalt.edu.twitter;

// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
	

public class TwitterScoreServlet extends HttpServlet
{

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
   {
	   String searchTerm = request.getParameter("car");
	   InfoFromTwitter twitterObj = new InfoFromTwitter();
	   int score =  twitterObj.getScore(searchTerm);
	   String stringifiedScore = Integer.toString(score);
	   String json = new Gson().toJson(stringifiedScore);
	      response.setContentType("application/json");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write(json);
   }
   
}






