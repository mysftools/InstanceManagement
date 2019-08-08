package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.CompanyMetaModel;

public interface CompanyReposetory extends JpaRepository<CompanyMetaModel, Integer>{
	
	CompanyMetaModel findBytoken(String token);

}
