package com.example.Requests;

import java.util.ArrayList;

public class RequestModel {
	ArrayList<String> stringArray = new ArrayList<>();
	ArrayList<Integer> integerArray = new ArrayList<>();
	
	public RequestModel(String message, Integer n){
		stringArray.add(message);
		integerArray.add(n);
	}
	
	public String getMessage(){
		String tempString = "";
		for (int i = 0; i <stringArray.size(); i++) {
			tempString += stringArray.get(i) + ", \n";
		}
		tempString += "\n \n \n ";
		for (int i = 0; i <integerArray.size(); i++) {
			tempString += integerArray.get(i) + ", \n";
		}
		return tempString;
	}
	
	public void addString(String s) {
		stringArray.add(s);
	}	
	
	public void addInteger(Integer n) {
		integerArray.add(n);
	}	
}
