package com.project.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private LocalDateTime orderDate;
	private String orderStatus;
	private Integer totalOrderValue;

//	Relationships here

	// 1. Customer Relationship
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	// 2. Details Relationship
	@OneToMany(mappedBy = "order")
	List<OrderDetails> orderDetails = new ArrayList<>();
	// 3.payment Relationship.
	@ManyToOne(cascade = CascadeType.ALL)
	private Payment payment;
}
