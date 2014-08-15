package com.cobalt.edu.twitter;

import java.util.ArrayList;

public class InfoFromTwitter
{
    private TwitterClient twitterClient;
    

    public InfoFromTwitter()
    {
        twitterClient = new TwitterClient();
    }
    
    
    public ArrayList<String> getFullReport(String searchTerm)
    {
    	ArrayList<String> tweets = twitterClient.getAllInfoFromTwitter(searchTerm);
    	return tweets;
    }
    

    public int getScore(String searchTerm)
    {
        int score= twitterClient.getScoreFromTwitter(searchTerm);
        return score;
    }

}
