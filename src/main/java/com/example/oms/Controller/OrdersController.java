package com.example.oms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oms.Service.OrderService;

@RestController()
@RequestMapping(value = "/order")
public class OrdersController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/get")
	public Object getOrders(@RequestParam(required = false)Integer order_id,@RequestParam(required = false)Integer customer_id,@RequestParam(required = false)String cust_name) {
		return orderService.getOrderDetails(order_id, customer_id, cust_name);
		
	}
	@PostMapping("/create")
	public Object createOrder(@RequestParam(required = true)Integer product_id,@RequestParam(required = true)Integer cust_id,@RequestParam(required = true)Integer quantity) {
		return orderService.createOrder(product_id,cust_id,quantity);
	}

}
