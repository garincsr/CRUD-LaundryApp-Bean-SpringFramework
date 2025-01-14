package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Product;
import jakarta.persistence.*;

import java.util.List;

public class ProductRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("enigma-persistence");
    EntityManager em = emf.createEntityManager();

    public void save(Product payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    public void merge (Product payload, Integer id){
        Product existingProduct = em.find(Product.class, id);
        if (existingProduct == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(payload);
        tx.commit();
    }

    public List<Product> findAll(){
        return em.createQuery("select c from Product c", Product.class).getResultList();
    }

    public Product findById(Integer id){
        return em.find(Product.class, id);
    }

    public void delete(Integer id){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Product.class, id));
        tx.commit();
    }
}
