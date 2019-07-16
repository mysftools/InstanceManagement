package com.instance.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.InstanceMetaModel;
import com.instance.management.model.InstanceRunDetailsMetaModel;
import com.instance.management.model.InstanceRunDetailsModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.InstanceRunDetailsReposetory;
import com.instance.management.system.RandomToken;

@Controller
@RequestMapping("/instancedetails")
public class InstanceRunDetailsController {

	@Autowired
	InstanceRunDetailsReposetory instanceRunDetailsReposetory;

	@Autowired
	InstanceReposetory instanceReposetory;

	@Autowired
	RandomToken randomToken;

	@GetMapping("")
	public String index(@RequestParam String token, Model model, HttpServletResponse response, HttpSession session)
			throws Exception {
		/*
		 * if (!LoginController.userValidate(session)) { response.sendRedirect("/");
		 * return null; }
		 */

		model.addAttribute("page", "instancesetails");
		model.addAttribute("token", token);
		return "template";
	}

	@PostMapping("/get")
	public @ResponseBody Object getinstdata(@RequestParam String token, HttpServletResponse response,
			HttpSession session) throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		return instanceRunDetailsReposetory.findByinstToken(token);
	}

	@PostMapping("/add")
	public @ResponseBody Object adddata(@RequestBody InstanceRunDetailsModel runDetailsModel,
			HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(runDetailsModel.getToken());
			InstanceRunDetailsMetaModel runDetailsMetaModel = new InstanceRunDetailsMetaModel();
			runDetailsMetaModel.setInstToken(instanceMetaModel.getInstToken());
			runDetailsMetaModel.setInstname(instanceMetaModel.getNameOfInstance());
			runDetailsMetaModel.setDate(new Date(System.currentTimeMillis()));
			runDetailsMetaModel.setNoOfCalls(runDetailsModel.getNum());
			runDetailsMetaModel.setStript(runDetailsModel.getCode());
			runDetailsMetaModel.setDetailToken(randomToken.getToken(8));
			instanceRunDetailsReposetory.save(runDetailsMetaModel);
			map.put("status", true);
			map.put("message", "data saved successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
	}

	@PostMapping("/getbyid")
	public @ResponseBody Object getbyid(@RequestParam String token, HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			InstanceRunDetailsMetaModel runDetailsMetaModel = instanceRunDetailsReposetory.findBydetailToken(token);
			map.put("status", true);
			map.put("message", "data saved successfully");
			map.put("respons", runDetailsMetaModel);
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
		
	}
}
