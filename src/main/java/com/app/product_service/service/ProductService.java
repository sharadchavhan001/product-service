package com.app.product_service.service;

import com.app.product_service.exception.ProductNotFoundException;
import com.app.product_service.generated.model.Product;
import com.app.product_service.mapper.ProductMapper;
import com.app.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public List<Product> getAllProducts() {
        List<com.app.product_service.model.Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            return productMapper.toGeneratedProductList(products);
        } else {
            throw new ProductNotFoundException("Products not found"); // Custom exception
        }
    }

    public Product addProduct(Product product) {
        return productMapper.toGeneratedProduct(productRepository.save(productMapper.generatedProductToProduct(product)));
    }

    public Product updateProduct(String productId, Product product) {
        com.app.product_service.model.Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(!Objects.equals(product.getName(), "") && product.getName() != null ? product.getName() : existingProduct.getName()); // Update fields as needed
            existingProduct.setPrice(!Objects.equals(product.getPrice(), "") && product.getPrice() != null ? product.getPrice() : existingProduct.getPrice()); // Update fields as needed
            existingProduct.setDescription(!Objects.equals(product.getDescription(), "") && product.getDescription() != null ? product.getDescription() : existingProduct.getDescription()); // Update fields as needed
            return productMapper.toGeneratedProduct(productRepository.save(existingProduct));
        } else {
            throw new ProductNotFoundException("Product not found"); // Custom exception
        }
    }

    public Product getProductById(String productId) {
        com.app.product_service.model.Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return productMapper.toGeneratedProduct(product);
        } else {
            throw new ProductNotFoundException("Product not found"); // Custom exception
        }
    }

    public Void deleteProduct(String productId) {
        com.app.product_service.model.Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        } else {
            throw new ProductNotFoundException("Product not found"); // Custom exception
        }
        return null;
    }
}
