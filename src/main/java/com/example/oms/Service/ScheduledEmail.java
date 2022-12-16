package com.example.oms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.oms.Dao.CustomersRepo;
import com.example.oms.Entity.Customer;

@Service
public class ScheduledEmail {
	@Autowired
	CustomersRepo customersRepo;
	
	@Scheduled(cron = "0 03 14 * * *",zone = "GMT+5:30") //mail sent everyday at 14:00 afternoon
//	@Scheduled(fixedRate = 1000*60*60)  //mail every hour to customer
	public void ScheduledEmails() {
		System.out.println("working-->");
		List<Customer> customers =customersRepo.ScheduledEmailCustomer();
		if(!customers.isEmpty()) {
			for(Customer current_cust:customers) {
				if(current_cust.getEmail()!=null)
				{
					sendMail(current_cust.getCustomerName(), current_cust.getEmail());
				}
			}
		}
		
	}
	
	public void sendMail(String cust_name,String cust_email) {
		System.out.println("mail sent to "+cust_name+" at :"+cust_email);
		
	}

}
