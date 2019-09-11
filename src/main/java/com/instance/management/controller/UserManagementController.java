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

import com.instance.management.model.ChangepwdModel;
import com.instance.management.model.CompanyMetaModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.model.UserModel;
import com.instance.management.model.UserUpdateModel;
import com.instance.management.reposetory.CompanyReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.service.OtpSendService;
import com.instance.management.system.RandomToken;

@Controller
@RequestMapping("/usermanagement")
public class UserManagementController {

	@Autowired
	UserReposetory userrepo;
	
	@Autowired
	CompanyReposetory companyReposetory;

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
		model.addAttribute("page", "userprofile");
		return "template";
	}

	@PostMapping("/saveuser")
	public @ResponseBody Object add(@RequestBody UserModel userModel, HttpSession session, Model model)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		if (userModel.getPassword().equals(userModel.getRpassword())) {
			
			
			CompanyMetaModel companyMetaModel=companyReposetory.findBycompanyname(userModel.getCompanyId().toUpperCase());
			
			if (companyMetaModel==null) {
				companyMetaModel=new CompanyMetaModel();
				companyMetaModel.setCompanyname(userModel.getCompanyId().toUpperCase());
				companyMetaModel.setToken(randomToken.getToken(10));
				companyMetaModel.setTotalruns(0);
				companyMetaModel.setRemainingruns(0);
			}
			
			
			
			UserMetaModel userMetaModel = new UserMetaModel();
			userMetaModel.setUsername(userModel.getUsername());
			userMetaModel.setUserid(userModel.getUserid());
			userMetaModel.setStatus(false);
			userMetaModel.setOtpstatus(false);
			userMetaModel.setPassword(userModel.getPassword());
			userMetaModel.setRole(userModel.getRole());
			userMetaModel.setCompanyId(companyMetaModel.getToken());
			userMetaModel.setToken(randomToken.getToken(10));
			if (Boolean.parseBoolean(otpSendService.sendotp(userModel.getUserid()).get("status").toString())) {
				userrepo.save(userMetaModel);
				companyReposetory.save(companyMetaModel);
				model.addAttribute("email", userModel.getUserid());
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

	}

	@PostMapping("/getuserbyid")
	public @ResponseBody Object getcrrentuser(HttpServletResponse response, HttpSession session) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		UserMetaModel userMetaModel = userrepo.findBytoken(session.getAttribute("token").toString());
		if (userMetaModel != null) {
			map.put("status", true);
			map.put("response", userMetaModel);
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}

	@PostMapping("/getbyid")
	public @ResponseBody Object getuser(@RequestParam String token, HttpServletResponse response, HttpSession session)
			throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		UserMetaModel userMetaModel = userrepo.findBytoken(token);
		if (userMetaModel != null) {
			map.put("status", true);
			map.put("response", userMetaModel);
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}

	@PostMapping("/adminuser")
	public @ResponseBody Object getalladminusers(HttpSession session, HttpServletResponse response) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		return userrepo.findByCompanyIdAndRole(session.getAttribute("company").toString(), "developer");
	}

	@PostMapping("/update")
	public @ResponseBody Object update(@RequestBody UserUpdateModel updateModel, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		UserMetaModel userMetaModel = userrepo.findBytoken(updateModel.getToken());
		if (userMetaModel != null) {
			userMetaModel.setUserid(updateModel.getUserid());
			userMetaModel.setUsername(updateModel.getUsername());
			userMetaModel.setListInst(updateModel.getListInst());
			userrepo.save(userMetaModel);
			map.put("status", true);
			map.put("response", userMetaModel);
			map.put("message", "User Updated successfully");
			return map;
		} else {
			map.put("status", false);
			map.put("message", "no data found");
			return map;
		}

	}

	@PostMapping("/changepassword")
	public @ResponseBody Object changepassword(@RequestBody ChangepwdModel changepwdModel, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		UserMetaModel userMetaModel = userrepo.findBytoken(session.getAttribute("token").toString());
		if (userMetaModel.getPassword().equals(changepwdModel.getPassword())) {
			if (changepwdModel.getNewpassword().equals(changepwdModel.getConfirmpassword())) {
				userMetaModel.setPassword(changepwdModel.getNewpassword());
				userrepo.save(userMetaModel);
				map.put("status", true);
				map.put("response", userMetaModel);
				return map;
			} else {

				map.put("status", false);
				map.put("response", "Password mis match");
				return map;
			}

		} else {
			map.put("status", false);
			map.put("message", "you entered old password");
			return map;
		}

	}

	@PostMapping("/forgetpassword")
	public @ResponseBody Object forgetpassword(@RequestBody Map<String, String> email) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return otpSendService.sendpass(email.get("email"));
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "something went wrong");
			return map;
		}

	}

	@PostMapping("/upstatus")
	@ResponseBody
	public Object UpStatus(@RequestParam String token, UserUpdateModel model, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			UserMetaModel usermodel = userrepo.findBytoken(token);
			usermodel.setStatus(!usermodel.isStatus());
			usermodel.setOtpstatus(!usermodel.isOtpstatus());
			userrepo.save(usermodel);
			map.put("status", true);
			map.put("message", "status Updated successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean accured");
			return map;
		}
	}

	@PostMapping("/deletebyid")
	@ResponseBody
	public Object deleteUserById(@RequestParam String token, HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}

			UserMetaModel usermodel = userrepo.findBytoken(token);
			userrepo.delete(usermodel);
			map.put("status", true);
			map.put("message", "User deleted successfully");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "some error has bean accured");
			return map;
		}
	}

}
