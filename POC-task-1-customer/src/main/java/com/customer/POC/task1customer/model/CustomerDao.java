package com.customer.POC.task1customer.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDao {
	private String id;
	private String name;
	private String emailId;
	private String mobileNo;
	private List<ItemDao> items;

}
