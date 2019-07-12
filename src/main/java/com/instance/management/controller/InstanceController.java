package com.instance.management.controller;

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
import com.instance.management.model.InstanceModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.system.RandomToken;

@Controller
@RequestMapping("/instancemanagement")
public class InstanceController {

	@Autowired
	InstanceReposetory instanceReposetory;

	@Autowired
	RandomToken randomToken;

	@GetMapping("")
	public String index(Model model, HttpServletResponse response, HttpSession session) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		model.addAttribute("page", "home");
		return "template";
	}

	@PostMapping("/getall")
	public @ResponseBody Object testusser(HttpServletResponse response, HttpSession session) throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		return instanceReposetory.findBytoken(session.getAttribute("token").toString());
	}
	
	@PostMapping("/getlist")
	public @ResponseBody Object testuser(HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			map.put("status", true);
			map.put("data", instanceReposetory.findBytoken(session.getAttribute("token").toString()));
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean occured");
			return map;
		}
		
	}

	@PostMapping("/add")
	public @ResponseBody Object addinstance(@RequestBody InstanceModel instanceModel, HttpServletResponse response,
			HttpSession session) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		InstanceMetaModel instanceMetaModel = new InstanceMetaModel();
		instanceMetaModel.setToken(session.getAttribute("token").toString());
		instanceMetaModel.setNameOfInstance(instanceModel.getNameOfInstance());
		instanceMetaModel.setSecurityCode(instanceModel.getSecurityCode());
		instanceMetaModel.setType(instanceModel.getType());
		instanceMetaModel.setInstToken(randomToken.getToken(10));
		instanceMetaModel.setClientkey(instanceModel.getClientkey());
		instanceMetaModel.setClientSecreat(instanceModel.getClientSecreat());
		instanceReposetory.save(instanceMetaModel);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", true);
		map.put("message", "Instance successfully created");
		return map;
	}

	@PostMapping("/findbyinsttoken")
	public @ResponseBody Object findbytoken(@RequestParam String token, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(token);
		if (instanceMetaModel != null) {
			map.put("status", true);
			map.put("response", instanceMetaModel);
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}

	@PostMapping("/deletebyid")
	public @ResponseBody Object delete(@RequestParam String token, HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(token);
		if (instanceMetaModel != null) {
			instanceReposetory.delete(instanceMetaModel);
			map.put("status", true);
			map.put("message", "Deleted successfully");
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}
	}

	@PostMapping("/updatebyid")
	public @ResponseBody Object update(@RequestBody InstanceMetaModel instanceMetaModel, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		InstanceMetaModel instanceMetaModel1 = instanceReposetory.findByinstToken(instanceMetaModel.getInstToken());
		if (instanceMetaModel1 != null) {
			instanceMetaModel1.setToken(session.getAttribute("token").toString());
			instanceMetaModel1.setInstToken(instanceMetaModel.getInstToken());
			instanceMetaModel1.setNameOfInstance(instanceMetaModel.getNameOfInstance());
			instanceMetaModel1.setSecurityCode(instanceMetaModel.getSecurityCode());
			instanceMetaModel1.setType(instanceMetaModel.getType());
			instanceMetaModel1.setClientkey(instanceMetaModel.getClientkey());
			instanceMetaModel1.setClientSecreat(instanceMetaModel.getClientSecreat());
			instanceReposetory.save(instanceMetaModel1);
			map.put("status", true);
			map.put("response", "Deleted successfully");
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}
}
