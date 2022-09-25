package com.example.demo;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EtrCodeExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtrCodeExerciseApplication.class, args);
		
		
	}
	/**
	 * This method access the interchanges.json file to extract each location on the highway,
	 * and their distance from the beginning of the highway. Saving this data in a hashmap for 
	 * efficient retrieval.
	 * @return HashMap containing locations as key and their distance from the beginning of
	 * the highway as value;
	 */
	@Bean 
	   public Map<String, Double> distanceMap(){
	      Map<String, Double> distanceMap = new HashMap<String, Double>();
	      
	      JSONParser parser = new JSONParser();
			double distanceFromRoot = 0.0;
		    try {
		    	FileReader fr = new FileReader("interchanges.json");
		    	
		    	Object obj = parser.parse(fr);
		    	JSONObject jsonObject = (JSONObject) obj;
		    	JSONObject locations = (JSONObject) jsonObject.get("locations");
		    	JSONObject location = null;
		    	JSONArray routesArray = null;
		    	JSONObject route = null;
		    	List<String> myList = new ArrayList<String>();
		    	myList.addAll(locations.keySet());
		    	List<Integer> intList = new ArrayList<Integer>();
		        for(String s : myList){
		            intList.add(Integer.valueOf(s));
		        }
		        Collections.sort(intList);
		    	
		    	for (Integer integer : intList) {
		    		location = (JSONObject) locations.get("" + integer);
		    		distanceMap.put(location.get("name").toString(), distanceFromRoot);
		    		routesArray = (JSONArray) location.get("routes");
		    		route = (JSONObject) routesArray.get(0);
		    		
		    		distanceFromRoot += Double.parseDouble(route.get("distance").toString());
		    	}
		    	fr.close();
		    }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	
	        }
		    
	      return distanceMap;      
	   }

}
