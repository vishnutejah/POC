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
public class ItemService {
	
	private CustomerService customerService;
	private CustomerRepository customerRepository;
	private ItemRepository itemRepository;

	public ItemService(CustomerRepository customerRepository, ItemRepository itemRepository,CustomerService customerService ) {
		this.customerRepository = customerRepository;
		this.itemRepository = itemRepository;
		this.customerService= customerService;
		
	}
	
	public ResponseEntity<Object> addItem(Items item) {

		Items newItem = new Items();
		newItem.setItem_id(item.getItem_id());
		newItem.setItem_name(item.getItem_name());
		newItem.setItem_category(item.getItem_category());
		List<Items> itemList = new ArrayList<Items>();
		itemList.add(newItem);
		for (int i = 0; i < item.getCustomers().size(); i++) {
			if (!customerRepository.findById(item.getCustomers().get(i).getCustomer_id()).isPresent()) {
				Customer newCustomer = item.getCustomers().get(i);
				newCustomer.setCustomerItems(itemList);
				Customer savedCustomer = customerRepository.save(newCustomer);
				if (!customerRepository.findById(savedCustomer.getCustomer_id()).isPresent())
					return ResponseEntity.unprocessableEntity().body("Item Creation Failed");
			} else
				return ResponseEntity.unprocessableEntity().body("Customer with Id is already Present");
		}
		return ResponseEntity.ok("Successfully created Item");
	}
	
	  public ItemDao getItem(String id) {
	    	if(itemRepository.findById(id).isPresent()) {
	    		Items item =itemRepository.findById(id).get();
	    		ItemDao itemDao= new ItemDao();
	    		itemDao.setId(item.getItem_id());
	    		itemDao.setCategory(item.getItem_category());
	    		itemDao.setName(item.getItem_name());
	    		return itemDao;
	    	}
	    	else return null;
	    }
	
	 public List<ItemDao> getItems(){
	    	List<Items> itemList = itemRepository.findAll();
	    	if(itemList.size()>0) {
	    		List<ItemDao> itemDao=new ArrayList<ItemDao>();
	    		for(Items item:itemList) {
	    			ItemDao dao=new ItemDao();
	    			dao.setId(item.getItem_id());
	    			dao.setCategory(item.getItem_category());
	    			dao.setName(item.getItem_name());
	    			itemDao.add(dao);
	    		}
	    		return itemDao;
	    	}
	    	else return new ArrayList<ItemDao>();
	    }
	
	public ResponseEntity<Object> deleteItem(String id) {
		if (itemRepository.findById(id).isPresent()) {
			itemRepository.deleteById(id);
			if (itemRepository.findById(id).isPresent()) {
				return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
			} else
				return ResponseEntity.ok().body("Successfully deleted specified record");
		} else
			return ResponseEntity.unprocessableEntity().body("No Records Found");
	}

	public ResponseEntity<Object> updateItem(String id, Items item) {
		if (itemRepository.findById(id).isPresent()) {
			Items newItem = itemRepository.findById(id).get();
			newItem.setItem_id(item.getItem_id());
			newItem.setItem_name(item.getItem_name());
			newItem.setItem_category(item.getItem_category());
			Items savedItem = itemRepository.save(newItem);
			if (itemRepository.findById(savedItem.getItem_id()).isPresent())
				return ResponseEntity.accepted().body("Item updated successfully");
			else
				return ResponseEntity.badRequest().body("Failed to update Item");

		} else
			return ResponseEntity.unprocessableEntity().body("Specified Item not found");
	}
	
	  public List<CustomerDao> getCustomersList(Items item){
	    	List<CustomerDao> customersList = new ArrayList<CustomerDao>();
	    	for(int i=0;i<item.getCustomers().size();i++) {
	    		CustomerDao customerDao = new CustomerDao();
	    		customerDao.setId(item.getCustomers().get(i).getCustomer_id());
	    		customerDao.setEmailId(item.getCustomers().get(i).getCustomer_email_id());
	    		customerDao.setMobileNo(item.getCustomers().get(i).getCustomer_mobile_number());
	    		customerDao.setName(item.getCustomers().get(i).getCustomer_name());
	    		customerDao.setItems(customerService.getItemsList(item.getCustomers().get(i)));
	    		customersList.add(customerDao);
	    	}
	    	return customersList;
	    }
}
