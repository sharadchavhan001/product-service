package com.app.product_service.service;

import com.app.product_service.exception.ProductNotFoundException;
import com.app.product_service.mapper.ProductMapper;
import com.app.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    private com.app.product_service.generated.model.Product product;
    private com.app.product_service.model.Product internalProduct;


    @BeforeEach
    public void setUp() {
        product = new com.app.product_service.generated.model.Product();
        product.setId("1");
        product.setName("John Doe");
        product.setPrice(220f);
        product.setDescription("1234567890");
        internalProduct = new com.app.product_service.model.Product("1", "John Doe", 220f, "1234567890");

    }

    @Test
    public void testSaveProduct() {
        when(productMapper.generatedProductToProduct(product)).thenReturn(internalProduct);
        when(productRepository.save(internalProduct)).thenReturn(internalProduct);
        when(productMapper.toGeneratedProduct(internalProduct)).thenReturn(product);

        com.app.product_service.generated.model.Product savedProduct = productService.addProduct(product);

        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(any(String.class))).thenReturn(Optional.of(internalProduct));
        when(productMapper.toGeneratedProduct(internalProduct)).thenReturn(product);

        com.app.product_service.generated.model.Product foundProduct = productService.getProductById("1");

        assertNotNull(foundProduct);
        assertEquals(product.getId(), foundProduct.getId());
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById("1");
        });
    }
}