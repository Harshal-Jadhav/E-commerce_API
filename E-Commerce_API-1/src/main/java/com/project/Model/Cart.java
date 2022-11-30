package com.project.Model;

import java.util.ArrayList;
import java.util.List;

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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Integer totalCartValue;

//	Relationships here
	// 1. Customer Relationship
	@OneToOne
	private Customer customer;
	
	// 2. Details Relationship.
	@OneToMany(mappedBy = "cart")
	private List<OrderDetails> cartList = new ArrayList<>();
}
