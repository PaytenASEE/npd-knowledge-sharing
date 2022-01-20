package com.example.kss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.kss.model.Product;

@RepositoryRestResource(exported = true, path = "product", collectionResourceRel = "products")
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
