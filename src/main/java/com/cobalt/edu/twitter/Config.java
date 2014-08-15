package com.cobalt.edu.twitter;

import java.io.InputStream;
import java.util.Properties;


public class Config {
	
	private static Properties properties;
	
	static {
		loadProp();
	}
	
	private static void loadProp() {
		try{
		properties = new Properties();
		InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties");
		properties.load(in);
		in.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getDatabaseUrl() {
		return (String) properties.get("mysql_url");
	}
	public String getConsumerKey(){
		return (String) properties.get("consumer_key");
	}
	public String getConsumerSecret(){
		return (String) properties.get("consumer_secret");
	}
	public String getAccessToken(){
		return (String) properties.get("access_token");
	}
	public String getAccessTokenSecret(){
		return (String) properties.get("access_token_secret");
	}
	
}
	

