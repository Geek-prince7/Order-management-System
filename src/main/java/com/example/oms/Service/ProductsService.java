package com.example.oms.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oms.Dao.ProductsRepo;
import com.example.oms.Entity.Products;

@Service
public class ProductsService {
	
	@Autowired
	ProductsRepo productsRepo;
	
	
	//get product using filter
	public Object getProductByFilter(Integer prod_id,String prod_name,Integer quantity,Double price) {
		List<Products> output=productsRepo.getProduct(prod_id, prod_name, quantity, price);
		return output;
	}
	
	
	//add a new product
	public Object addProduct(String product_name,Integer quantity,Double price) {
		Products newProduct=new Products();
		newProduct.setPrice(price);
		newProduct.setProductName(product_name);
		newProduct.setQuantity(quantity);
		return productsRepo.save(newProduct);
		
	}
	
	//update product's quantity in stock or price 
	public Object UpdateProduct(Integer prod_id,Integer quantity,Double price) {
		if(quantity==null && price==null)
		{
			return "enter quantity/price";
		}
		
		Optional<Products> prod=productsRepo.findById(prod_id);
		Products existing_product=prod.get();
		if(existing_product!=null)
		{
			if(quantity!=null)
			{
				existing_product.setQuantity(quantity);
			}
			if(price!=null)
			{
				existing_product.setPrice(price);
			}
			return productsRepo.save(existing_product);
			
		}
		
		return "Product not found";
		
	}
	
	
	
	
	
	
	


}
