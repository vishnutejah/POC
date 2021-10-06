package com.customer.POC.task1customer.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.POC.task1customer.model.Customer;
import com.customer.POC.task1customer.model.CustomerDTO;
import com.customer.POC.task1customer.model.Items;
import com.customer.POC.task1customer.repository.CustomerRepository;
import com.customer.POC.task1customer.repository.ItemRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Override
	@Transactional
	public CustomerDTO addCustomer(CustomerDTO customerDto) {
		Customer customer= new Customer();
        mapDtoToEntity(customerDto, customer);
        Customer savedCustomer = customerRepository.save(customer);
        return mapEntityToDto(savedCustomer);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		 List<CustomerDTO> customerDtos = new ArrayList<>();
	        List<Customer> customers = customerRepository.findAll();
	        customers.stream().forEach(customer -> {
	        	CustomerDTO customerDto = mapEntityToDto(customer);
	            customerDtos.add(customerDto);
	        });
	        return customerDtos;
	}

	@Override
	@Transactional
	public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDto) {
		  Customer cust = customerRepository.findById(customerId).get();
	        cust.getCustomerItems().clear();
	        mapDtoToEntity(customerDto, cust);
	        Customer customer = customerRepository.save(cust);
	        return mapEntityToDto(customer);
	}

	@Override
	public String deletecustomer(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
           customer.get().removeItems();
           customerRepository.deleteById(customer.get().getCustomer_id());
            return "Customer with id: " + customerId + " deleted successfully!";
        }
        return null;
	}
	
	  private void mapDtoToEntity(CustomerDTO customerDto, Customer customer) {
		  
		  customer.setCustomer_email_id(customerDto.getCustomer_email_id());
	      customer.setCustomer_mobile_number(customerDto.getCustomer_mobile_number());
	      customer.setCustomer_name(customerDto.getCustomer_name());
	        if (null == customer.getCustomerItems()) {
	            customer.setCustomerItems(new HashSet<>());
	        }
	        customerDto.getCustomerItems().stream().forEach(itemm -> {
	            Items item = itemRepository.findById(itemm.getItem_id()).get();
	            if (null == item) {
	                item = new Items();
	                item.setCustomers(new HashSet<>());
	            }
	            item.setItem_id(item.getItem_id());
	            customer.addItems(item);
	        });
	    }
	  private CustomerDTO mapEntityToDto(Customer customer) {
		    CustomerDTO responseDto = new CustomerDTO();
	       responseDto.setCustomer_email_id(customer.getCustomer_email_id());
	       responseDto.setCustomer_id(customer.getCustomer_id());
	       responseDto.setCustomer_mobile_number(customer.getCustomer_mobile_number());
	       responseDto.setCustomer_name(customer.getCustomer_name());
	        responseDto.setCustomerItems(customer.getCustomerItems());
	        return responseDto;
	    }

}
