package com.example.demo.hello;

import java.util.ArrayList;

public class modelHello {
	ArrayList<String> stringArray = new ArrayList<>();
	
	public modelHello(String message){
		stringArray.add(message);
	}
	
	public String getMessage(){
		String tempString = "";
		for (int i = 0; i <stringArray.size(); i++) {
			tempString += stringArray.get(i) + ", \n";
		}
		return tempString;
	}
	
	public void addString(String s) {
		stringArray.add(s);
	}
}
