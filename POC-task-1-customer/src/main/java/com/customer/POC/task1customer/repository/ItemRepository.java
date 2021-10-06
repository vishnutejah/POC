package com.customer.POC.task1customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.POC.task1customer.model.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items,String> {


}
