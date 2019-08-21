package com.instance.management.backup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instance.management.reposetory.InstanceReposetory;

@Service
public class BackUpService {

	@Autowired
	InstanceReposetory instanceReposetory;

	/*
	 * public void data(String[] token) { GetInstanceDetailsService tp = new
	 * GetInstanceDetailsService(); List<CustomerSfInfo> csfList =
	 * tp.getServers(token); for (CustomerSfInfo cs : csfList) { try { new
	 * SfMetadataThread(cs).run(); } catch (Exception e) { e.printStackTrace(); } }
	 * }
	 * 
	 * public void data1(HttpSession httpSession) { GetInstanceDetailsService tp =
	 * new GetInstanceDetailsService(); List<InstanceMetaModel> instanceMetaModels =
	 * instanceReposetory
	 * .findBytoken(httpSession.getAttribute("token").toString());
	 * 
	 * List<CustomerSfInfo> csfList = tp.getServers1(instanceMetaModels); for
	 * (CustomerSfInfo cs : csfList) { try { new SfMetadataThread(cs).run(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 */
	public void single(String filepath, String token) {
		GetInstanceDetailsService tp = new GetInstanceDetailsService();
		List<CustomerSfInfo> csfList = tp.getServerssingle(filepath, token);
		for (CustomerSfInfo cs : csfList) {
			try {
				new SfMetadataThread(cs).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
