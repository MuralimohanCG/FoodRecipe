package com.example.demo.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.model.Items;
import com.example.demo.exception.FoodItemNotFoundException;
import com.example.demo.service.FoodService;

@Service
public class FoodRecipeDaoImp implements FoodService{
	
	private static final Logger logger = LoggerFactory.getLogger(FoodRecipeDaoImp.class);
	
	@Autowired
	public FoodRepository respository;
	
	public String dateFormat() {
		//Date and Time
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = dateTime.format(formatter);
        
        return date;
	}
	
	/**
     * @param items contain api request data
     * @return contain api response data
     */
	
	public Items saveItems(Items items) throws FoodItemNotFoundException {
		 if (Objects.isNull(items)) {
	          logger.error("Recipe Item creation exception");
	          throw new FoodItemNotFoundException("Recipe Item creation exception");
		 }
		
		String date = dateFormat();
	    items.setCreateDate(date);
		
		respository.save(items);
		logger.info("Food Itea was created");
		return items;
	}
	
	/**
     * Method to fetch all the persisted recipes
     * @return All recipes present in records
     */
	public List<Items> getAllItems(){
	
		List<Items> itemList = respository.findAll();
		logger.info("All recipe item list " +itemList.size());
		
		return itemList;
	}
	
	/**
     * Method to fetch the persisted recipe based on id.
     * if not found then user custom message is returned.
     *
     * @param id of recipe to select from records
     * @return the recipe detail with given id
     */
	public Items getByRecipe(int Id) throws FoodItemNotFoundException{
		
		Items getRecipe = respository.findById(Id).orElseThrow(()
				->new FoodItemNotFoundException("No Recipe record exist for given id "+Id));
		
		return getRecipe;
		 
	}
	
	/**
     * Method to delete the recipe with provided id.
     * if not found then user custom message is returned.
     *
     * @param id to delete the recipe from records
     * @return void
     */
	public int deleteItem(int Id) throws FoodItemNotFoundException {
		 logger.info("Recipe item to delete" + Id);
		 
		 respository.findById(Id).orElseThrow(()
				 ->new FoodItemNotFoundException("Recipe not Found by the Id "+Id));
		 
		 respository.deleteById(Id);
		
		 logger.info("Recipe item was deleted" + Id);
		 
		 return Id;
	}
	
	/**
     * If recipe not found then user custom error message is returned.
     *
     * @param Items contain api request data
     * @return contain api response data
     */
	public Items updateItems(int Id, Items items) throws FoodItemNotFoundException {
		 Optional<Items> recipeItem = respository.findById(Id);
		 Items itemsData = respository.findById(Id).orElseThrow(()
				 ->new FoodItemNotFoundException("Recipe not Found by the Id "+Id));
		 logger.info("Recipe item to update", recipeItem.isPresent());
		String date = dateFormat();
		itemsData.setCategory(items.getCategory());
		itemsData.setName(items.getName());
		itemsData.setQuantity(items.getQuantity());
		itemsData.setCookInstrctions(items.getCookInstrctions());
		itemsData.setIngredients(items.getIngredients());
		items.setCreateDate(date);
		
		respository.save(items);
		logger.info("Recipe item updated", Boolean.TRUE);
		return items;
	}
	

}