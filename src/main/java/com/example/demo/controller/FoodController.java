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
	 Input as Items
	 saveItems 
	 Output data saved in H2
	 */
	
	@PostMapping("/saveItems")
	public String saveItems(@RequestBody Items items) {
		logger.info("In SaveItems::");
		
		if(items == null) {
			throw new FoodItemNotFoundException("Invalid input values");
		}
		foodServices.saveItems(items);
	   return "Saved values...";
	}
	/*
	 return response entity as Items
	 */
	@GetMapping("/getAllItems")
	@ResponseBody
	public ResponseEntity<List<Items>> getAllItems(){
		logger.info("In getAllItems::");
		List<Items> items= foodServices.getAllItems();
		return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
	}
	
	/*
	 Input category Id
	 Return List of Items
	 */

	@GetMapping("/getByCatgeroy/{category}")
	public List<Items> getByCatgeroy(@PathVariable String category){
		logger.info("In getByCatgeroy::");

		if(category == null || category.toLowerCase().equals("null"))  {
			throw new FoodItemNotFoundException("Invalid Category Code");
		}
		return foodServices.getByCatgeroy(category);
	}
	
	/*
	 Input int id, Items
	 update data in H2
	 */
	@PutMapping("/updateItems/{Id}")
	public String updateItems(@PathVariable int Id, @RequestBody Items items){
		logger.info("In updateItems::");
		if(Id <= 0) {
			throw new FoodItemNotFoundException("Invalid Items Object");
		}
		return foodServices.updateItems(Id,items);
	}
	
	/*
	 Input int id 
	 return delete id from Items
	 */
	@DeleteMapping("/deleteItem/{Id}")
	public String deleteItem(@PathVariable int Id) {
		logger.info("In deleteItem::");
		if(Id <= 0) {
			throw new FoodItemNotFoundException("Invalid Item Id");
		}
		return foodServices.deleteItem(Id);
	}

}
