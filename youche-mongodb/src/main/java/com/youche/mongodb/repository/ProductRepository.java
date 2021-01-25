package com.youche.mongodb.repository;

import com.youche.mongodb.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameLike(String name);
}
