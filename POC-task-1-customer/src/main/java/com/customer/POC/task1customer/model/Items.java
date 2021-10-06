package com.customer.POC.task1customer.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class Items {
	@Id
	@Column(name="ItemID")
	@GenericGenerator(name = "item_sequence", strategy ="com.customer.POC.task1customer.model.ItemIdGenerator")
	@GeneratedValue(generator = "item_sequence")
	private String item_id;
	
	@NotNull
	@Column(name="ItemName")
	private String name;
	
	@Column(name="ItemCategory")
	private String item_category;
	
	
	  @ManyToMany(mappedBy = "customerItems", fetch= FetchType.LAZY)
	  @JsonIgnore
	  private Set<Customer> customers=new HashSet<Customer>();	  		 
}
