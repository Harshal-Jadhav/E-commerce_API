package com.project.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Integer quantity;
	private Integer totalValue;
	
//	Relationship here
	// 1. Orders Relationship
	@ManyToOne
	private Orders order;
	// 2.cart Relationship
	@ManyToOne
	private Cart cart;
	// 3.Product Relationship
	@OneToOne
	private Product product;
}
