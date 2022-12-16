package com.example.oms.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oms.Dao.CustomersRepo;
import com.example.oms.Dao.OrderDetailsRepo;
import com.example.oms.Dao.OrdersRepo;
import com.example.oms.Dao.ProductsRepo;
import com.example.oms.Entity.Customer;
import com.example.oms.Entity.OrderDetails;
import com.example.oms.Entity.Orders;
import com.example.oms.Entity.Products;

@Service
public class OrderService {
	
	@Autowired
	OrdersRepo ordersRepo;
	
	@Autowired
	OrderDetailsRepo orderDetailsRepo;
	
	@Autowired
	CustomersRepo customersRepo;
	
	@Autowired
	ProductsRepo productsRepo;
	
	public Object getOrderDetails(Integer order_id,Integer customer_id,String cust_name) {
		List<OrderDetails> output=orderDetailsRepo.getOrderDetails(order_id, customer_id, cust_name);
		return output;
		
	}
	
	public Object createOrder(Integer product_id,Integer cust_id,Integer quantity) {
		//get customer order count
		Optional<Customer> existingCustomer=customersRepo.findById(cust_id);
		
		//get the product
		Optional<Products> product=productsRepo.findById(product_id);
		
		
		Products prod=product.get();
		Customer existing=existingCustomer.get();
		if(prod==null || existing==null)
		{
			return "product/customer doesn't exist";
		}
		
		
		//update quantity in stock
		int quantityInStock=prod.getQuantity();
		if(quantityInStock-quantity<0)
		{
			return "we only have "+quantityInStock+" items";
		}
		prod.setQuantity(quantityInStock-quantity);
		productsRepo.save(prod);
	
		
		//check if customer is about to be gold customer or platinum
		existing.setOrderCount(existing.getOrderCount()+1);
		existing.setCategory(setCategory(existing.getOrderCount()));
		customersRepo.save(existing);
		
		Double price;
		if(existing.getCategory()=="platinum")
		{
			price=0.8*prod.getPrice();  //20% discount to platinum
		}
		else if(existing.getCategory()=="gold")
		{
			price=0.9*prod.getPrice(); //10% discount
		}
		else
		{
			price=prod.getPrice(); //no discount
		}
		
		
		Orders newOrder=new Orders();
		newOrder.setCustomerId(cust_id);
		newOrder.setProductId(product_id);
		newOrder.setQuantity(quantity);
		newOrder.setDiscountedPrice(price*quantity);
		return ordersRepo.save(newOrder);
		
	}
	public String setCategory(Integer OrderCount) {
		if(OrderCount<10) {
			return "regular";
		}
		else if(OrderCount<20) {
			return "gold";
		}
		else {
			return "platinum";
		}
		
		
		
	}

}
