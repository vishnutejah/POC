package com.customer.POC.task1customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.POC.task1customer.model.Items;
import com.customer.POC.task1customer.repository.ItemRepository;

@RestController
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@GetMapping(path="/items")
	public List<Items> getItems() {
		List<Items> itemsList=itemRepo.findAll();
		return itemsList;
	}
	
	@GetMapping(path="/items/{id}")  
	public Optional<Items> getItemById(@Valid @PathVariable String id) {
		return itemRepo.findById(id);	
	}
	
	@PostMapping(path="/items")
	public void setItem(@Valid @RequestBody Items item) {
		
		itemRepo.save(item);
	}


}
