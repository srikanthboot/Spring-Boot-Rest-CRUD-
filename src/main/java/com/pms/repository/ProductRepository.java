package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> 
{
List<Product> findByManufacturer(String manufacturer);
}
