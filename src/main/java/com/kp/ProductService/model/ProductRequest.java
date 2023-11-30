package com.kp.ProductService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
	private String name;
	private long price;
	private long quantity;
}
