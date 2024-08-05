package com.app.product_service.mapper;
// src/main/java/com/app/openai_gen_springBoot/mapper/ProductMapperImpl.java

import com.app.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public com.app.product_service.generated.model.Product toGeneratedProduct(Product product) {
        if (product == null) {
            return null;
        }
        return new com.app.product_service.generated.model.Product()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription());

    }

    @Override
    public List<com.app.product_service.generated.model.Product> toGeneratedProductList(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::toGeneratedProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product generatedProductToProduct(com.app.product_service.generated.model.Product product) {
        if (product == null) {
            return null;
        }
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        return newProduct;
    }
}