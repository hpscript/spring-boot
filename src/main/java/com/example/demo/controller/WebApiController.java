package com.example.demo.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.apache.commons.text.StringEscapeUtils;


@RestController
@RequestMapping("/api")
public class WebApiController {
	
	@RequestMapping(value="weather/tokyo"
			, produces=MediaType.APPLICATION_JSON_VALUE
			, method=RequestMethod.GET)
	private String call() {
		RestTemplate rest = new RestTemplate();
		
		final String cityCode = "130010";
		final String endpoint = "http://weather.livedoor.com/forecast/webservice/json/v1";
	    
		final String url = endpoint + "?city=" + cityCode;
		
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		
		String json = response.getBody();
		
		return decode(json);
	}
	
	private static String decode(String string) {
		return StringEscapeUtils.unescapeJava(string);
	}
	
}