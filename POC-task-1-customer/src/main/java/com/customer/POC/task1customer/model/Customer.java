package com.customer.POC.task1customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Data  //combination of @Getter @Setter @equals @hashcode @toString etc
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@Min(5)
	@NotNull
	private Integer cust_Id;
	
	@Size(min=8, message="{error.custName.invalid}")
	@NotNull
	private String cust_Name;
	
	@Pattern(regexp="(^$|[0-9]{10})", message="{error.phoneNumber.invalid}")
	@Column(nullable=true)
	private String phone_No;
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