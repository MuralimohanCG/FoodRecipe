package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.model.Items;
import com.example.demo.exception.FoodItemNotFoundException;
import com.example.demo.service.FoodService;

/*
 * 
 Food Controller 
 */

@RestController
public class FoodController {
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	public FoodService foodServices;
	
	/*
	 @ Required as Items
	 @ Return save Item
	 */
	
	@PostMapping("/saveItems")
	public ResponseEntity<Items> saveItems(@RequestBody Items items) throws FoodItemNotFoundException  {
		logger.info("In SaveItems::");
		items =foodServices.saveItems(items);
		logger.info("Recipe Record Created");
		return new ResponseEntity<Items>(items, HttpStatus.OK);
	}
	/*
	 return response entity as Items
	 */
	@GetMapping("/getAllItems")
	@ResponseBody
	public ResponseEntity<List<Items>> getAllItems(){
		logger.info("In getAllItems::");
		List<Items> items= foodServices.getAllItems();
		logger.info("Recipe Items list  Size ",items.size());
		return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
	}
	
	/*
	 @Path Long id
	 Return List of Items
	 */

	@GetMapping("/getByRecipe/{Id}")
	public ResponseEntity<Items> getByRecipe(@PathVariable int Id) throws FoodItemNotFoundException{
		logger.info("In getByRecipe::" + Id);
		Items getRecipe = foodServices.getByRecipe(Id);
		
		return new ResponseEntity<Items>(getRecipe, HttpStatus.OK);
	}
	
	/*
	 @Path  id, Items
	 return update item list
	 */
	@PutMapping("/updateItems/{Id}")
	public ResponseEntity<Items> updateItems(@PathVariable int Id, @RequestBody Items items) 
			throws FoodItemNotFoundException{
		logger.info("In updateItems::");
		foodServices.updateItems(Id,items);
		logger.info("Recipe item record Updated ");
		
		return new ResponseEntity<Items>(items, HttpStatus.OK); 
	}
	
	/*
	  @param id to delete the recipe item
	 */
	@DeleteMapping("/deleteItem/{Id}")
	public ResponseEntity<String> deleteItem(@PathVariable int Id) {
		logger.info("In deleteItem::");
		foodServices.deleteItem(Id);
		logger.info("Recipe item record delete ");
		return new ResponseEntity<String>("Recipe Item record deleted"+String.valueOf(Id), HttpStatus.OK);
	}

}
