package com.app.product_service.mapper;

import com.app.product_service.model.Product;
import java.util.List;

public interface ProductMapper {
    com.app.product_service.generated.model.Product toGeneratedProduct(Product product);

    List<com.app.product_service.generated.model.Product> toGeneratedProductList(List<Product> products);

    Product generatedProductToProduct(com.app.product_service.generated.model.Product product);
}
