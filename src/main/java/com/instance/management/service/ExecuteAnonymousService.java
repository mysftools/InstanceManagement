package com.instance.management.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.instance.management.model.AccountResponse;

@Service
public class ExecuteAnonymousService {
	
	@Autowired
	SalesForceAuthService authService;

	public Object execute(Map<String, Object> responsemessage) {
		try {

			if (Boolean.parseBoolean(responsemessage.get("status").toString())) {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + responsemessage.get("access_token").toString());
				MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
						params, headers);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<AccountResponse> salesforceTestData = restTemplate.exchange(
						responsemessage.get("instance_url").toString()
								+ "/services/data/v46.0/tooling/executeAnonymous/?" + "anonymousBody="
								+ responsemessage.get("code").toString(),
						HttpMethod.GET, request, AccountResponse.class);
				responsemessage.put("data", salesforceTestData.getBody());
				return responsemessage;
			} else {
				return responsemessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e;
		}

	}
}
