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

import com.instance.management.model.UserMetaModel;
import com.instance.management.model.UserModel;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.service.OtpSendService;
import com.instance.management.system.RandomToken;
import com.instance.management.system.TwoWayPasswordManagement;

@Controller
@RequestMapping("/invite")
public class InviteController {

	@Autowired
	TwoWayPasswordManagement twoWayPasswordManagement;

	@Autowired
	UserReposetory userrepo;

	@Autowired
	OtpSendService otpSendService;

	@Autowired
	RandomToken randomToken;

	@GetMapping("")
	public String index(Model model, HttpServletResponse response, HttpSession session) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		model.addAttribute("page", "invite");
		return "template";
	}

	@PostMapping("/saveuser")
	public @ResponseBody Object add(@RequestBody UserModel userModel, @RequestParam String url, HttpSession session,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			if (userModel.getPassword().equals(userModel.getRpassword())) {

				UserMetaModel userMetaModel = new UserMetaModel();
				userMetaModel.setUsername(userModel.getUsername());
				userMetaModel.setUserid(userModel.getUserid());
				userMetaModel.setListInst(userModel.getListInst());
				userMetaModel.setStatus(false);
				userMetaModel.setOtpstatus(false);
				userMetaModel.setPassword(userModel.getPassword());
				userMetaModel.setRole(userModel.getRole());
				userMetaModel.setCompanyId(session.getAttribute("company").toString());
				userMetaModel.setToken(randomToken.getToken(10));

				String t = twoWayPasswordManagement.encrypt(userMetaModel.getUserid());
				url = url + "/active?token=" + t;

				if (Boolean.parseBoolean(
						otpSendService.sendActivationlink(userMetaModel, url, session).get("status").toString())) {
					userrepo.save(userMetaModel);

					map.put("status", true);
					map.put("code", 200);
					map.put("message", "user added");
					return map;
				} else {
					map.put("status", false);
					map.put("code", 400);
					map.put("message", "user not added");
					return map;
				}

			} else {
				map.put("status", false);
				map.put("code", 400);
				map.put("message", "user not added");
				return map;
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("code", 400);
			map.put("message", "user not added");
			return map;
		}

	}

}
