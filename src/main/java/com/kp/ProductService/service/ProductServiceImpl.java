package com.kp.ProductService.service;

import com.kp.ProductService.Exception.ProductServiceCustomException;
import com.kp.ProductService.entity.Product;
import com.kp.ProductService.model.ProductRequest;
import com.kp.ProductService.model.ProductResponse;
import com.kp.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product..");
		Product product = Product
				.builder()
				.productName(productRequest.getName())
				.price(productRequest.getPrice())
				.quantity(productRequest.getQuantity())
				.build();

		productRepository.save(product);
		log.info("Product Created");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		Product product = productRepository
				.findById(productId)
				.orElseThrow(() -> new ProductServiceCustomException(
						"Product with given id not found",
						"PRODUCT_NOT_FOUND"
				));
		ProductResponse productResponse = new ProductResponse();
		copyProperties(product, productResponse);

		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		Product product = productRepository
				.findById(productId)
				.orElseThrow(() -> new ProductServiceCustomException(
						"Products with product this id are not found",
						"NOT_FOUND"
				));
		if (product.getQuantity() < quantity) {
			throw new ProductServiceCustomException(
					"Product does not have sufficient quantity",
					"INSUFFICIENT_QUANTITY"
			);
		}
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		log.info("Product quantity updated successfully");
	}
}
