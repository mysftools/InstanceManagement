package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instance.management.model.OtpModel;

@Repository
public interface OtpRepository extends JpaRepository<OtpModel, Integer> {
	public OtpModel findBytempPassword(String otp);
	public OtpModel findByusername(String username);
}
