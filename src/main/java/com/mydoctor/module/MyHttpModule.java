package com.mydoctor.module;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;



public class MyHttpModule {
	
	private HttpClient client;
	private HttpGet request;
	private JSONObject jsonObject;
	
	public JSONObject requestToServerUsingGetJSON(String query) throws ClientProtocolException, IOException{
		
		
		this.client = HttpClientBuilder.create().build();
		request = new HttpGet(query.toString());
		request.addHeader("accept", "application/json");
		HttpResponse response;
		String json ="";
		System.out.println("request : " + query.toString());
		response = client.execute(request);
		json = IOUtils.toString(response.getEntity().getContent());
		
		System.out.println("response : " + json);
		
		this.jsonObject = new JSONObject(json);
		return jsonObject;
	
		
		
	}


}
