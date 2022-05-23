package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.model.Items;




public interface FoodService {	
	
	public String saveItems(Items items);

	public List<Items> getAllItems();
	
	public List<Items> getByCatgeroy(String Category);
	
	public String deleteItem(int Id);
	
	public String updateItems(int Id, Items items);
	
}
