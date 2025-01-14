package com.enigmacamp.repository.impl;

import com.enigmacamp.entitiy.Product;
import com.enigmacamp.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
private final EntityManager em;

    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void create(Product payload) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    @Override
    public void update(Product payload, Integer id) {
        Product existingProduct = em.find(Product.class, id);
        if (existingProduct == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(payload);
        tx.commit();
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("select c from Product c", Product.class).getResultList();
    }

    @Override
    public Product get(Integer id) {
        return em.find(Product.class, id);
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Product.class, id));
        tx.commit();
    }
}
