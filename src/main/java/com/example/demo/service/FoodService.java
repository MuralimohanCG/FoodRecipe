package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.dao.model.Items;
import com.example.demo.exception.FoodItemNotFoundException;




public interface FoodService {	
	
	public Items saveItems(Items items) throws FoodItemNotFoundException;

	public List<Items> getAllItems();
	
	public Items getByRecipe(int Id) throws FoodItemNotFoundException;
	
	public int deleteItem(int Id) throws FoodItemNotFoundException;
	
	public Items updateItems(int Id, Items items) throws FoodItemNotFoundException;
	
}
