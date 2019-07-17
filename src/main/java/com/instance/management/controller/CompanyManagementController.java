package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.CompanyMetaModel;
import com.instance.management.model.CompanyModel;
import com.instance.management.reposetory.CompanyReposetory;
import com.instance.management.system.RandomToken;

@Controller
@RequestMapping("/company")
public class CompanyManagementController {

	@Autowired
	CompanyReposetory companyReposetory;

	@Autowired
	RandomToken randomtoken;

	@PostMapping("/add")
	public @ResponseBody Object addcompany(@RequestBody CompanyModel companyModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CompanyMetaModel companyMetaModel = new CompanyMetaModel();
			companyMetaModel.setCompanyname(companyModel.getCompanyname());
			companyMetaModel.setToken(randomtoken.getToken(8));
			companyReposetory.save(companyMetaModel);
			map.put("status", true);
			map.put("message", "company added successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
	}
	
	@PostMapping("/getall")
	public @ResponseBody Object getall() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", true);
			map.put("response",companyReposetory.findAll());
			map.put("message", "company added successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
	}
}
