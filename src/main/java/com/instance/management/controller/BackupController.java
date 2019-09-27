package com.instance.management.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.instance.management.backup.BackUpService;
import com.instance.management.model.BackUpMetaModel;
import com.instance.management.reposetory.BackUpReposetory;
import com.instance.management.reposetory.CompanyReposetory;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.reposetory.UserReposetory;
import com.instance.management.system.DirPath;

@Controller
@RequestMapping("/backup")
public class BackupController {

	@Autowired
	BackUpService backUpService;

	@Autowired
	CompanyReposetory companyReposetory;

	@Autowired
	BackUpReposetory backUpReposetory;

	private final Path fileStorageLocation;

	@Autowired
	InstanceReposetory instanceReposetory;

	@Autowired
	UserReposetory userrepo;

	@Autowired
	public BackupController() {
		this.fileStorageLocation = Paths.get("./").toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {

		}
	}

	@GetMapping("")
	public String index(Model model, HttpServletResponse response, HttpSession session) throws Exception {

		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		model.addAttribute("page", "backup");
		return "template";
	}

	@PostMapping("/takesingilebackup")
	@ResponseBody
	public Object takesingleBackup(@RequestParam("filepath") MultipartFile filepath, @RequestParam String uid,
			HttpServletResponse response, HttpSession session) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}

		BackUpMetaModel backUpMetaModel = new BackUpMetaModel();
		backUpMetaModel.setInstanceid(uid);
		backUpMetaModel.setUsername(session.getAttribute("username").toString());
		backUpMetaModel.setUserid(session.getAttribute("token").toString());
		backUpMetaModel.setNameOfInstance(instanceReposetory.findByinstToken(uid).getNameOfInstance());
		File file = new File(DirPath.setPath() + "/fileUpload/" + "package.xml");
		file.mkdirs();
		String fileName = org.springframework.util.StringUtils.cleanPath(file.toString());
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		Files.copy(filepath.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		backUpService.single(file.getAbsolutePath(), uid);
		backUpReposetory.save(backUpMetaModel);
		Map<String, Object> response1 = new HashMap<>();
		response1.put("status", true);
		response1.put("message", "File Uploaded Successfully");
		return response1;
	}

	@PostMapping("/backuphistory")
	@ResponseBody
	public Object backuphistory(HttpSession session, HttpServletResponse response) throws Exception {
		if (!LoginController.userValidate(session)) {
			response.sendRedirect("/");
			return null;
		}
		return backUpReposetory.findByuserid(session.getAttribute("token").toString());
	}

	/*
	 * @PostMapping("/backupall") public @ResponseBody Object
	 * backall(HttpServletResponse response, HttpSession session) { Map<String,
	 * Object> map = new HashMap<String, Object>(); try { if
	 * (!LoginController.userValidate(session)) { response.sendRedirect("/"); return
	 * null; }
	 * 
	 * backUpService.data1(session);
	 * 
	 * map = new HashMap<String, Object>(); map.put("status", true);
	 * map.put("message", "Data back up successfully");
	 * 
	 * return map; } catch (Exception e) { e.printStackTrace(); map = new
	 * HashMap<String, Object>(); map.put("status", false); map.put("message",
	 * "Some error has bean occured"); return map; } }
	 * 
	 * @PostMapping("/backupselected") public @ResponseBody Object
	 * backselected(@RequestBody Map<String, String[]> token, HttpServletResponse
	 * response, HttpSession session) { Map<String, Object> map = new
	 * HashMap<String, Object>(); try { if (!LoginController.userValidate(session))
	 * { response.sendRedirect("/"); return null; }
	 * 
	 * backUpService.data(token.get("token"));
	 * 
	 * map = new HashMap<String, Object>(); map.put("status", true);
	 * map.put("message", "Data back up successfully");
	 * 
	 * return map; } catch (Exception e) { e.printStackTrace(); map = new
	 * HashMap<String, Object>(); map.put("status", false); map.put("message",
	 * "Some error has bean occured"); return map; } }
	 */

	/*
	 * @PostMapping("/getinstinfo") public @ResponseBody Object
	 * getinstanceinfo(HttpServletResponse response, HttpSession session) {
	 * Map<String, Object> map = new HashMap<String, Object>(); try { if
	 * (!LoginController.userValidate(session)) { response.sendRedirect("/"); return
	 * null; } if (session.getAttribute("role").toString().equals("admin")) {
	 * CompanyMetaModel companyMetaModel = companyReposetory
	 * .findBytoken(session.getAttribute("company").toString());
	 * List<InstanceMetaModel> instanceMetaModels = instanceReposetory
	 * .findBytoken(session.getAttribute("token").toString()); map = new
	 * HashMap<String, Object>(); map.put("status", true); map.put("message",
	 * "Data back up successfully"); map.put("totalinst",
	 * instanceMetaModels.size()); map.put("totalcalls",
	 * companyMetaModel.getTotalruns()); map.put("remainingcalls",
	 * companyMetaModel.getRemainingruns()); return map; } else { CompanyMetaModel
	 * companyMetaModel = companyReposetory
	 * .findBytoken(session.getAttribute("company").toString()); UserMetaModel
	 * userMetaModel =
	 * userrepo.findBytoken(session.getAttribute("token").toString());
	 * 
	 * map = new HashMap<String, Object>(); map.put("status", true);
	 * map.put("message", "Data back up successfully"); map.put("totalinst",
	 * userMetaModel.getListInst().split(",").length); map.put("totalcalls",
	 * companyMetaModel.getTotalruns()); map.put("remainingcalls",
	 * companyMetaModel.getRemainingruns()); return map; }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); map = new HashMap<String,
	 * Object>(); map.put("status", false); map.put("message",
	 * "Some error has bean occured"); return map; }
	 * 
	 * }
	 */
	
	
	
	/*
	 * @PostMapping("/fileupload")
	 * 
	 * @ResponseBody public Object fileupload(@RequestParam("filepath")
	 * MultipartFile filepath, HttpSession session) { Map<String, Object> response =
	 * new HashMap<>(); try { if (!filepath.getOriginalFilename().contains(".xml"))
	 * { response = new HashMap<>(); response.put("status", false);
	 * response.put("message", "Select xml file only"); return response; }
	 * 
	 * File file = new File(DirPath.setPath() + "/fileUpload/" + "package.xml");
	 * file.mkdirs(); String fileName =
	 * org.springframework.util.StringUtils.cleanPath(file.toString()); Path
	 * targetLocation = this.fileStorageLocation.resolve(fileName);
	 * Files.copy(filepath.getInputStream(), targetLocation,
	 * StandardCopyOption.REPLACE_EXISTING); response = new HashMap<>();
	 * response.put("status", true); response.put("message",
	 * "File Uploaded Successfully"); return response; } catch (Exception e) {
	 * response = new HashMap<>(); response.put("status", false);
	 * response.put("message", "File not Recieved"); return response; }
	 * 
	 * }
	 */

}
