package com.instance.management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instance.management.reposetory.PriceReposetory;


@Controller
@RequestMapping("/price")
public class PriceManagement {

	@Autowired
	PriceReposetory priceReposetory;
	
	@GetMapping("/getall")
	public @ResponseBody Object getallprice() {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			map=new HashMap<String, Object>();
			map.put("status", true);
			map.put("code", 200);
			map.put("response", priceReposetory.findAll());
			return map;
		} catch (Exception e) {
			map=new HashMap<String, Object>();
			map.put("status", false);
			map.put("code", 500);
			map.put("message", "some error has bean occured");
			e.printStackTrace();
			return map;
		}
		
	}

	
	
}
