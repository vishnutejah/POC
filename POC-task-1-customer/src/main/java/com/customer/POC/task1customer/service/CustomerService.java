package com.customer.POC.task1customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.POC.task1customer.model.Customer;
import com.customer.POC.task1customer.model.CustomerDao;
import com.customer.POC.task1customer.model.ItemDao;
import com.customer.POC.task1customer.model.Items;
import com.customer.POC.task1customer.repository.CustomerRepository;
import com.customer.POC.task1customer.repository.ItemRepository;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;
    private ItemRepository itemRepository;

    public CustomerService(CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }
    
    public CustomerDao getCustomer(String id) {
    	if(customerRepository.findById(id).isPresent()) {
    		Customer customer =customerRepository.findById(id).get();
    		CustomerDao customerDao= new CustomerDao();
    		customerDao.setId(customer.getCustomer_id());
    		customerDao.setEmailId(customer.getCustomer_email_id());
    		customerDao.setName(customer.getCustomer_name());
    		customerDao.setMobileNo(customer.getCustomer_mobile_number());
    		customerDao.setItems(getItemsList(customer));
    		return customerDao;
    	}
    	else return null;
    }
    
    public List<CustomerDao> getCustomers(){
    	List<Customer> customerList = customerRepository.findAll();
    	if(customerList.size()>0) {
    		List<CustomerDao> customerDao=new ArrayList<CustomerDao>();
    		for(Customer customer:customerList) {
    			CustomerDao dao=new CustomerDao();
    			dao.setId(customer.getCustomer_id());
    			dao.setEmailId(customer.getCustomer_email_id());
    			dao.setName(customer.getCustomer_name());
    			dao.setMobileNo(customer.getCustomer_mobile_number());
    			dao.setItems(getItemsList(customer));
    			customerDao.add(dao);
    		}
    		return customerDao;
    	}
    	else return new ArrayList<CustomerDao>();
    }
    
    public ResponseEntity<Object> updateCustomer(Customer customer, String customer_id) {
        if(customerRepository.findById(customer_id).isPresent()) {
            Customer newCustomer = customerRepository.findById(customer_id).get();
            newCustomer.setCustomer_id(customer.getCustomer_id());
        	newCustomer.setCustomer_email_id(customer.getCustomer_email_id());
        	newCustomer.setCustomer_mobile_number(customer.getCustomer_mobile_number());
        	newCustomer.setCustomer_name(customer.getCustomer_name());
        	newCustomer.setCustomerItems(customer.getCustomerItems());
        	Customer savedCustomer = customerRepository.save(newCustomer);
            if(customerRepository.findById(savedCustomer.getCustomer_id()).isPresent())
                return  ResponseEntity.accepted().body("Customer updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the Customer specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the Customer specified");
    }
   
    public ResponseEntity<Object> deleteCustomer(String id) {
        if (customerRepository.findById(id).isPresent()) {
        	customerRepository.deleteById(id);
            if (customerRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Customer");
            else return ResponseEntity.ok().body("Successfully deleted the specified Customer");
        } else return ResponseEntity.badRequest().body("Cannot find the Customer specified");
    }
    
    public List<ItemDao> getItemsList(Customer customer){
    	List<ItemDao> itemsList = new ArrayList<ItemDao>();
    	for(int i=0;i<customer.getCustomerItems().size();i++) {
    		ItemDao itemDao = new ItemDao();
    		itemDao.setId(customer.getCustomerItems().get(i).getItem_id());
    		itemDao.setName(customer.getCustomerItems().get(i).getItem_name());
    		itemDao.setCategory(customer.getCustomerItems().get(i).getItem_category());
    		itemsList.add(itemDao);
    	}
    	return itemsList;
    }
    	 
}
