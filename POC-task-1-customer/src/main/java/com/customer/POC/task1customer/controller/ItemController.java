package com.customer.POC.task1customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.POC.task1customer.model.CustomerDao;
import com.customer.POC.task1customer.model.ItemDao;
import com.customer.POC.task1customer.model.Items;
import com.customer.POC.task1customer.repository.ItemRepository;
import com.customer.POC.task1customer.service.ItemService;

@RestController
public class ItemController {
	
	private ItemService itemService;
    private ItemRepository itemRepository;

    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/item/create")
    public ResponseEntity<Object> createItem(@Valid @RequestBody Items item) {
        return  itemService.addItem(item);
    }
    @PostMapping("/item/create/repo")
    public void createItemRepo(@Valid @RequestBody Items item) {
        itemRepository.save(item);
    }
    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
    @GetMapping("/item/details/{id}")
    public ItemDao getItem(@Valid @PathVariable Long id) {
        if(itemRepository.findById(id).isPresent())
            return itemService.getItem(id);
        else return null;
    }
    @GetMapping("/item/all")
    public List<ItemDao> getItems() {
        return itemService.getItems();
    }
    @GetMapping("/item/customerlist/details/{id}")
    public List<CustomerDao> getCustomerList(@Valid @PathVariable Long id) {
        return itemService.getCustomersList(itemRepository.getById(id));
    }
    @PutMapping("/item/update/{id}")
    public ResponseEntity<Object> updateItem(@Valid @PathVariable Long id, @Valid @RequestBody Items item) {
        return itemService.updateItem(id, item);
    }



}
