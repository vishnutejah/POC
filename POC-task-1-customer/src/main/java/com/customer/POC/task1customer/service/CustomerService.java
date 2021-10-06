package com.customer.POC.task1customer.service;

import java.util.List;

import com.customer.POC.task1customer.model.CustomerDTO;

public interface CustomerService {

	public CustomerDTO addCustomer(CustomerDTO customerDto);
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customer);
    public String deletecustomer(Long customerId);
}
