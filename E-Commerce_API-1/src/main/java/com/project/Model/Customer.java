package com.project.Model;

import java.util.Stack;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String firstName;
	private String LastName;
	private String email;
	private String phone;
	private String password;

	@Embedded
	private Address address;

//	Relationships Starts Here.
	
	// 1. Cart Relationship.
	@OneToOne(mappedBy = "customer")
	private Cart cart;

	// 2.Orders Relationship.
//	private List<Orders> list = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Stack<Orders> orderList = new Stack<Orders>();
}
