package com.cobalt.edu.twitter;

// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

	

public class DbModelServlet extends HttpServlet
{

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
   {
	   String makeName = request.getParameter("make");
	   InfoFromDB database= new InfoFromDB();
	   ArrayList<String> modelNames=database.getModelNames(makeName);
	   InfoFromTwitter twitterObj = new InfoFromTwitter();
	   ArrayList<String> scores=new ArrayList<String>();
	   
	   for (int j=0;j<modelNames.size();j++){
	   int i =  twitterObj.getScore(modelNames.get(j));
	   scores.add(modelNames.get(j));
	   scores.add(Integer.toString(i));
	   }
	   	  String json = new Gson().toJson(scores);
	      response.setContentType("application/json");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write(json);
   }
   
}

