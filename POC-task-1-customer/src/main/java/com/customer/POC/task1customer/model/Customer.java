package com.customer.POC.task1customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer", schema = "vishnu")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "customer_id")
public class Customer {
	@Column(name="CustomerID")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customer_id;
	
	@Column(name="EmailId")
	@Email
	private String customer_email_id;
	
	@Column(name="CustomerName")
	@Size(min=8, message="{error.custName.invalid}")
	@NotNull
	private String customer_name;
	
	@Column(name="MobileNumber")
	@Pattern(regexp="(^$|[0-9]{10})", message="{error.phoneNumber.invalid}")
	private String customer_mobile_number;
	
	 @ManyToMany(targetEntity = Items.class, cascade = CascadeType.ALL)
	  @JoinTable(name="customer_items", joinColumns ={@JoinColumn(name="CustomerID")}, 
	  inverseJoinColumns ={@JoinColumn(name="ItemID")}) 
	  private List<Items> customerItems;
	 
	 
	
}
