package com.example.oms.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.oms.Entity.Customer;

public interface CustomersRepo extends JpaRepository<Customer, Integer> {
	
	@Query(value = "select * from customers where order_count=9 or order_count=19",nativeQuery = true)
	public List<Customer> ScheduledEmailCustomer();

}
