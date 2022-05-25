package com.example.demo;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.dao.FoodRecipeDaoImp;
import com.example.demo.dao.model.Ingredients;
import com.example.demo.dao.model.Items;
import com.example.demo.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class FoodControllersTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@MockBean
	private FoodService foodService;
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	Ingredients ingred= new Ingredients(1,"Oil",10);
	Ingredients ingred1= new Ingredients(2,"Ghee",10);
	Ingredients ingred2= new Ingredients(3,"Paneer",100);
	List<Ingredients> list1 =  new ArrayList<Ingredients>();
	
	Items items_1;
	Items items_2;
	Items items_4;
	
	@Before(value = "")
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	void testSaveItems() throws Exception {
		list1.add(ingred);
		list1.add(ingred1);
		list1.add(ingred2);
		items_1 = new Items(1L,"Paneer Biryani","Veg","13-05-2022 10:12",3,list1,"add Vegitables");
		Mockito.when(foodService.saveItems(ArgumentMatchers.any())).thenReturn(items_1);
        String json = mapper.writeValueAsString(items_1);
        mockMvc.perform(post("/saveItems")
        		.contentType(MediaType.APPLICATION_JSON)
        		.characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());
        		//.andExpect(jsonPath("$[0].name", Matchers.equalTo("Paneer Biryani")));
	}

	@Test
	void testGetAllItems() throws Exception {
		list1.add(ingred);
		list1.add(ingred1);
		list1.add(ingred2);
		items_2 = new Items(2L,"Veg Biryani","Veg","13-05-2022 11:12",5,list1,"oil, ghee,masala ");
		List<Items> itemsList = new ArrayList<Items>();
		itemsList.add(items_1);
		itemsList.add(items_2);
		
		Mockito.when(foodService.getAllItems()).thenReturn(itemsList);
		
		mockMvc.perform(get("/getAllItems"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

	@Test
	void testGetByRecipe() throws Exception {
		List<Items> itemsList = new ArrayList<Items>();
		Optional<Items> recipeItem = Optional.of(items_2);
		itemsList.add(items_1);
		itemsList.add(items_2);
		
		recipeItem.ifPresent(itemsList::add);
		
		Mockito.when(foodService.getByRecipe(ArgumentMatchers.anyLong())).thenReturn(recipeItem);
		
		mockMvc.perform(get("/getByRecipe/2"))
		.andExpect(status().isOk());
		
	}

	@Test
	void testUpdateItems() throws Exception {
		list1.add(ingred);
		list1.add(ingred1);
		list1.add(ingred2);
	   items_4 = new Items(2L,"Mutton Biryani","Non-Veg","13-05-2022 11:12",7,list1,"oil, ghee,masala ");
		
		Mockito.when(foodService.updateItems(ArgumentMatchers.anyLong(),ArgumentMatchers.any())).thenReturn(items_4);
        String json = mapper.writeValueAsString(items_4);
        mockMvc.perform(put("/updateItems/2")
        		.contentType(MediaType.APPLICATION_JSON)
        		.characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());
	}

	@Test
	void testDeleteItem() throws Exception {
        MvcResult requestResult = mockMvc.perform(delete("/deleteItem/1"))
                .andExpect(status().isOk())
                .andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, status().isOk());
	}

}
