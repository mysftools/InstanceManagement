package com.instance.management.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.TwoWayPasswordManagement;

@Controller
@RequestMapping("/active")
public class AccountActivationController {

	@Autowired
	TwoWayPasswordManagement twoWayPasswordManagement;

	@Autowired
	UserReposetory userrepo;

	String error = null;

	@GetMapping("")
	public String loginpage(@RequestParam String token, Model model) throws Exception {
		if (token.contains("-")) {
			String mail = twoWayPasswordManagement.decrypt(token);
			UserMetaModel metaModel = userrepo.findByuserid(mail);
			String temp = error;
			error = null;
			model.addAttribute("email", mail);
			model.addAttribute("error", temp);
			if (metaModel == null) {
				return "login";
			} else {
				metaModel.setOtpstatus(true);
				metaModel.setStatus(true);
				userrepo.save(metaModel);
				return "activeSetPassword";
			}
		} else {
			String temp = error;
			error = null;
			model.addAttribute("email", token);
			model.addAttribute("error", temp);
			return "activeSetPassword";
		}

	}

	@PostMapping(value = "/setpasssword")
	public @ResponseBody Object setpassword(@RequestParam String userid, @RequestParam String password,
			@RequestParam String cpassword, HttpServletResponse response)throws Exception {
		try {
			
			UserMetaModel metaModel = userrepo.findByuserid(userid);
			if (metaModel==null) {
				error = "Acount Not found";
				response.sendRedirect("/active?token=" + userid);
				return null;
			}else {
				if (!password.equals(cpassword)) {
					System.out.println(password+"   "+cpassword);
					error = "Passwor and confirm Password did not match";
					response.sendRedirect("/active?token=" + userid);
					return null;
				}else {
					metaModel.setPassword(password);
					userrepo.save(metaModel);
					response.sendRedirect("/");
					return null;
				}
			}
			
		} catch (Exception e) {
			error = "Something Went wrong";
			response.sendRedirect("/active?token=" + userid);
			return null;
		}
		
	}
}
