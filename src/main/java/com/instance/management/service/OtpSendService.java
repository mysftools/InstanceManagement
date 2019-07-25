package com.instance.management.service;

import java.io.FileReader;
import java.util.Date;
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

import com.instance.management.model.OtpModel;
import com.instance.management.model.UserMetaModel;
import com.instance.management.reposetory.OtpRepository;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.DirPath;
import com.instance.management.system.RandomToken;
import com.instance.management.system.TwoWayPasswordManagement;

@Service
public class OtpSendService {

	@Autowired
	UserReposetory userReposetory;
	
	@Autowired
	OtpRepository otpRepository;

	TwoWayPasswordManagement twoWayPasswordManagement = new TwoWayPasswordManagement();
	
	@Autowired
	RandomToken randomToken;
	
	public Map<String, Object> sendotp(String usermail){
		Map<String, Object> map1 = new HashMap<String, Object>();
		try {
			
			OtpModel otpModel = new OtpModel();
			String otp=randomToken.getNumaricOtp(6);
			otpModel.setTempPassword(otp);
			otpModel.setSendTime(new Date(System.currentTimeMillis()));
			otpModel.setUsername(usermail);
			
			if(Boolean.parseBoolean(sendmail(usermail, otp, "Here is Your OTP").get("status").toString())) {
				otpRepository.save(otpModel);
				map1.put("status", true);
				map1.put("message", "otp bean send to your email");
				return map1;
			}else {
				map1.put("status", false);
				map1.put("message", "some error has bean accoured");
				return map1;
			}
			
			
		} catch (Exception e) {
			map1.put("status", false);
			map1.put("message", "some error has bean accoured");
			return map1;
		}
		
		
	}

	public Map<String, Object> sendpass(String usermail) throws Exception {
		Map<String, Object> map1 = new HashMap<String, Object>();
		try {
			UserMetaModel userMetaModel=userReposetory.findByusername(usermail);
			userMetaModel.setStatus(true);
			userMetaModel.setOtpstatus(true);
			userReposetory.save(userMetaModel);
			sendmail(usermail, userMetaModel.getPassword(), "Here is Your Password");
			
			map1.put("status", true);
			map1.put("message", "password has bean send to your email");
			return map1;
		} catch (Exception e) {
			map1.put("status", false);
			map1.put("message", "some error has bean accoured");
			return map1;
		}
		
	}

	private Map<String, Object> sendmail(String usermail,String body,String sub)  throws Exception {
		Map<String, Object> map1 = new HashMap<String, Object>();
		try {

			String s1 = DirPath.setPath();
			Yaml yaml = new Yaml();

			Map<String, Object> map = new HashMap<>();
			FileReader fileReader = new FileReader(s1 + "/config/email.config");
			map = yaml.load(fileReader);

			String email = map.get("email").toString();
			String pas = twoWayPasswordManagement.decrypt(map.get("emailpassword").toString());

			Properties props = System.getProperties();
			props.put("mail.smtp.host", map.get("host").toString());
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session s = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(s);

			message.setFrom(new InternetAddress(email));

			message.setRecipients(Message.RecipientType.TO, usermail);

			message.setSubject(sub);

			BodyPart messageBodyPart = new MimeBodyPart();
			
			messageBodyPart.setText(body);

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
			e.printStackTrace();
			map1.put("status", false);
			map1.put("message", "some error has bean accoured");
			return map1;
		}
		
	}
}
