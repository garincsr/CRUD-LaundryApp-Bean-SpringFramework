package com.enigmacamp.service.impl;

import com.enigmacamp.entitiy.Product;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = new ProductRepository();

    @Override
    public void create(Product payload) {
        productRepository.save(payload);
    }

    @Override
    public void update(Product payload, Integer id) {
        productRepository.merge(payload, id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
