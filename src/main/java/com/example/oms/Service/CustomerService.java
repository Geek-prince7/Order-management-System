package com.example.oms.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oms.Dao.CustomersRepo;
import com.example.oms.Entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	CustomersRepo customersRepo;
	
	public Object addCustomer(String name,String email,String phone_no) {
		Customer newCustomer=new Customer();
		newCustomer.setCustomerName(name);
		newCustomer.setEmail(email);
		newCustomer.setPhoneNo(phone_no);
		newCustomer.setCategory("regular");
		newCustomer.setOrderCount(0);
		return customersRepo.save(newCustomer);
	}
	

}
