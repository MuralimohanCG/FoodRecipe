package com.example.demo.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Entity
@Component

public class Items {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String category;
	private String CreateDate;
	private int quantity;
	private String ingredients;
	private String cookInstrctions;
	
	public Items(int id, String name, String category, String CreateDate, int quantity, String ingredients,String cookInstrctions) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.category=category;
		this.CreateDate=CreateDate;
		this.quantity=quantity;
		this.ingredients=ingredients;
		this.cookInstrctions=cookInstrctions;
	}
	public Items() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getCookInstrctions() {
		return cookInstrctions;
	}
	public void setCookInstrctions(String cookInstrctions) {
		this.cookInstrctions = cookInstrctions;
	}
	
	
	

}
