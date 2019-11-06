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
import com.instance.management.model.InstanceRunDetailsModel;
import com.instance.management.model.ProgressBarMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.ProgressBarReposetory;

@Service
public class ExecuteAnonymousService implements Runnable {

	SalesForceAuthService authService;

	InstanceReposetory instancerepo;

	ProgressBarReposetory progressBarReposetory;

	InstanceRunDetailsService instanceRunDetailsService;

	ApexModel apexModel = null;

	public static Map<Long, Boolean> map1;

	HttpSession session = null;

	public ExecuteAnonymousService() {

	}

	public ExecuteAnonymousService(HttpSession session, ApexModel apexModel, InstanceReposetory instancerepo,
			SalesForceAuthService authService, ProgressBarReposetory progressBarReposetory,
			InstanceRunDetailsService instanceRunDetailsService, Map<Long, Boolean> map) throws Exception {
		this.instancerepo = instancerepo;
		this.authService = authService;
		this.session = session;
		this.apexModel = apexModel;
		this.instanceRunDetailsService = instanceRunDetailsService;
		this.progressBarReposetory = progressBarReposetory;
		map1 = map;
	}

	@Override
	public void run() {
		try {
			map1.put(Thread.currentThread().getId(), true);
			InstanceMetaModel instanceMetaModel = instancerepo.findByinstToken(apexModel.getToken());
			Map<String, Object> responsemessage = authService.login(instanceMetaModel.getUsername(),
					instanceMetaModel.getPassword(), instanceMetaModel.getSecurityCode(),
					instanceMetaModel.getClientkey(), instanceMetaModel.getClientSecreat());
			if (Boolean.parseBoolean(responsemessage.get("status").toString())) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + responsemessage.get("access_token").toString());
				MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
						params, headers);
				InstanceRunDetailsModel instanceRunDetailsModel = new InstanceRunDetailsModel();
				instanceRunDetailsModel.setCode(apexModel.getCode());
				instanceRunDetailsModel.setNum(apexModel.getNum());
				instanceRunDetailsModel.setToken(instanceMetaModel.getInstToken());
				Map<String, Object> map = instanceRunDetailsService.add(instanceRunDetailsModel, session);
				String instdetailtoken = map.get("token").toString();
				float p = (float) (double) 100 / apexModel.getNum();
				float p1 = p;
				
				ProgressBarMetaModel progressBarMetaModel = new ProgressBarMetaModel();
				progressBarMetaModel.setInstrundtoken(instdetailtoken);
				progressBarMetaModel.setPercentage(p);
				progressBarMetaModel.setInsttoken(instanceMetaModel.getInstToken());
				progressBarMetaModel.setThreadId(Thread.currentThread().getId());
				progressBarReposetory.save(progressBarMetaModel);
				int r=1;
				for (int i = 1; i < apexModel.getNum(); i++) {
					ProgressBarMetaModel progressBarMetaModel1 = progressBarReposetory
							.findByinstrundtoken(instdetailtoken);
					if (map1.get(Thread.currentThread().getId()) &&progressBarMetaModel1!=null) {
						
						p = p + p1;
						r++;
						progressBarMetaModel1.setPercentage((int) p);
						progressBarReposetory.save(progressBarMetaModel1);
						RestTemplate restTemplate = new RestTemplate();
						ResponseEntity<AccountResponse> salesforceTestData = restTemplate.exchange(
								responsemessage.get("instance_url").toString() + "/services/data/v"
										+ instanceMetaModel.getApiversion() + "/tooling/executeAnonymous/?"
										+ "anonymousBody=" + apexModel.getCode(),
								HttpMethod.GET, request, AccountResponse.class);
						responsemessage.put("data", salesforceTestData.getBody());
					}else {
						break;
					}

				}
				map1.put(Thread.currentThread().getId(), false);
				instanceRunDetailsService.updatecalls(r,
						session);
				ProgressBarMetaModel progressBarMetaModel1 = progressBarReposetory.findByinstrundtoken(instdetailtoken);
				progressBarReposetory.delete(progressBarMetaModel1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
