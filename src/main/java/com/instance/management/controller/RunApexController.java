package com.instance.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.ApexModel;
import com.instance.management.model.InstanceMetaModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.service.ExecuteAnonymousService;
import com.instance.management.service.SalesForceAuthService;

@Controller
@RequestMapping("/apex")
public class RunApexController {

	@Autowired
	SalesForceAuthService authService;

	@Autowired
	UserReposetory userrepo;

	@Autowired
	InstanceReposetory instancerepo;

	@Autowired
	ExecuteAnonymousService executeAnonymousService;

	@PostMapping("/runcode")
	public @ResponseBody Object runapex(HttpServletResponse response, HttpSession session,
			@RequestBody ApexModel apexModel) throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		Thread.sleep(1000);
		System.out.println("sd");
		UserMetaModel userMetaModel = userrepo.findBytoken(session.getAttribute("token").toString());
		if (userMetaModel.getRemainingCalls() >= apexModel.getNum()) {
			int n = userMetaModel.getRemainingCalls() - 1;
			userMetaModel.setRemainingCalls(n);
			InstanceMetaModel instanceMetaModel = instancerepo.findByinstToken(apexModel.getToken());
			Map<String, Object> responsemessage = authService.login(userMetaModel.getUsername(),
					userMetaModel.getPassword(), instanceMetaModel.getClientkey(),
					instanceMetaModel.getClientSecreat());
			if (Boolean.parseBoolean(responsemessage.get("status").toString())) {
				responsemessage.put("code", apexModel.getCode());
				userrepo.save(userMetaModel);
				return executeAnonymousService.execute(responsemessage);
			} else {
				return responsemessage;
			}
		} else {
			return false;
		}

	}

}
