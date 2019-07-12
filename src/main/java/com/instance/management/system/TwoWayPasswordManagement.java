package com.instance.management.system;

import java.security.AlgorithmParameters;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class TwoWayPasswordManagement {

	public TwoWayPasswordManagement() {

	}

	public String encry(String originalPassword) throws Exception {

		return encrypt(originalPassword, genkey());

	}

	public String decry(String encpassword) throws Exception {

		return decrypt(encpassword, genkey());
	}

	private static SecretKeySpec genkey() throws Exception {
		String password = System.getProperty("java.version");

		if (password == null) {
			throw new IllegalArgumentException("Run with -Dpassword=<password>");
		}

		// The salt (probably) can be stored along with the encrypted data
		byte[] salt = new String("12345678").getBytes();

		// Decreasing this speeds down startup time and can be useful during testing,
		// but it also makes it easier for brute force attackers
		int iterationCount = 40000;
		// Other values give me java.security.InvalidKeyException: Illegal key size or
		// default parameters
		int keyLength = 128;
		return createSecretKey(password.toCharArray(), salt, iterationCount, keyLength);
	}

	private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength)
			throws Exception {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	}

	private static String encrypt(String property, SecretKeySpec key) throws Exception {
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
	}

	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	private static String decrypt(String string, SecretKeySpec key) throws Exception {
		String iv = string.split(":")[0];
		String property = string.split(":")[1];
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws Exception {
		return Base64.getDecoder().decode(property);
	}
}
