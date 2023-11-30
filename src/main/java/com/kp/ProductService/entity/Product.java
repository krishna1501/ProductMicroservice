package com.kp.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "PRICE")
	private long price;
	@Column(name = "QUANTITY")
	private long quantity;
}
