package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.dao.model.Items;
import com.example.demo.exception.FoodItemNotFoundException;




public interface FoodService {	
	
	public Items saveItems(Items items) throws FoodItemNotFoundException;

	public List<Items> getAllItems();
	
	public Optional<Items> getByRecipe(Long Id) throws FoodItemNotFoundException;
	
	public void deleteItem(int Id) throws FoodItemNotFoundException;
	
	public Items updateItems(Long Id, Items items) throws FoodItemNotFoundException;
	
}
