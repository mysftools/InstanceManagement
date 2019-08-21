package com.instance.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.UserReposetory;

@Controller
@RequestMapping("/")
public class AurthController {

	String error = null;

	@Autowired
	UserReposetory userrepo;

	@GetMapping("")
	public ModelAndView loginpage(HttpSession session, HttpServletResponse response) throws Exception {

		String temp = error;
		error = null;

		return new ModelAndView("login", "error", temp);
	}

	@PostMapping("/")
	public ModelAndView loginpage1(HttpSession session, HttpServletResponse response) throws Exception {

		String temp = error;
		error = null;

		return new ModelAndView("login", "error", temp);
	}

	@PostMapping(value = "/authenticate")
	public @ResponseBody Object auth(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			@RequestParam String username, @RequestParam String password) throws Exception {
		UserMetaModel usermodel = userrepo.findByuserid(username);
		try {

			if (usermodel.getUserid().equals(username) && usermodel.getPassword().equals(password)
					&& usermodel.isStatus() && usermodel.isOtpstatus()) {

				session = request.getSession();
				session.setAttribute("userid", usermodel.getUserid());
				session.setAttribute("username", usermodel.getUsername());
				session.setAttribute("token", usermodel.getToken());
				session.setAttribute("role", usermodel.getRole());
				session.setAttribute("company", usermodel.getCompanyId());
				session.setAttribute("password", usermodel.getPassword());

				usermodel.setAttempt(0);
				userrepo.save(usermodel);
				response.sendRedirect("instancemanagement");
				return null;
			} else {
				if (!usermodel.isStatus()) {
					error = "Your account has been blocked";
					response.sendRedirect("/");
					return null;
				}
				if (!usermodel.isOtpstatus()) {
					error = "Your account has been blocked";
					response.sendRedirect("/");
					return null;
				}
				usermodel.setAttempt(usermodel.getAttempt() + 1);
				userrepo.save(usermodel);
				if (usermodel.getAttempt() >= 3) {
					usermodel.setStatus(false);
					usermodel.setAttempt(0);
					userrepo.save(usermodel);
					error = "Your account has been blocked";
					response.sendRedirect("/");
					return null;
				}

				error = "Invalid username and password";
				response.sendRedirect("/");
				return null;
			}
		} catch (Exception e) {
			error = "Invalid username and password";
			response.sendRedirect("/");
			return null;
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws Exception {
		if (session.getAttributeNames() != null) {
			session.invalidate();
			response.sendRedirect("/");
			return null;
		}
		return null;
	}
}
