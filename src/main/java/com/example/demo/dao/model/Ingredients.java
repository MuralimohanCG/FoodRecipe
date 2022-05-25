package com.example.demo.dao.model;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "INGRIDIENT")
public class Ingredients {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 3, message = "The field must be at least 3 characters")
    @Length(max = 50, message = "The field must be less than 50 characters")
	private String name;
	private int quantityInGrms;
	
	
	
	public Ingredients(int id, String name, int quantityInGrms) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.quantityInGrms=quantityInGrms;
		
	}
	public Ingredients() {
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
	public int getQuantityInGrms() {
		return quantityInGrms;
	}
	public void setQuantityInGrms(int quantityInGrms) {
		this.quantityInGrms = quantityInGrms;
	}
	
	
}
