package com.example.oms.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.oms.Entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Integer> {
	// save is already implemented so i am only implementing get query
	@Query(value = "select * from products where "
			+ "(:prod_id is null or product_id=:prod_id) "
			+ "AND (:prod_name is null or product_name=:prod_name) "
			+ "AND (:quantity is null or quantity=:quantity) "
			+ "AND (:price is null or price=:price)",nativeQuery = true)
	public List<Products> getProduct(Integer prod_id,String prod_name,Integer quantity,Double price);

}
