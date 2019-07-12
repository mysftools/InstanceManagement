package com.instance.management.controller;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.InstanceMetaModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.DirPath;

@Controller
@RequestMapping("/backup")
public class BackupController {

	@Autowired
	InstanceReposetory instanceReposetory;
	
	@Autowired
	UserReposetory userrepo;

	@GetMapping("")
	public String index(Model model, HttpServletResponse response, HttpSession session) throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		model.addAttribute("page", "backup");
		return "template";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/backupall")
	public @ResponseBody Object backall(HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			List<InstanceMetaModel> instanceMetaModels = instanceReposetory
					.findBytoken(session.getAttribute("token").toString());
			JSONArray array = new JSONArray();
			for (InstanceMetaModel instanceMetaModel : instanceMetaModels) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("securityCode", instanceMetaModel.getSecurityCode());
				jsonObject.put("type", instanceMetaModel.getType());
				jsonObject.put("token", instanceMetaModel.getToken());
				jsonObject.put("instToken", instanceMetaModel.getInstToken());
				jsonObject.put("nameOfInstance", instanceMetaModel.getNameOfInstance());
				jsonObject.put("clientkey", instanceMetaModel.getClientkey());
				jsonObject.put("clientSecreat", instanceMetaModel.getClientSecreat());
				array.add(jsonObject);
			}
			String s1 = DirPath.setPath();

			FileWriter file = new FileWriter(s1 + "/testa.json");
			file.write(array.toJSONString());
			file.flush();
			file.close();
			map = new HashMap<String, Object>();
			map.put("status", true);
			map.put("message", "Data back up successfully");

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Some error has bean occured");
			return map;
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/backupselected")
	public @ResponseBody Object backselected(@RequestBody Map<String, String[]> token, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}

			JSONArray array = new JSONArray();
			for (String string : token.get("token")) {
				InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(string);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("securityCode", instanceMetaModel.getSecurityCode());
				jsonObject.put("type", instanceMetaModel.getType());
				jsonObject.put("token", instanceMetaModel.getToken());
				jsonObject.put("instToken", instanceMetaModel.getInstToken());
				jsonObject.put("nameOfInstance", instanceMetaModel.getNameOfInstance());
				jsonObject.put("clientkey", instanceMetaModel.getClientkey());
				jsonObject.put("clientSecreat", instanceMetaModel.getClientSecreat());
				array.add(jsonObject);
			}
			String s1 = DirPath.setPath();
			FileWriter file = new FileWriter(s1 + "/tests.json");
			file.write(array.toJSONString());
			file.flush();
			file.close();
			map = new HashMap<String, Object>();
			map.put("status", true);
			map.put("message", "Data back up successfully");

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Some error has bean occured");
			return map;
		}
	}

	@PostMapping("/getinstinfo")
	public @ResponseBody Object getinstanceinfo(HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			UserMetaModel userMetaModel = userrepo.findBytoken(session.getAttribute("token").toString());
			List<InstanceMetaModel> instanceMetaModels = instanceReposetory
					.findBytoken(session.getAttribute("token").toString());
			map = new HashMap<String, Object>();
			map.put("status", true);
			map.put("message", "Data back up successfully");
			map.put("totalcalls", userMetaModel.getCalls());
			map.put("remainingcalls", userMetaModel.getRemainingCalls());
			map.put("totalinst", instanceMetaModels.size());
			return map;
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Some error has bean occured");
			return map;
		}
		
	}

}
