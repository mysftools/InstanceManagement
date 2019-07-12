package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.InstanceMetaModel;

public interface InstanceReposetory extends JpaRepository<InstanceMetaModel, Integer>{

	public List<InstanceMetaModel> findBytoken(String token);
	
	public InstanceMetaModel findByinstToken(String instToken);
}
