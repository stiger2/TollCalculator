package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.CalculatorController;
import com.example.demo.model.DistanceCost;
import com.example.demo.service.CalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
class EtrCodeExerciseApplicationTests {
	
	
	@MockBean
	CalculatorService calService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void init() {
		
		
	}
	

	@Test
	void whenGetDistanceAndCostOfCalculatorControllerIsCalled_ItCalls_fetchDistanceOfCalculatorService() throws Exception {
		String from = "Donald Cousens Pkwy";
		String to = "Highway 404";
		DistanceCost dc = new DistanceCost();
		dc.setFrom(from);
		dc.setTo(to);
		dc.setDistance("13.12km");
		dc.setCost("$3.28");
		
		mockMvc.perform(get("/api/v1/calculator/{from}/{to}", from, to))
						.andDo(print())
						.andExpect(status().isCreated());
		
		verify(calService).fetchDistance(from, to);
		
	}
	
	
	

}
