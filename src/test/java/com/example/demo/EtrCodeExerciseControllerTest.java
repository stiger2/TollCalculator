package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.exception.LocationNotFoundException;
import com.example.demo.model.DistanceCost;
@AutoConfigureMockMvc
@SpringBootTest
class EtrCodeExerciseControllerTest {

	

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void init() {
		
		
	}
	

	@Test
	void whenTheAPIIsCalledWithVariablesThatDoesNotExist_IsRespondsWithResponseCode404() throws Exception {
		String from = "Highway 5";
		String to = "Highway 6";
		
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isNotFound())
						.andExpect(result -> assertTrue(result.getResolvedException() instanceof LocationNotFoundException));
	
	}
	
	@Test
	void whenAPIIsCalled_WithATravel_FromDonaldCousensPkwy_To_Highway404_ItReturns_13Point12Km_AsDistance_And3Point28Dollars_AsCost() throws Exception {
		String from = "Donald Cousens Pkwy";
		String to = "Highway 404";
		var expectedJson = "{\"from\" : \"Donald Cousens Pkwy\", \"to\" : \"Highway 404\", \"distance\" : \"13.12km\", \"cost\" : \"$3.28\"}";
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isCreated())
						.andExpect(content().json(expectedJson));
		
	}
	
	@Test
	void whenAPIIsCalled_WithATravel_FromQEW_To_Highway400_ItReturns_67Point75Km_AsDistance_And16Point94Dollars_AsCost() throws Exception {
		String from = "QEW";
		String to = "Highway 400";
		var expectedJson = "{\"from\" : \"QEW\", \"to\" : \"Highway 400\", \"distance\" : \"67.75km\", \"cost\" : \"$16.94\"}";
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isCreated())
						.andExpect(content().json(expectedJson));
		
	}
	
	@Test
	void whenAPIIsCalled_WithATravel_FromSalemRoad_To_QEW_ItReturns_115Point28Km_AsDistance_And28Point82Dollars_AsCost() throws Exception {
		String from = "Salem Road";
		String to = "QEW";
		var expectedJson = "{\"from\" : \"Salem Road\", \"to\" : \"QEW\", \"distance\" : \"115.28km\", \"cost\" : \"$28.82\"}";
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isCreated())
						.andExpect(content().json(expectedJson));
		
	}
	
	@Test
	void whenAPIIsCalled_WithATravel_FromQEW_To_SalemRoad_ItReturns_115Point28Km_AsDistance_And28Point82Dollars_AsCost() throws Exception {
		String from = "QEW";
		String to = "Salem Road";
		var expectedJson = "{\"from\" : \"QEW\", \"to\" : \"Salem Road\", \"distance\" : \"115.28km\", \"cost\" : \"$28.82\"}";
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isCreated())
						.andExpect(content().json(expectedJson));
		
	}
	
	

}
