package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.UserMetaModel;

public interface UserReposetory extends JpaRepository<UserMetaModel	, Integer> {
	public UserMetaModel findBytoken(String token);
	public UserMetaModel findByuserid(String username);
	public List<UserMetaModel> findByCompanyIdAndRole(String companyname,String role);
	
	
}
