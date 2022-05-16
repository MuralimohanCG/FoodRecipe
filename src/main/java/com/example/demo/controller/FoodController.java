package com.example.demo.controller;


import java.util.List;

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



@RestController
public class FoodController {
	
	@Autowired
	public FoodService foodServices;
	
	@PostMapping("/saveItems")
	public String saveItems(@RequestBody Items items) {
		if(items == null) {
			throw new FoodItemNotFoundException("Invalid input values");
		}
		foodServices.saveItems(items);
	   return "Saved values...";
	}
	
	@GetMapping("/getAllItems")
	@ResponseBody
	public ResponseEntity<List<Items>> getAllItems(){
		List<Items> items= foodServices.getAllItems();
		return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
	}
	
	@GetMapping("/getByCatgeroy/{category}")
	public List<Items> getByCatgeroy(@PathVariable String category){
		 if(category == null || category.toLowerCase().equals("null"))  {
			throw new FoodItemNotFoundException("Invalid Category Code");
		}
		return foodServices.getByCatgeroy(category);
	}
	
	@PutMapping("/updateItems/{Id}")
	public String updateItems(@PathVariable int Id, @RequestBody Items items){
		if(Id <= 0) {
			throw new FoodItemNotFoundException("Invalid Items Object");
		}
		return foodServices.updateItems(Id,items);
	}
	
	@DeleteMapping("/deleteItem/{Id}")
	public String deleteItem(@PathVariable int Id) {
		if(Id <= 0) {
			throw new FoodItemNotFoundException("Invalid Item Id");
		}
		return foodServices.deleteItem(Id);
	}

}
