package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.model.CompanyMetaModel;
import com.instance.management.model.PaymentCompletionModel;
import com.instance.management.reposetory.CompanyReposetory;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	CompanyReposetory companyReposetory;

	@PostMapping("/ordercreat")
	public @ResponseBody Object creatorder(@RequestParam int amount, HttpServletResponse response, HttpSession session)
			throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		RazorpayClient razorpay = new RazorpayClient("rzp_test_8sJa3x6gA2CVoj", "BJIKGCUHxsQ6cCL59JO03MiJ");
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount);
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "rcptid #5");
		orderRequest.put("payment_capture", true);

		Order orders = razorpay.Orders.create(orderRequest);
		Map<String, Object> orderdetails = new HashMap<String, Object>();
		orderdetails.put("amount", orders.get("amount"));
		orderdetails.put("order_id", orders.get("id"));
		orderdetails.put("amount_due", orders.get("amount_due"));
		orderdetails.put("amount_paid", orders.get("amount_paid"));
		orderdetails.put("attempts", orders.get("attempts"));
		orderdetails.put("created_at", orders.get("created_at"));
		orderdetails.put("currency", orders.get("currency"));
		orderdetails.put("entity", orders.get("entity"));
		orderdetails.put("created_at", orders.get("created_at"));
		orderdetails.put("receipt", orders.get("receipt"));
		orderdetails.put("orders_status", orders.get("status"));
		orderdetails.put("email", session.getAttribute("userid"));
		orderdetails.put("username", session.getAttribute("username"));
		orderdetails.put("key", "rzp_test_8sJa3x6gA2CVoj");
		String cid = session.getAttribute("company").toString();
		orderdetails.put("company_name", companyReposetory.findBytoken(cid).getCompanyname());
		return orderdetails;
	}

	@PostMapping("/updaterun")
	public @ResponseBody Object updatepaymentRuns(@RequestBody PaymentCompletionModel completionModel,
			HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!LoginController.userValidate(session)) {
				response.sendRedirect("/");
				return null;
			}
			RazorpayClient razorpay = new RazorpayClient("rzp_test_8sJa3x6gA2CVoj", "BJIKGCUHxsQ6cCL59JO03MiJ");
			Payment payment = razorpay.Payments.fetch(completionModel.getRazorpay_payment_id());
			if ( payment!=null) {
				CompanyMetaModel companyMetaModel = companyReposetory
						.findBytoken(session.getAttribute("company").toString());
				companyMetaModel.setTotalruns(companyMetaModel.getRemainingruns() + completionModel.getRuns());
				companyMetaModel.setRemainingruns(companyMetaModel.getRemainingruns() + completionModel.getRuns());
				companyReposetory.save(companyMetaModel);
				map.put("status", true);
				map.put("code",200);
				map.put("message", "Runs updated successfully");
				return map;
			}else {
				map.put("status", false);
				map.put("code", 500);
				map.put("message", "Some Problem has bean occured");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("code", 500);
			map.put("message", "Some Problem has bean occured");
			return map;
		}
	}
}
