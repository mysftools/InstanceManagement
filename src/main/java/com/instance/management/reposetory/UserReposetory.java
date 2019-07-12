package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.UserMetaModel;

public interface UserReposetory extends JpaRepository<UserMetaModel	, Integer> {
	public UserMetaModel findBytoken(String token);
	public UserMetaModel findByusername(String username);
	
}
