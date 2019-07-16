package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instance.management.model.InstanceRunDetailsMetaModel;

@Repository
public interface InstanceRunDetailsReposetory extends JpaRepository<InstanceRunDetailsMetaModel, Integer> {

	List<InstanceRunDetailsMetaModel> findByinstToken(String token);
	InstanceRunDetailsMetaModel findBydetailToken(String token);
}
