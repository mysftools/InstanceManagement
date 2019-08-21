package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.ApexModel;
import com.instance.management.model.ProgressBarMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.ProgressBarReposetory;
import com.instance.management.service.ExecuteAnonymousService;
import com.instance.management.service.InstanceRunDetailsService;
import com.instance.management.service.SalesForceAuthService;

@Controller
@RequestMapping("/apex")
public class RunApexController {

	@Autowired
	ExecuteAnonymousService executeAnonymousService1;

	@Autowired
	SalesForceAuthService authService;

	@Autowired
	InstanceRunDetailsService instanceRunDetailsService;

	@Autowired
	InstanceReposetory instancerepo;

	@Autowired
	ProgressBarReposetory progressBarReposetory;

	Map<Long, Boolean> map1 = new HashMap<Long, Boolean>();

	@PostMapping("/runcode")
	@Async
	public @ResponseBody Object runapex(HttpServletResponse response, HttpSession session,
			@RequestBody ApexModel apexModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}

			ExecuteAnonymousService executeAnonymousService = new ExecuteAnonymousService(session, apexModel,
					instancerepo, authService, progressBarReposetory, instanceRunDetailsService, map1);
			Thread thread = new Thread(executeAnonymousService);
			thread.start();

			map.put("status", true);
			map.put("message", "code executed successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error accured during execution");
			return map;
		}
	}

	@PostMapping("/stop")
	public @ResponseBody Object stopthread(@RequestParam String instid) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			ProgressBarMetaModel progressBarMetaModel = progressBarReposetory.findByinsttoken(instid);
			if (progressBarMetaModel != null) {
				System.out.println(progressBarMetaModel.getThreadId());
				ExecuteAnonymousService.map1.put(progressBarMetaModel.getThreadId(), false);
				progressBarReposetory.delete(progressBarMetaModel);
				map.put("status", true);
				map.put("message", "Execution stoped successfully");
				return map;
			} else {
				map.put("status", true);
				map.put("message", "Na run found for this Instance");
				return map;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("status", false);
			map.put("message", "some error accured during execution");
			return map;
		}

	}

	@PostMapping("/getprogress")
	public @ResponseBody Object getprogress(@RequestParam String instancetoken) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ProgressBarMetaModel progressBarMetaModel = progressBarReposetory.findByinsttoken(instancetoken);
			if (progressBarMetaModel != null) {
				map.put("status", true);
				map.put("message", "Execution stoped successfully");
				map.put("response", progressBarMetaModel);
				return map;
			} else {
				map.put("code", 102);
				map.put("status", false);
				map.put("message", "Na run found for this Instance");
				return map;
			}
		} catch (Exception e) {
			map.put("code", 500);
			map.put("status", false);
			map.put("message", "some error accured during execution");
			return map;
		}

	}

}
