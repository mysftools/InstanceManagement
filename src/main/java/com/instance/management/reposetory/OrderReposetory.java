package com.instance.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instance.management.model.OrderMetaModel;

public interface OrderReposetory extends JpaRepository<OrderMetaModel, Integer> {

}
