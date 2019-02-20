/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.map;

import java.util.ArrayList;

/**
 *
 * @author Natheepan
 */
public class Continent {

	public static final int MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA = 9;
	public static final int MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA = 4;
	public static final int MAX_NUMBER_OF_COUNTRIES_IN_AFRICA = 6;
	public static final int MAX_NUMBER_OF_COUNTRIES_IN_EUROPE = 7;
	public static final int MAX_NUMBER_OF_COUNTRIES_IN_ASIA = 12;
	public static final int MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA = 4;
	private String name;
	private ArrayList<Country> countries = new ArrayList<>();
	private int pointsWhenFullyOccupied;

	public Continent(String name, int pointsWhenFullyOccupied) {
		this.name = name;
		this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountry(Country country) {
		countries.add(country);
	}
//    public void setCountries(ArrayList<Country> countries)
//    {
//        this.countries = countries;
//    }

	public int getPointsWhenFullyOccupied() {
		return pointsWhenFullyOccupied;
	}

	public void setPointsWhenFullyOccupied(int pointsWhenFullyOccupied) {
		this.pointsWhenFullyOccupied = pointsWhenFullyOccupied;
	}

	public Country getCountry(String name) {
		for (Country country : countries) {
			if (country.getName().equals(name)) {
				return country;
			}
		}
		return null;
	}
}
