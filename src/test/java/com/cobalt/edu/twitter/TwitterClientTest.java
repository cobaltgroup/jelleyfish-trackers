package com.cobalt.edu.twitter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TwitterClientTest {

	@Test
	public void testGetTweetsReturnNotEmptyList() {
		TwitterClient twitterClient = new TwitterClient();
		List<String> tweets = twitterClient.getAllInfoFromTwitter("summer");
		Assert.assertNotNull(tweets);
		Assert.assertNotEquals(0, tweets.size());
	}

	@Test
	public void testIfSearchTermInTweet() {
		TwitterClient twitterClient = new TwitterClient();
		List<String> tweets = twitterClient.getTweets("summer");
		Assert.assertNotNull(tweets);
		int count=0;
		for(String st: tweets){
			if(!(st.toLowerCase().contains("summer")))
				count++;
		}
		boolean closeTo100=count<=100 && count > 90;
		Assert.assertFalse(closeTo100);
	}

	@Test
	public void testGetTweetsReturnDifferentTweetsForDifferentCalls() {
		TwitterClient twitterClient = new TwitterClient();
		TwitterClient twitterClient1 = new TwitterClient();
		List<String> tweets = twitterClient.getAllInfoFromTwitter("summer");
		List<String> tweets1 = twitterClient1.getAllInfoFromTwitter("cars");
		String tweet1OfTester = tweets.get(0);
		String tweet1OfTester2 = tweets1.get(0);
		Assert.assertNotEquals(tweet1OfTester2, tweet1OfTester);
	}

	@Test
	public void testGetScoreReturnsNotZero() {
		TwitterClient twitterClient = new TwitterClient();
		int score = twitterClient.getScoreFromTwitter("summer");
		Assert.assertNotEquals(0, score);
	}

	@Test
	public void testScoreNotCalculatedTheSame() {
		TwitterClient twitterClient = new TwitterClient();
		TwitterClient twitterClient1 = new TwitterClient();
		int score = twitterClient.getScoreFromTwitter("summer");
		int score1 = twitterClient1.getScoreFromTwitter("cars");
		Assert.assertNotEquals(score1, score);
	}

}
