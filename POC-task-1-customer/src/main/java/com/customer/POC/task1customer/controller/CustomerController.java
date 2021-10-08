package com.customer.POC.task1customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.POC.task1customer.model.Customer;
import com.customer.POC.task1customer.model.CustomerDao;
import com.customer.POC.task1customer.model.ItemDao;
import com.customer.POC.task1customer.repository.CustomerRepository;
import com.customer.POC.task1customer.service.CustomerService;

@RestController
public class CustomerController {
	
	 private CustomerService customerService;
	    private CustomerRepository customerRepository;

	    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
	        this.customerService = customerService;
	        this.customerRepository = customerRepository;
	    }
	    @PostMapping("/customer/create")
	    public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer) {
	       try{
	    	   customerRepository.save(customer);
	    	   return ResponseEntity.ok().body("Customer created!!");
	    	   }
	       catch(Exception e) {
	    	   return ResponseEntity.unprocessableEntity().body("Failed creating the Customer specified");
	       }
	    }
	    
	    @GetMapping("/customer/details/{id}")
	    public CustomerDao getCustomer(@Valid @PathVariable String id) {
	        if(customerRepository.findById(id).isPresent())
	            return customerService.getCustomer(id);
	        else return  null;
	    }
	    @GetMapping("/customer/itemlist/details/{id}")
	    public List<ItemDao> getCustomerItems(@Valid @PathVariable String id) {
	        if(customerRepository.findById(id).isPresent()) {
	            return customerService.getItemsList(customerRepository.getById(id));
	        }
	        else return  null;
	    }
	    @GetMapping("/customer/itemnamelist/details/{id}")
	    public List<String> getCustomerItemNames(@Valid @PathVariable String id) {
	        if(customerRepository.findById(id).isPresent()) {
	            List<ItemDao> itemsList= customerService.getItemsList(customerRepository.getById(id));
	            //Java 8: Using streams with method reference
	            List<String> itemNames=itemsList.stream().map(ItemDao::getName).collect(Collectors.toList());
	            return itemNames;
	        }
	        else return  null;
	    }
	    @GetMapping("/customer/all")
	    public List<CustomerDao> getCustomers() {
	        return customerService.getCustomers();
	    }
	    @PutMapping("/customer/update/{id}")
	    public ResponseEntity<Object> updateCustomer(@Valid @PathVariable String id,@Valid @RequestBody Customer customer) {
	        return customerService.updateCustomer(customer, id);
	    }
	    @DeleteMapping("customer/delete/{id}")
	    public ResponseEntity<Object> deleteCustomer(@Valid @PathVariable String id) {
	        return customerService.deleteCustomer(id);
	    }

}
