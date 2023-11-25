package com.kp.ProductService.service;

import com.kp.ProductService.model.ProductRequest;
import com.kp.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
