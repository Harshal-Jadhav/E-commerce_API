package com.project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	private Integer Id;
	private String productName;
	private String productDescription;
	private String productBrand;
	private Double rating;
	private Double productMarketPrice;
	private Double productSellingPrice;
	private Integer Quantity;
}
