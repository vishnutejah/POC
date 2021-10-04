package com.customer.POC.task1customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.POC.task1customer.model.Customer;
import com.customer.POC.task1customer.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository repo;
	
	@GetMapping(path="/customers")   //,produces= {"application/json"}
	public List<Customer> getCustomers() {
		return repo.findAll();
	}
	
	@GetMapping(path="/customers/{id}")  
	public Optional<Customer> getCustomerbyId(@Valid @PathVariable int id) {
		return repo.findById(id);	
	}
	
	@PostMapping(path="/customers")
	public void setCustomer(@Valid @RequestBody Customer customer) {
		repo.save(customer);
	}
	
	@PutMapping(path="/customers")
	public void updateCustomer(@Valid @RequestBody Customer customer) {
		repo.save(customer);
	}
	
	@DeleteMapping("/customers/{id}")
	public void deleteCustomerbyId(@Valid @PathVariable int id) {
		repo.deleteById(id); 
	}

}
