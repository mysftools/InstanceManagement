package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			CompanyMetaModel companyMetaModel1 = companyReposetory.findBycompanyname(companyModel.getCompanyname());
			if (companyMetaModel1 == null) {
				CompanyMetaModel companyMetaModel = new CompanyMetaModel();
				companyMetaModel.setCompanyname(companyModel.getCompanyname());
				companyMetaModel.setToken(randomtoken.getToken(8));
				companyReposetory.save(companyMetaModel);
				map.put("status", true);
				map.put("message", "company added successfully");
				return map;
			} else {
				map.put("status", true);
				map.put("message", "company added successfully");
				return map;
			}

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
			map.put("response", companyReposetory.findAll());
			map.put("message", "company added successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
	}

	@PostMapping("/purchase")
	public @ResponseBody Object updatecompany(@RequestParam int calls, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			CompanyMetaModel companyMetaModel = companyReposetory
					.findBytoken(session.getAttribute("company").toString());
			companyMetaModel.setTotalruns(calls);
			companyMetaModel.setRemainingruns(calls);
			companyReposetory.save(companyMetaModel);
			map.put("status", true);
			map.put("message", "User Updated successfully");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}

	}

	@GetMapping("/getruns")
	public @ResponseBody Object getRuns(HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}

			CompanyMetaModel companyMetaModel = companyReposetory
					.findBytoken(session.getAttribute("company").toString());

			map = new HashMap<String, Object>();
			map.put("status", true);
			map.put("message", "Data back up successfully");
			map.put("remainingcalls", companyMetaModel.getRemainingruns());
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Some error has bean occured");
			return map;
		}
	}
}
