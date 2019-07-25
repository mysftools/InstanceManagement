package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.BackUpMetaModel;

public interface BackUpReposetory extends JpaRepository<BackUpMetaModel, Integer>{
	
	List<BackUpMetaModel> findByuserid(String token);
}
