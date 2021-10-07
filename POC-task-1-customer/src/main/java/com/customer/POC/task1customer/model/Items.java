package com.customer.POC.task1customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Items" , schema="vishnu")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "item_id")
public class Items {
	
	
	  
	 
	@Id
	@Column(name="ItemID")
	@GenericGenerator(name = "item_sequence", strategy="com.customer.POC.task1customer.model.ItemIdGenerator")
	@GeneratedValue(generator = "item_sequence")
	private String item_id;
	
	@NotNull
	@Column(name="ItemName")
	private String item_name;
	
	@Column(name="ItemCategory")
	private String item_category;
	
	
	@ManyToMany(mappedBy = "customerItems", targetEntity = Customer.class, cascade = CascadeType.ALL)
	private List<Customer> customers;	  		 
}
