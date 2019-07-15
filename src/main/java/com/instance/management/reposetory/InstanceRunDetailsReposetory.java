package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instance.management.model.InstanceRunDetailsModel;

@Repository
public interface InstanceRunDetailsReposetory extends JpaRepository<InstanceRunDetailsModel, Integer> {

	List<InstanceRunDetailsModel> findByinstToken(String token);

}
