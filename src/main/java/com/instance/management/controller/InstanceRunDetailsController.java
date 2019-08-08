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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.InstanceRunDetailsMetaModel;
import com.instance.management.reposetory.InstanceRunDetailsReposetory;

@Controller
@RequestMapping("/instancedetails")
public class InstanceRunDetailsController {

	@Autowired
	InstanceRunDetailsReposetory instanceRunDetailsReposetory;

	@GetMapping("")
	public String index(@RequestParam String token, Model model, HttpServletResponse response, HttpSession session)
			throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

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

		return instanceRunDetailsReposetory.findByinstTokenOrderByDateDesc(token);
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
