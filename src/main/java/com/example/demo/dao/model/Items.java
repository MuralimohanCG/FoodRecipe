package com.example.demo.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Component
@Table(name = "Recipe")
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 3, message = "The field must be at least 3 characters")
	@Length(max = 50, message = "The field must be less than 50 characters")
	private String name;
	private String category;
	
	private String createDate;
	
	private int quantity;
	
	@NotNull(message = "Recipe Ingredients required")
    @NotEmpty(message = "Recipe Ingredients Required")
	@OneToMany(targetEntity = Ingredients.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ref_id", referencedColumnName = "id", nullable = false)
	private List<Ingredients> ingredients;
	
	 @NotNull(message = "Recipe Cooking instructions are required")
	 @NotBlank(message = "Recipe Cooking instructions  must be of length 1 to 1500")
	 @Size(min = 1, max = 1500, message = "Recipe Cooking instructions must be of length 1 to 1500")
	private String cookInstrctions;
	
	public Items(int id, String name, String category, String createDate, int quantity, List<Ingredients> ingredients,String cookInstrctions) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.category=category;
		this.createDate=createDate;
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
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<Ingredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	public String getCookInstrctions() {
		return cookInstrctions;
	}
	public void setCookInstrctions(String cookInstrctions) {
		this.cookInstrctions = cookInstrctions;
	}
	
	
	

}
