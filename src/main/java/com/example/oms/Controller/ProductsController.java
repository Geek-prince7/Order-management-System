package com.example.oms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.oms.Service.ProductsService;

@RestController
@RequestMapping(value = "/product")
public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/get")
	public Object getProducts(@RequestParam(required = false)Integer prod_id,@RequestParam(required = false)String prod_name,@RequestParam(required = false)Integer quantity,@RequestParam(required = false)Double price) {
		return productsService.getProductByFilter(prod_id, prod_name, quantity, price);
		
	}
	
	//to add one product at a time
	@PostMapping("/add")
	public Object addProduct(@RequestParam(required = true)String product_name,@RequestParam(required = true)Integer quantity,@RequestParam(required = true)Double price) {
		return productsService.addProduct(product_name, quantity, price);
	}
	
	//to update product's quantity in stock or if price updated
	@PostMapping("/update")
	public Object UpdateProduct(@RequestParam(required = true)Integer product_id,@RequestParam(required = false)Integer quantity,@RequestParam(required = false) Double price) {
		return productsService.UpdateProduct(product_id, quantity,price);
	}
	
	
		
	
	

}
