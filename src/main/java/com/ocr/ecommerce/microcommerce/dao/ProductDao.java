package com.ocr.ecommerce.microcommerce.dao;

import com.ocr.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByNameLike(@Param("name") String name);

    List<Product> findBySellPriceGreaterThan(int price);

    @Query("SELECT p FROM Product p where p.buyPrice > :price")
    List<Product> getProductsWithBuyPriceGreaterThan(@Param("price") int price);
}
