package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.PriceMetaModel;

public interface PriceReposetory extends JpaRepository<PriceMetaModel, Integer> {

}
