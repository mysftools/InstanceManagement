package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.ApexModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.service.ExecuteAnonymousService;
import com.instance.management.service.SalesForceAuthService;

@Controller
@RequestMapping("/apex")
public class RunApexController {

	@Autowired
	ExecuteAnonymousService executeAnonymousService1;

	@Autowired
	UserReposetory userrepo;

	@Autowired
	SalesForceAuthService authService;

	@Autowired
	InstanceReposetory instancerepo;

	@PostMapping("/runcode")
	public @ResponseBody Object runapex(HttpServletResponse response, HttpSession session,
			@RequestBody ApexModel apexModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			ExecuteAnonymousService executeAnonymousService = new ExecuteAnonymousService(session, apexModel, userrepo,
					instancerepo, authService);
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

}
