package com.customer.POC.task1customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Column(name="customerID")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUST_SEQ")
	@SequenceGenerator(name="CUST_SEQ",sequenceName = "CUST_SEQ_GEN", initialValue = 12345,allocationSize =1)
	private Long customer_id;
	
	@Column(name="emailId")
	@Email
	private String customer_email_id;
	
	@Column(name="customerName")
	@Size(min=8, message="{error.custName.invalid}")
	@NotNull
	private String customer_name;
	
	@Column(name="mobileNumber",nullable=true)
	@Pattern(regexp="(^$|[0-9]{10})", message="{error.phoneNumber.invalid}")
	private String customer_mobile_no;
	
}


/*
 * public int getCust_Id() { return cust_Id; } public void setCust_Id(int
 * cust_Id) { this.cust_Id = cust_Id; } public String getCust_Name() { return
 * cust_Name; } public void setCust_Name(String cust_Name) { this.cust_Name =
 * cust_Name; } public @Size(min = 10) String getPhone_No() { return phone_No; }
 * public void setPhone_No(@Size(min = 10) String phone_No) { this.phone_No =
 * phone_No; }
 * 
 * @Override public String toString() { return "Customer [cust_Id=" + cust_Id +
 * ", cust_Name=" + cust_Name + ", phone_No=" + phone_No + "]"; } public
 * Customer(@Size(min = 5) int cust_Id, @Size(min = 8) String
 * cust_Name, @Size(min = 10) @Size(min = 10) String phone_No) { super();
 * this.cust_Id = cust_Id; this.cust_Name = cust_Name; this.phone_No = phone_No;
 * } public Customer(){}
 */