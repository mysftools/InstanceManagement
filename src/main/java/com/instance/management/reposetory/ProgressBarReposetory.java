package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.ProgressBarMetaModel;

public interface ProgressBarReposetory extends JpaRepository<ProgressBarMetaModel, Integer> {

	ProgressBarMetaModel findByinstrundtoken(String token);
	ProgressBarMetaModel findByinsttoken(String token);
	
}
