package com.util;

import java.text.SimpleDateFormat;

public class DateFormat {
	final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,M,d");
	
	private DateFormat(){
		
	}
	
	public String format(Object obj){
		
		return dateFormat.format(obj);
		
	}

}
