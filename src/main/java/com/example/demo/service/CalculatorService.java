package com.example.demo.service;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.LocationNotFoundException;
import com.example.demo.model.DistanceCost;

@Service
public class CalculatorService {
	
	@Autowired
	private Map<String, Double> distanceMap;



	public DistanceCost fetchDistance(String from, String to) {
		Optional<Double> fromOpt = Optional.ofNullable(distanceMap.get(from));
		Optional<Double> toOpt = Optional.ofNullable(distanceMap.get(to));
		if (!fromOpt.isPresent()) {
			throw new LocationNotFoundException("Location with name: '" + from + "' not found");
		}
		
		if (!toOpt.isPresent()) {
			throw new LocationNotFoundException("Location with name: '" + to + "' not found");
		}
		final double rate = 0.25;
		double distance = 0;
		double cost = 0;
		DistanceCost distanceCost = new DistanceCost();
		distance = Math.abs(fromOpt.get() - toOpt.get());
		cost = distance * rate;
		DecimalFormat f = new DecimalFormat("##.00");
		distanceCost.setFrom(from);
		distanceCost.setTo(to);
		distanceCost.setDistance(f.format(distance) + "km");
		distanceCost.setCost("$" + f.format(cost));
		
		return distanceCost;
	}

}
