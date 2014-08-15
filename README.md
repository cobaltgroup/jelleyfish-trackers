jelleyfish-trackers
===================

jelleyfishtrackers.com is a mobile optimized application that tracks the popularity of cars on Twitter. 

Setup
-----
how to setup

This is a Maven project, which can be served on Tomcat. To configure the project and run:

1. Change all of the servlet mapping links in the DataTrans.js file to map the correct directory in your Tomcat application. (i.e. change http://jelleyfishtrackers.com:8080/... to http://yourIPAddress/warFileTitle:8080/...)

2. Create a mySQL database with the following tables and fields:
    MAKES
      -id
      -make_name
      -search_alias
    MODELS
      -id
      -make_id
      -madel_name

3. Create an app.properties file in the src/main/resources directory. This should include the url to a database, and all of the necessary Twitter Developer keys. For example:
  mysql_url = aaaa:bbbb://cccc/dddd
  consumer_key = ************************
  consumer_secret = ************************
  access_token = ************************
  access_token_secret = ************************

4. Create a war file and deploy it to Tomcat!
