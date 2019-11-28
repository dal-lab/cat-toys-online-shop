package com.dallab.cattoys.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findAllByDeleted(boolean deleted);

    Optional<Product> findById(Long id);

    Product save(Product product);

}
