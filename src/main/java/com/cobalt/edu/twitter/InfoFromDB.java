package com.cobalt.edu.twitter;

import java.util.ArrayList;

public class InfoFromDB {
	private DatabaseClient dbClient;
	
	public InfoFromDB(){
		dbClient=new DatabaseClient();
	}
	
	public ArrayList<String> getMakeNames(){
		ArrayList<String> makeNames=dbClient.selectCol("SELECT * FROM MAKES", "make_name");
		return makeNames;
	}
	
	public ArrayList<String> getModelNames(String makeName){
		String query="SELECT * FROM MODELS WHERE make_id=(SELECT id FROM MAKES WHERE make_name = '"+makeName+"')";
		ArrayList<String> modelNames=dbClient.selectCol(query, "model_name");
		return modelNames;
	}
	
	public ArrayList<String> getSearchAliases(){
		ArrayList<String> searchAliases=dbClient.selectCol("SELECT * FROM MAKES", "search_alias");
		return searchAliases;
	}
	
	public void insertCar(String make, String searchName){
		ArrayList<String> takenIdsString=dbClient.selectCol("SELECT * FROM MAKES","id");
		ArrayList<Integer> takenIds= new ArrayList<Integer>();
		for(String id:takenIdsString){
			takenIds.add((Integer)Integer.parseInt(id));
		}
		ArrayList<Integer> possibleIds= new ArrayList<Integer>();
		for(int i =0;i<=takenIds.size();i++){
			possibleIds.add((Integer)i);
		}
		possibleIds.removeAll(takenIds);
			
		String insertable="INSERT INTO MAKES (make_name, id, search_alias) VALUES ('"+make+"','"+possibleIds.get(0).toString()+"','"+searchName+"')";
		dbClient.doQuery(insertable);
	}
	
	public void deleteCar(String name){
		String deletable="DELETE FROM MAKES WHERE make_name='"+name+"'";
		dbClient.doQuery(deletable);
	}
}
