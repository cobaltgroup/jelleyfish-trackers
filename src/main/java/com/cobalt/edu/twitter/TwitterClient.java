package com.cobalt.edu.twitter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

	public ArrayList<String> getAllInfoFromTwitter(String searchTerm) {
		try {
			ConfigurationBuilder configBuilder = getConfigFromTwitter();
			QueryResult queryResult = createQueryToTwitter(searchTerm, configBuilder);
			return getListOfTweetInfo(queryResult);
		} catch (TwitterException ex) {
			return null;
		}
	}
	public ArrayList<String> getTweets(String searchTerm) {
		try {
			ConfigurationBuilder configBuilder = getConfigFromTwitter();
			QueryResult queryResult = createQueryToTwitter(searchTerm, configBuilder);
			return getTweetStrings(queryResult);
		} catch (TwitterException ex) {
			return null;
		}
	}

	public int getScoreFromTwitter(String searchTerm) {
		try {
			ConfigurationBuilder configBuilder = getConfigFromTwitter();
			QueryResult result = createQueryToTwitter(searchTerm, configBuilder);
			int score = getTotalRetweets(result);
			return score;
		}
		catch (TwitterException e) {
			return -1;
		}
	}

	private int getTotalRetweets(QueryResult result) {
		List<Status> tweets = result.getTweets();
		int score = 0;
		for (int i = 0; i < tweets.size(); i++) {
			if (tweets.get(i) == null)
				i = tweets.size();
			else if (tweets.get(i).getRetweetCount() < 1) {
				score++;
			} else {
				score += tweets.get(i).getRetweetCount();
			}
		}
		return score;
	}

	private ArrayList<String> getListOfTweetInfo(QueryResult result) {
		ArrayList<String> tweetText = new ArrayList<String>();
		List<Status> tweets = result.getTweets();
		for (int i = 0; i < tweets.size(); i++) {
			if (tweets.get(i) == null)
				i = tweets.size();
			else {
				tweetText.add(tweets.get(i).getText());
				tweetText.add("Retweets: " + tweets.get(i).getRetweetCount());
				tweetText.add("Favorites: " + tweets.get(i).getFavoriteCount());
				tweetText.add("Username: "
						+ tweets.get(i).getUser().getScreenName());
			}
		}
		return tweetText;
	}
	private ArrayList<String> getTweetStrings(QueryResult result) {
		ArrayList<String> tweetText = new ArrayList<String>();
		List<Status> tweets = result.getTweets();
		for (int i = 0; i < tweets.size(); i++) {
			if (tweets.get(i) == null)
				i = tweets.size();
			else {
				tweetText.add(tweets.get(i).getText());
			}
		}
		return tweetText;
	}
	private QueryResult createQueryToTwitter(String searchTerm,
			ConfigurationBuilder configBuilder) throws TwitterException {
		TwitterFactory twitterFactory = new TwitterFactory(configBuilder.build());
		Twitter twitter = twitterFactory.getInstance();
		Query query = new Query(searchTerm);
		addNewQuery(query, searchTerm);
		QueryResult result = twitter.search(query);
		return result;
	}

	private ConfigurationBuilder getConfigFromTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Config config= new Config();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(config.getConsumerKey())
				.setOAuthConsumerSecret(
						config.getConsumerSecret())
				.setOAuthAccessToken(
						config.getAccessToken())
				.setOAuthAccessTokenSecret(
						config.getAccessTokenSecret());
		return cb;
	}

	private void addNewQuery(Query query, String searchTerm) {
		setGeoLocation(query);
		query.setCount(100);
		query.setLang("en");
		setSinceDate(query);
		query.query(searchTerm);
	}

	private void setGeoLocation(Query query) {
		GeoLocation loc = new GeoLocation(47.6097, -122.3331);
		query.setGeoCode(loc, 500.0, Query.Unit.mi);
	}

	private void setSinceDate(Query query) {
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) - 1;
		if (dayOfMonth == 0) {
			month--;
			if (month == 2)
				dayOfMonth = 28;
			else if (month == 9 || month == 4 || month == 6 || month == 11)
				dayOfMonth = 30;
			else
				dayOfMonth = 31;
		}
		query.setSince(year + "-" + month + "-" + dayOfMonth);
	}

}
