package com.ocr.ecommerce.microcommerce.controller;

import com.ocr.ecommerce.microcommerce.dao.ProductDao;
import com.ocr.ecommerce.microcommerce.exception.ProductNotFoundException;
import com.ocr.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // products
    @GetMapping(value = "products")
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    // products/{id}
    @GetMapping(value = "products/{id}")
    public Product getProduct(@PathVariable int id) throws ProductNotFoundException {
        Product product = productDao.findById(id).orElse(null);
        if (product == null)
            throw new ProductNotFoundException(String.format("Can't find product with id: %s", id));

        return product;
    }

    // products/searchByName/{name}
    @GetMapping(value = "products/searchByName/{name}")
    public List<Product> searchByName(@PathVariable String name) {
        return productDao.findByNameLike("%" + name + "%");
    }

    // products
    @PostMapping(value = "products")
    public void saveProduct(@Valid @RequestBody Product product) {
        productDao.save(product);
    }

    // products/buyPriceSuperior/{price}
    @GetMapping(value = "products/buyPriceSuperior/{price}")
    public List<Product> getProductsWithBuyingPriceHigherThen(@PathVariable int price) {
        return productDao.getProductsWithBuyPriceGreaterThan(price);
    }

    // products/sellPriceSuperior/{price}
    @GetMapping(value = "products/sellPriceSuperior/{price}")
    public List<Product> getProductsWithSellingPriceHigherThen(@PathVariable int price) {
        return productDao.findBySellPriceGreaterThan(price);
    }

    // products
    @PutMapping(value = "products")
    public void updateProduct(@Valid @RequestBody Product product) {
        productDao.save(product);
    }

    // products/{id}
    @DeleteMapping(value = "products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDao.deleteById(id);
    }
}
