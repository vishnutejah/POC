package com.customer.POC.task1customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.POC.task1customer.model.Customer;
import java.lang.String;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
