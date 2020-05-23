package com.app.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServicesApplication{

	public static void main(String[] args) { 
		SpringApplication.run(ServicesApplication.class, args);
	}
	
	public static String GetMessageOfException(Exception e){
    	return String.format("%s %s Line:%d",e.toString(), e.getStackTrace()[0].getFileName().toString(),e.getStackTrace()[0].getLineNumber());
    }

}
