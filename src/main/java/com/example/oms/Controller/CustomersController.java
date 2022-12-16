package com.example.oms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oms.Service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomersController {
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("/add")
	public Object addCustomer(@RequestParam(required = true)String name,@RequestParam(required = false)String email,@RequestParam(required = true)String phone_no) {
		return customerService.addCustomer(name, email, phone_no);
		
		
	}
	

}
