package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.ApexModel;
import com.instance.management.service.ExecuteAnonymousService;

@Controller
@RequestMapping("/apex")
public class RunApexController {

	@PostMapping("/runcode")
	public @ResponseBody Object runapex(HttpServletResponse response, HttpSession session,
			@RequestBody ApexModel apexModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}

			ExecuteAnonymousService executeAnonymousService = new ExecuteAnonymousService(session, apexModel);
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
