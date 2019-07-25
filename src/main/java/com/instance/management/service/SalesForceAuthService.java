package com.instance.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class SalesForceAuthService {

	protected static final String loginHost = "https://login.salesforce.com";

	public Map<String, Object> login(String username, String password, String empID, String secret) {
		Map<String, Object> responsemessage = new HashMap<String, Object>();
		try {
			String baseUrl = loginHost + "/services/oauth2/token";
			HttpPost oauthPost = new HttpPost(baseUrl);
			List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
			parametersBody.add(new BasicNameValuePair("grant_type", "password"));
			parametersBody.add(new BasicNameValuePair("username", username));
			parametersBody.add(new BasicNameValuePair("password", password));
			parametersBody.add(new BasicNameValuePair("client_id", empID));
			parametersBody.add(new BasicNameValuePair("client_secret", secret));
			oauthPost.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
			CloseableHttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(oauthPost);
			String res = EntityUtils.toString(response.getEntity());
			JSONParser jsonParser = new JSONParser();
			JSONObject jo = (JSONObject) jsonParser.parse(res);
			responsemessage.put("access_token", jo.get("access_token").toString());
			responsemessage.put("instance_url", jo.get("instance_url").toString());
			responsemessage.put("status", true);
			return responsemessage;
		} catch (Exception e) {
			e.printStackTrace();
			responsemessage.put("status", false);
			responsemessage.put("error_message", e.getMessage());
			return responsemessage;
		}
	}
}
