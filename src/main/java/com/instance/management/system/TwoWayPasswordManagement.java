package com.instance.management.system;

import org.springframework.stereotype.Service;

@Service
public class TwoWayPasswordManagement {

	public TwoWayPasswordManagement() {

	}

	public  String encrypt(String password) throws Exception {

		int code;
		String result = "";
		for (int i = 0; i < password.length(); i++) {
			code = Math.round((float) Math.random() * 8 + 1);
			result += code + Integer.toHexString(((int) password.charAt(i)) ^ code) + "-";
		}
		return result.substring(0, result.lastIndexOf("-"));

	}

	public  String decrypt(String encrptedpass) throws Exception {
		encrptedpass = encrptedpass.replace("-", "");
		String result = "";
		for (int i = 0; i < encrptedpass.length(); i += 3) {
			String hex = encrptedpass.substring(i + 1, i + 3);
			result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(encrptedpass.charAt(i)))));
		}
		return result;
	}

}
