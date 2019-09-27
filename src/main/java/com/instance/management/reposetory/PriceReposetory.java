package com.instance.management.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.PriceMetaModel;

public interface PriceReposetory extends JpaRepository<PriceMetaModel, Integer> {
	PriceMetaModel findBytoken(String token);
	List<PriceMetaModel> findBystatus(boolean status);
}
