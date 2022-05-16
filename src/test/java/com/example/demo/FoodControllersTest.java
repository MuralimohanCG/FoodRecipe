package com.example.demo;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.dao.model.Items;
import com.example.demo.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
class FoodControllersTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@MockBean
	private FoodService foodService;
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	Items items_1 = new Items(1,"Paneer Biryani","Veg","13-05-2022 10:12",3,"Vegitables paneer added","oils, GHEE ");
	Items items_2 = new Items(2,"Veg Biryani","Veg","13-05-2022 11:12",5,"Vegitables added","oil, ghee,masala ");
	Items items_3 = new Items(3,"Chicken Biryani","Non-Veg","13-05-2022 10:42",5,"chicken,oil, ","oils,ghee,masala,chicken ");
	
	@Before(value = "")
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	

	@Test
	void testSaveItems() throws Exception {
			
		Mockito.when(foodService.saveItems(ArgumentMatchers.any())).thenReturn("Saved");
        String json = mapper.writeValueAsString(items_1);
        mockMvc.perform(post("/saveItems")
        		.contentType(MediaType.APPLICATION_JSON)
        		.characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk()) 
                .andExpect(jsonPath("$", Matchers.equalTo("Saved values...")));
	}

	@Test
	void testGetAllItems() throws Exception {
		
		List<Items> itemsList = new ArrayList<Items>();
		itemsList.add(items_1);
		itemsList.add(items_2);
		itemsList.add(items_3);
		
		Mockito.when(foodService.getAllItems()).thenReturn(itemsList);
		
		mockMvc.perform(get("/getAllItems"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
        .andExpect(jsonPath("$[0].name", Matchers.equalTo("Paneer Biryani")));
	}

	@Test
	void testGetByCatgeroy() throws Exception {
		List<Items> itemsList = new ArrayList<Items>();
		itemsList.add(items_1);
		itemsList.add(items_2);
		itemsList.add(items_3);
		
		Mockito.when(foodService.getByCatgeroy(ArgumentMatchers.anyString())).thenReturn(itemsList);
		
		mockMvc.perform(get("/getByCatgeroy/Veg"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
        .andExpect(jsonPath("$[1].name", Matchers.equalTo("Veg Biryani")));
		
	}

	@Test
	void testUpdateItems() throws Exception {
		Items items_4 = new Items(2,"Mutton Biryani","Non-Veg","13-05-2022 11:12",7,"Mutton, Vegitables, Onions","oil, ghee,masala ");
		
		Mockito.when(foodService.updateItems(ArgumentMatchers.anyInt(),ArgumentMatchers.any())).thenReturn("Saved");
        String json = mapper.writeValueAsString(items_4);
        mockMvc.perform(put("/updateItems/2")
        		.contentType(MediaType.APPLICATION_JSON)
        		.characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk()) 
                .andExpect(jsonPath("$", Matchers.equalTo("Saved")));
	}

	@Test
	void testDeleteItem() throws Exception {
		Mockito.when(foodService.deleteItem(ArgumentMatchers.anyInt())).thenReturn("Food Item was deleted");
        MvcResult requestResult = mockMvc.perform(delete("/deleteItem/1"))
                .andExpect(status().isOk())
                .andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Food Item was deleted");
	}

}
