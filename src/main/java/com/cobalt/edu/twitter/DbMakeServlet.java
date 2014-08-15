package com.cobalt.edu.twitter;

// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

	

public class DbMakeServlet extends HttpServlet
{

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException
   {
	   InfoFromDB dbObject= new InfoFromDB();
	   ArrayList<String> makeNames=dbObject.getMakeNames();
	   InfoFromTwitter twitterObj = new InfoFromTwitter();
	   ArrayList<String> tweetText=new ArrayList<String>();
	   ArrayList<String> searchAliases=dbObject.getSearchAliases();
	   
	   for (int j=0;j<makeNames.size();j++){
		   int score =  twitterObj.getScore(searchAliases.get(j));
		   tweetText.add(makeNames.get(j));
		   tweetText.add(Integer.toString(score));
	   }
	   	  String jsonObj = new Gson().toJson(tweetText);
	      response.setContentType("application/json");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write(jsonObj);
   }
   
}

