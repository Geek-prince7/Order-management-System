package com.example.oms.Dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.oms.Entity.Orders;


public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
