package com.instance.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@PostMapping("")
	public String index(@RequestParam String username, Model model) throws Exception {

		model.addAttribute("email", username);
		error = null;
		model.addAttribute("error", error);
		return "otp";
	}

	@PostMapping("/verify")
	public @ResponseBody String passwordchange(Model model, HttpServletResponse response,@RequestBody Map<String, String> map) throws Exception {

		int TEN_MINUTES = 15 * 60 * 1000;
		OtpModel otpModel = otpRepository.findBytempPassword(map.get("otp"));
		UserMetaModel userMetaModel = userrepo.findByusername(map.get("username"));
		String s = userMetaModel.getUsername();
		try {
			long tenAgo = System.currentTimeMillis() - TEN_MINUTES;
			if (otpModel.getSendTime().getTime() < tenAgo) {
				model.addAttribute("error", "Otp has bean expired");
				return "otp";
			} else {
				if (otpModel.getUsername().equals(s)) {
					if (otpModel.getTempPassword().equals(map.get("otp"))) {
						userMetaModel.setStatus(true);
						userMetaModel.setOtpstatus(true);
						userrepo.save(userMetaModel);
						otpRepository.delete(otpModel);
						model.addAttribute("error", error);
						error = "Your account has been blocked";
						response.sendRedirect("/");
						return null;
					} else {
						model.addAttribute("error", "Otp did not match");
						response.sendRedirect("/otp");
						return null;
					}
				} else {
					model.addAttribute("error", "Some error has bean occured please try again");
					response.sendRedirect("/otp");
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "Otp did not match");
			response.sendRedirect("/otp");
			return null;
		}

	}

	@PostMapping("/resend")
	public String resendotp(Model model, @RequestBody Map<String, String> map) throws Exception {
		model.addAttribute("error", "Otp has been send to you mail");
		otpSendService.sendotp(map.get("username"));
		return "otp";
	}

}
