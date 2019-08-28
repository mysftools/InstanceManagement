package com.instance.management.system;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomToken {

	public RandomToken() {

	}

	public String getToken(int length) {
		return initalT()+endt(length-2);
	}
	
	
	private String endt(int length) {
		final String characters = "ABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
		StringBuilder result = new StringBuilder();
	
		while (length > 0) {
			Random rand = new Random();
			result.append(characters.charAt(rand.nextInt(characters.length())));
			length--;
		}
		return result.toString();
	}

	private String initalT() {
		StringBuilder result1 = new StringBuilder();
		final String characters1 = "ABCDEFGHIJLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 2; i++) {
			Random rand = new Random();
			result1.append(characters1.charAt(rand.nextInt(characters1.length())));
		}
		return result1.toString();
	}

	public String getNumaricOtp(int length) {
		final String characters = "1234567890";
		StringBuilder result = new StringBuilder();
		while (length > 0) {
			Random rand = new Random();
			result.append(characters.charAt(rand.nextInt(characters.length())));
			length--;
		}
		return result.toString();
	}

	public String getAlphaNumaricOtp(int length) {
		final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
		StringBuilder result = new StringBuilder();
		while (length > 0) {
			Random rand = new Random();
			result.append(characters.charAt(rand.nextInt(characters.length())));
			length--;
		}
		return result.toString();
	}
}
