package com.instance.management.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.instance.management.model.ApexModel;
import com.instance.management.model.InstanceMetaModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.UserReposetory;

@Service
public class ExecuteAnonymousService implements Runnable {

	SalesForceAuthService authService;

	UserReposetory userrepo;

	InstanceReposetory instancerepo;

	ApexModel apexModel = null;

	HttpSession session = null;

	public ExecuteAnonymousService() {

	}

	public ExecuteAnonymousService(HttpSession session, ApexModel apexModel, UserReposetory userrepo,
			InstanceReposetory instancerepo, SalesForceAuthService authService) throws Exception {
		this.instancerepo=instancerepo;
		this.authService=authService;
		this.userrepo = userrepo;
		this.session = session;
		this.apexModel = apexModel;
	}

	@Override
	public void run() {
		try {
			runapex1(session, apexModel);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Object runapex(Map<String, Object> responsemessage) {
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

	public Object runapex1(HttpSession session, ApexModel apexModel) throws Exception {
		
		UserMetaModel userMetaModel = userrepo.findBytoken(session.getAttribute("token").toString());
		if (userMetaModel.getRemainingCalls() >= apexModel.getNum()) {
			InstanceMetaModel instanceMetaModel = instancerepo.findByinstToken(apexModel.getToken());
			Map<String, Object> responsemessage = authService.login(instanceMetaModel.getUsername(),
					instanceMetaModel.getPassword(), instanceMetaModel.getClientkey(),
					instanceMetaModel.getClientSecreat());
			if (Boolean.parseBoolean(responsemessage.get("status").toString())) {
				responsemessage.put("code", apexModel.getCode());
				return runapex(responsemessage);
			} else {
				return responsemessage;
			}
		} else {
			return false;
		}

	}

}
