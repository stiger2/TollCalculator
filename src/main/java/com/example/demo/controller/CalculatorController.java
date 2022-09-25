package com.example.demo.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.DistanceCost;
import com.example.demo.service.CalculatorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/calculator")
public class CalculatorController {
	@Autowired
	private CalculatorService calService;
	
	@Operation(summary = "Calculates the distance and cost of travel on the 407 highway")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			@Content(mediaType = MediaType.APPLICATION_XML_VALUE)},
		headers = {@Header(name = "location", description = "url to location")}, description = "Determines the distance and cost"),
	@ApiResponse(responseCode = "404", description = "Location not found")})	
	@GetMapping("/{from}/{to}")
	public ResponseEntity<DistanceCost> getDistanceAndCost(@PathVariable String from, @PathVariable String to) {
	    DistanceCost distanceCost = calService.fetchDistance(from, to);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(location).body(distanceCost);
	}
}
