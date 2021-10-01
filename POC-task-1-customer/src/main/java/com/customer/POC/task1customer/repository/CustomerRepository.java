package com.customer.POC.task1customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.POC.task1customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
