package com.instance.management.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instance.management.model.CompanyMetaModel;
import com.instance.management.model.InstanceMetaModel;
import com.instance.management.model.InstanceRunDetailsMetaModel;
import com.instance.management.model.InstanceRunDetailsModel;
import com.instance.management.reposetory.CompanyReposetory;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.InstanceRunDetailsReposetory;
import com.instance.management.system.RandomToken;

@Service
public class InstanceRunDetailsService {

	@Autowired
	CompanyReposetory companyReposetory;

	@Autowired
	InstanceReposetory instanceReposetory;

	@Autowired
	InstanceRunDetailsReposetory instanceRunDetailsReposetory;

	@Autowired
	RandomToken randomToken;

	public Map<String, Object> add(InstanceRunDetailsModel runDetailsModel, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(runDetailsModel.getToken());
		InstanceRunDetailsMetaModel runDetailsMetaModel = new InstanceRunDetailsMetaModel();
		runDetailsMetaModel.setInstToken(instanceMetaModel.getInstToken());
		runDetailsMetaModel.setUserToken(session.getAttribute("token").toString());
		runDetailsMetaModel.setInstname(instanceMetaModel.getNameOfInstance());
		runDetailsMetaModel.setDate(new Date(System.currentTimeMillis()));
		runDetailsMetaModel.setNoOfCalls(runDetailsModel.getNum());
		runDetailsMetaModel.setStript(runDetailsModel.getCode());
		runDetailsMetaModel.setDetailToken(randomToken.getToken(8));
		instanceRunDetailsReposetory.save(runDetailsMetaModel);
		map.put("token", runDetailsMetaModel.getDetailToken());
		map.put("status", true);
		map.put("message", "data saved successfully");
		return map;
	}

	public Object updatecalls(int calls, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			CompanyMetaModel companyMetaModel = companyReposetory
					.findBytoken(session.getAttribute("company").toString());
			int i = companyMetaModel.getRemainingruns() - calls;
			companyMetaModel.setRemainingruns(i);
			companyReposetory.save(companyMetaModel);
			map.put("status", true);
			map.put("message", "Runs Updated successfully");
			return map;
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
	}

	

}
