package com.instance.management.controller;

import java.util.Date;

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

import com.instance.management.model.InstanceRunDetailsModel;
import com.instance.management.reposetory.InstanceRunDetailsReposetory;

@Controller
@RequestMapping("/instancedetails")
public class InstanceRunDetailsController {

	@Autowired
	InstanceRunDetailsReposetory instanceRunDetailsReposetory;

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

	/*
	 * @GetMapping("/ins") public @ResponseBody Object adddata() {
	 * 
	 * InstanceRunDetailsModel runDetailsModel=new InstanceRunDetailsModel();
	 * runDetailsModel.setInstToken("DCP85SMPX8");
	 * runDetailsModel.setInstname("test"); runDetailsModel.setDate(new
	 * Date(System.currentTimeMillis())); runDetailsModel.setNoOfCalls(5);
	 * runDetailsModel.setStript("demo");
	 * instanceRunDetailsReposetory.save(runDetailsModel);
	 * 
	 * return "sd"; }
	 */

}
