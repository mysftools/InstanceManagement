package com.instance.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import com.instance.management.model.InstanceUpdateModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.InstanceRunDetailsReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.RandomToken;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Controller
@RequestMapping("/instancemanagement")
public class InstanceController {

	@Autowired
	UserReposetory userrepo;

	@Autowired
	InstanceReposetory instanceReposetory;

	@Autowired
	InstanceRunDetailsReposetory instanceRunDetailsReposetory;

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
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		if (session.getAttribute("role").equals("admin")) {

			map.put("role", session.getAttribute("role"));
			map.put("status", true);
			map.put("response", instanceReposetory.findBytoken(session.getAttribute("token").toString()));
			return map;
		} else {
			UserMetaModel usermodel = userrepo.findBytoken(session.getAttribute("token").toString());
			List<InstanceMetaModel> instanceMetaModels = new ArrayList<InstanceMetaModel>();
			String list = usermodel.getListInst();
			for (String instid : list.split(",")) {
				instanceMetaModels.add(instanceReposetory.findByinstToken(instid));
			}
			map.put("role", session.getAttribute("role"));
			map.put("status", true);
			map.put("response", instanceMetaModels);
			return map;
		}

	}

	@PostMapping("/getlist")
	public @ResponseBody Object testuser(HttpServletResponse response, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
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
		instanceMetaModel.setPassword(instanceModel.getPassword());
		instanceMetaModel.setUsername(instanceModel.getUsername());
		instanceMetaModel.setNameOfInstance(instanceModel.getNameOfInstance());
		instanceMetaModel.setSecurityCode(instanceModel.getSecurityCode());
		instanceMetaModel.setSandbox(Boolean.parseBoolean(instanceModel.getType()));
		instanceMetaModel.setInstToken(randomToken.getToken(10));
		instanceMetaModel.setClientkey(instanceModel.getClientkey());
		instanceMetaModel.setCoustomerName(instanceModel.getCoustomerName());
		instanceMetaModel.setClientSecreat(instanceModel.getClientSecreat());
		instanceMetaModel.setApiversion(instanceModel.getApiversion());
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

		instanceRunDetailsReposetory.deleteAll(instanceRunDetailsReposetory.findByinstTokenOrderByDateDesc(token));
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
	public @ResponseBody Object update(@RequestBody InstanceUpdateModel instanceUpdateModel,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(instanceUpdateModel.getInstToken());
		if (instanceMetaModel != null) {
			instanceMetaModel.setToken(session.getAttribute("token").toString());
			instanceMetaModel.setSandbox(Boolean.parseBoolean(instanceUpdateModel.getType()));
			instanceMetaModel.setInstToken(instanceUpdateModel.getInstToken());
			instanceMetaModel.setPassword(instanceUpdateModel.getPassword());
			instanceMetaModel.setUsername(instanceUpdateModel.getUsername());
			instanceMetaModel.setCoustomerName(instanceUpdateModel.getCoustomerName());
			instanceMetaModel.setNameOfInstance(instanceUpdateModel.getNameOfInstance());
			instanceMetaModel.setSecurityCode(instanceUpdateModel.getSecurityCode());
			instanceMetaModel.setClientkey(instanceUpdateModel.getClientkey());
			instanceMetaModel.setClientSecreat(instanceUpdateModel.getClientSecreat());
			instanceMetaModel.setApiversion(instanceUpdateModel.getApiversion());
			instanceReposetory.save(instanceMetaModel);
			map.put("status", true);
			map.put("response", "Deleted successfully");
			return map;
		} else {

			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}

	@PostMapping("/ordercreat")
	public @ResponseBody Object testorder(@RequestParam int amount) {
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_8sJa3x6gA2CVoj", "BJIKGCUHxsQ6cCL59JO03MiJ");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "rcptid #5");
			orderRequest.put("payment_capture", true);

			Order orders = razorpay.Orders.create(orderRequest);

			return orders.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
