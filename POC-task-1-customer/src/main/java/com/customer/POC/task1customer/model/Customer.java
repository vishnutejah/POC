package com.customer.POC.task1customer.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer", schema = "vishnu")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Column(name="CustomerID")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUST_SEQ")
	@SequenceGenerator(name="CUST_SEQ",sequenceName = "CUST_SEQ_GEN", initialValue = 12345,allocationSize =1)
	private Long customer_id;
	
	@Column(name="EmailId")
	@Email
	private String customer_email_id;
	
	@Column(name="CustomerName")
	@Size(min=8, message="{error.custName.invalid}")
	@NotNull
	private String customer_name;
	
	@Column(name="MobileNumber",nullable=true)
	@Pattern(regexp="(^$|[0-9]{10})", message="{error.phoneNumber.invalid}")
	private String customer_mobile_number;
	
	
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(name="customer_items", joinColumns ={@JoinColumn(name="CustomerID")}, 
	  inverseJoinColumns ={@JoinColumn(name="ItemID")}) 
	  private Set<Items> customerItems=new HashSet<Items>();
	  
	  public void addItems(Items item ){
	        this.customerItems.add(item);
	        item.getCustomers().add(this);
	    }
	    public void removeItem(Items item ) {
	        this.getCustomerItems().remove(item);
	        item.getCustomers().remove(this);
	    }
	    public void removeItems() {
	        for (Items item : new HashSet<>(customerItems)) {
	            removeItem(item);
	        }
	    }
	 
	
}
