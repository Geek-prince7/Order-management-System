package com.example.oms.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.oms.Entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer>{
	
	@Query(value = "select ord.order_id"
			+ ",ord.customer_id"
			+ ",ord.product_id"
			+ ",ord.quantity"
			+ ",ord.ordered_on"
			+ ",cm.customer_name"
			+ ",pd.product_name"
			+ ",(pd.price*ord.quantity) as product_price"
			+ ",ord.discounted_price "
			+ "from orders ord "
			+ "left join customers cm on ord.customer_id=cm.customer_id "
			+ "left join products pd on ord.product_id=pd.product_id "
			+ "WHERE (:order_id is null or ord.order_id=:order_id) "
			+ "AND (:customer_id is null or ord.customer_id=:customer_id) "
			+ "AND (:cust_name is null or cm.customer_name=:cust_name) order by ord.order_id",nativeQuery = true)
	public List<OrderDetails> getOrderDetails(Integer order_id,Integer customer_id,String cust_name);

}
