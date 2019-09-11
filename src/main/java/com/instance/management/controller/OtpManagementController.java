package com.instance.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.OtpModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.OtpRepository;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.service.OtpSendService;

@Controller
@RequestMapping("/otp")
public class OtpManagementController {

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	OtpSendService otpSendService;

	String error = null;

	@Autowired
	UserReposetory userrepo;

	@GetMapping("")
	public String index(@RequestParam String userid, Model model) throws Exception {
	
		model.addAttribute("email", userid);
		model.addAttribute("error", error);
	
		return "otp";
	}
	
	
	@PostMapping("/verify")
	public @ResponseBody String passwordchange(Model model, HttpServletResponse response,
			@RequestParam String username,@RequestParam String otp) throws Exception {

		int TEN_MINUTES = 15 * 60 * 1000;
		OtpModel otpModel = otpRepository.findBytempPassword(otp);
		if (otpModel != null) {
			UserMetaModel userMetaModel = userrepo.findByuserid(username);
			String s = userMetaModel.getUserid();
			try {
				long tenAgo = System.currentTimeMillis() - TEN_MINUTES;
				if (otpModel.getSendTime().getTime() < tenAgo) {
					error= "Otp has bean expired";
					response.sendRedirect("/otp?userid=" + username);
					return "otp";
				} else {
					if (otpModel.getUsername().equals(s)) {
						if (otpModel.getTempPassword().equals(otp)) {
							userMetaModel.setStatus(true);
							userMetaModel.setOtpstatus(true);
							userrepo.save(userMetaModel);
							otpRepository.delete(otpModel);
							error = "Your account has been activited";
							error=null;
							response.sendRedirect("/");
							return null;
						} else {
							error= "Otp did not match";
							response.sendRedirect("/otp?userid=" +username);
							return null;
						}
					} else {
						error= "Some error has bean occured please try again";
						response.sendRedirect("/otp?userid=" +username);
						return null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				error= "Otp did not match";
				response.sendRedirect("/otp?userid=" +username);
				return null;
			}
		} else {
			System.out.println("null");
			error= "Incorrect Otp";
			response.sendRedirect("/otp?userid=" +username);
			return "otp";
		}
	}

	@PostMapping("/resend")
	public String resendotp(Model model, @RequestBody Map<String, String> map) throws Exception {
		model.addAttribute("error", "Otp has been send to you mail");
		otpSendService.sendotp(map.get("userid"));
		return "otp";
	}

}
