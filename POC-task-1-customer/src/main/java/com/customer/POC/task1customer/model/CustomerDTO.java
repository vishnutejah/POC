package com.customer.POC.task1customer.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	private Long customer_id;
	private String customer_email_id;
	private String customer_name;
	private String customer_mobile_number;
	private Set<Items> customerItems=new HashSet<Items>();

}
