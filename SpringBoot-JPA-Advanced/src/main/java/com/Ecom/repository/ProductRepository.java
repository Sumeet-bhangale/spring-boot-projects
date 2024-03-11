package com.Ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Ecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByName(String name);
	
	List<Product> findAllByPrice(double price);
	
	// search by name like
	List<Product> findAllByNameContaining( String name);
	
	
		// SQL queries (Native Queries)
		// Below SQL will search all products that start with name
		@Query(value = "SELECT * FROM products_db WHERE name LIKE :name%", nativeQuery = true)
		List<Product> abc(String name);
	
}
