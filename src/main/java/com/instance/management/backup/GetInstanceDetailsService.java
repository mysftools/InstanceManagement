package com.instance.management.backup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.instance.management.model.InstanceMetaModel;
import com.instance.management.reposetory.InstanceReposetory;
import com.instance.management.system.DirPath;

public class GetInstanceDetailsService {

	@Autowired
	InstanceReposetory instanceReposetory;

	public List<CustomerSfInfo> getServers(String[] token) {
		List<CustomerSfInfo> custList = new ArrayList<>();

		try {
			for (String tokens : token) {
				InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(tokens);

				CustomerSfInfo csfi = new CustomerSfInfo();
				csfi.user = instanceMetaModel.getUsername();
				csfi.pwd = instanceMetaModel.getPassword();
				csfi.token = instanceMetaModel.getSecurityCode();
				csfi.isSandbox = instanceMetaModel.isSandbox();
				csfi.customerName = instanceMetaModel.getCoustomerName();
				csfi.backupPath = DirPath.setPath() + "/backup";
				csfi.fileLocation = DirPath.setPath() + "/fileUpload/package.xml";
				custList.add(csfi);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return custList;
	}

	public List<CustomerSfInfo> getServers1(List<InstanceMetaModel> instanceMetaModels) {
		List<CustomerSfInfo> custList = new ArrayList<>();
		try {
			for (InstanceMetaModel instanceMetaModel : instanceMetaModels) {
				CustomerSfInfo csfi = new CustomerSfInfo();
				csfi.user = instanceMetaModel.getUsername();
				csfi.pwd = instanceMetaModel.getPassword();
				csfi.token = instanceMetaModel.getSecurityCode();
				csfi.isSandbox = instanceMetaModel.isSandbox();
				csfi.customerName = instanceMetaModel.getCoustomerName();
				csfi.backupPath = DirPath.setPath() + "/backup";
				csfi.fileLocation = DirPath.setPath() + "/backuplocation";
				custList.add(csfi);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return custList;
	}

	public List<CustomerSfInfo> getServerssingle(String filepath, String token) {
		List<CustomerSfInfo> custList = new ArrayList<>();

		try {

			InstanceMetaModel instanceMetaModel = instanceReposetory.findByinstToken(token);

			CustomerSfInfo csfi = new CustomerSfInfo();
			csfi.user = instanceMetaModel.getUsername();
			csfi.pwd = instanceMetaModel.getPassword();
			csfi.token = instanceMetaModel.getSecurityCode();
			csfi.isSandbox = instanceMetaModel.isSandbox();
			csfi.customerName = instanceMetaModel.getCoustomerName();
			csfi.backupPath = DirPath.setPath() + "/backup";
			csfi.fileLocation = filepath;
			custList.add(csfi);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return custList;
	}

}
