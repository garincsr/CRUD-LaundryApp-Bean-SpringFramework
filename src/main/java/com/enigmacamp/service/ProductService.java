package com.enigmacamp.service;

import com.enigmacamp.entitiy.Product;

import java.util.List;

public interface ProductService {
    void create(Product payload);
    void update(Product payload, Integer id);
    List<Product> getAll();
    Product get(Integer id);
    void delete(Integer id);
}
