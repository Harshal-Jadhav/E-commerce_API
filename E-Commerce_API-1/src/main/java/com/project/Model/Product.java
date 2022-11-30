package com.project.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String productName;
	private String productDescription;
	private String productBrand;
	private Double rating;
	private Double productMarketPrice;
	private Double productSellingPrice;
	private Integer stock;

//	Relationships here.

	// 1.Details Relationship
	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private OrderDetails details;

	// 2.Category Details
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
