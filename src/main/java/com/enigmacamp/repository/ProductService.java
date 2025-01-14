package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Product;

import java.util.List;

public interface ProductService {
    Product create(Product payload);
    Product update(Product paylod);
    List<Product> getAll();
    Product get(Integer id);
    Boolean delete(Integer id);
}
