package com.app.product_service.delegate;

import com.app.product_service.generated.api.ProductsApiDelegate;
import com.app.product_service.generated.model.Product;
import com.app.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDelegateImpl implements ProductsApiDelegate {
    @Autowired
    ProductService productService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ProductsApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteProductById(String productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Product> getProductById(String productId) {
        return new ResponseEntity<>(productService.getProductById(productId), null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProductById(String productId, Product product) {
        return new ResponseEntity<>(productService.updateProduct(productId, product), null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> productsPost(Product product) {
        return new ResponseEntity<>(productService.addProduct(product), null, HttpStatus.CREATED);
    }
}
