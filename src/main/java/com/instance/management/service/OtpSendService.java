package com.instance.management.service;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.DirPath;
import com.instance.management.system.TwoWayPasswordManagement;

@Service
public class OtpSendService {

	@Autowired
	UserReposetory userReposetory;

	TwoWayPasswordManagement twoWayPasswordManagement = new TwoWayPasswordManagement();

	public Object sendOtp(String usermail) throws Exception {
		Map<String, Object> map1 = new HashMap<String, Object>();
		try {

			String s1 = DirPath.setPath();
			Yaml yaml = new Yaml();

			Map<String, Object> map = new HashMap<>();
			FileReader fileReader = new FileReader(s1 + "/config/email.config");
			map = yaml.load(fileReader);

			String email = map.get("email").toString();
			String pas = twoWayPasswordManagement.decry(map.get("emailpassword").toString());

			Properties props = System.getProperties();
			props.put("mail.smtp.host", map.get("host").toString());
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session s = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(s);

			message.setFrom(new InternetAddress(email));

			message.setRecipients(Message.RecipientType.TO, usermail);

			message.setSubject("Here is Your Password");

			BodyPart messageBodyPart = new MimeBodyPart();
			UserMetaModel userMetaModel=userReposetory.findByusername(usermail);
			String otp = userMetaModel.getPassword();
			userMetaModel.setStatus(true);
			userReposetory.save(userMetaModel);
			messageBodyPart.setText(otp);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			messageBodyPart = new MimeBodyPart();
			Transport tr = s.getTransport("smtps");
			tr.connect(map.get("host").toString(), email, pas);
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
			map1.put("status", true);
			map1.put("message", "password has bean send to your email");
			return map1;
		} catch (Exception e) {
			map1.put("status", false);
			map1.put("message", "some error has bean accoured");
			return map1;
		}
		
	}

}
