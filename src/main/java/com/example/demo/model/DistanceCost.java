package com.example.demo.model;
/**
 * A POJO to hold the request responses.
 * Represents
 * @author Shakiru Adeshina
 *
 */
public class DistanceCost {
	private String from;
	private String to;
	private String distance;
	private String cost;
	
	public DistanceCost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}


	@Override
	public String toString() {
		return "DistanceCost [from=" + from + ", to=" + to + ", distance=" + distance + ", cost=" + cost + "]";
	}

	
	
	
	
	
}
