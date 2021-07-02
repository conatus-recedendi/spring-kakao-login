package com.conatus.myapp.kakao_login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ApiController {
	@RequestMapping(value = "/login/{access_token}", method= RequestMethod.GET)
	public String requestRestApi(@PathVariable String access_token) throws Exception {
		URL url = new URL("https://kapi.kakao.com/v2/user/me");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Authorization", "Bearer " + access_token);
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			content.append(inputLine);
		in.close();
		con.disconnect();

		/*
		"https://kapi.kakao.com/v2/user/me" \
  -H "Authorization: Bearer {ACCESS_TOKEN}" \
  -d 'property_keys=["kakao_account.email"]'
  */

		// TODO: only return the accont_email. 
		return (content.toString());
	}
	
	@RequestMapping(value="/login/{access_token}", method=RequestMethod.POST)
	public String requestLogIn(@PathVariable String access_token) {
		return ("none");
	}

	@RequestMapping(value="/login/{access_token}", method=RequestMethod.DELETE)
	public String requestLogOut(@PathVariable String access_token) {
		return ("none");
	}

	@RequestMapping(value="/user/{id}", method= RequestMethod.GET)
	public String greeting()
	{
		return ("index");
	}


}