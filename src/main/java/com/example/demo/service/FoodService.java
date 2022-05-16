package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FoodRepository;
import com.example.demo.dao.model.Items;



@Component
@Service
public class FoodService {

	@Autowired
	public FoodRepository respository;
	
	public String saveItems(Items items) {
		
		//Date and Time
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = dateTime.format(formatter);
		System.out.println("Date::"+date);
		items.setCreateDate(date);
		
		respository.save(items);
		return "Saved food the Item.";
	}

	public List<Items> getAllItems(){
		return respository.findAll();
	}
	
	public List<Items> getByCatgeroy(String Category){
		return respository.findByCategory(Category);
	}
	
	public String deleteItem(int Id) {
		respository.deleteById(Id);
		return "Food Item was deleted ";
	}
	
	public String updateItems(int Id, Items items) {
		
		//Date and Time
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = dateTime.format(formatter);
		System.out.println("Date::"+date);
		items.setCreateDate(date);
		
		Items itemsData = respository.findById(Id);
		itemsData.setCategory(items.getCategory());
		itemsData.setName(items.getName());
		itemsData.setQuantity(items.getQuantity());
		itemsData.setCookInstrctions(items.getCookInstrctions());
		itemsData.setIngredients(items.getIngredients());
		itemsData.setCreateDate(date);
		
		respository.save(itemsData);
		return "Saved food the Item.";
	}
	
}
