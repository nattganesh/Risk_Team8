/**
 * 
 */
package com.risk.run;

import java.util.ArrayList;

import com.risk.army.Player;
import com.risk.map.Continent;
import com.risk.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author DKM
 *
 */
public class Model {
	private ArrayList<Continent> continentsModel = new ArrayList<>();
	private ArrayList<Country> countriesModel = new ArrayList<>();
	private ObservableList<Player> playersModel = FXCollections.observableArrayList();
	private ObservableList<Country> currentPlayerCountry = FXCollections.observableArrayList();

	private int currentPlayerIndex = 0;
	private Player playerWins = null;

	public Player setWinner(Player player) {
		playerWins = player;
		return playerWins;
	}

	public void IncrementPlayerIndex() {
		currentPlayerIndex = (currentPlayerIndex + 1) % getPlayerSize();
		setView();
	}

	public void setView() {
		currentPlayerCountry.clear();
		currentPlayerCountry.addAll(getCurrentPlayer().getOccupiedCountries());
	}

	public Player getNextPlayer() {
		Player current = playersModel.get(currentPlayerIndex);
		IncrementPlayerIndex();
		return current;
	}

	public void setCurrentPlayerCountryObs() {
		currentPlayerCountry.addAll(getCurrentPlayer().getOccupiedCountries());
	}

	public ObservableList<Country> getCurrentPlayerCountryObs() {
		return currentPlayerCountry;
	}

	public Player getCurrentPlayer() {
		return playersModel.get(currentPlayerIndex);
	}

	public void addCountry(Country country) {
		countriesModel.add(country);
	}

	public void addContinent(Continent continent) {
		continentsModel.add(continent);
	}

	public void addPlayer(Player player) {
		playersModel.add(player);
		player.setStartingPoints(getPlayerSize());
	}

	public int getPlayerSize() {
		return playersModel.size();
	}

	public ArrayList<Country> getCountries() {
		return countriesModel;
	}

	public ArrayList<Continent> getContinents() {
		return continentsModel;
	}

	public ObservableList<Player> getPlayers() {
		return playersModel;
	}

	public Country getCountry(String name) {
		for (Country country : countriesModel) {
			if (country.getName().equals(name)) {
				return country;
			}
		}
		return null;
	}

	public Player getPlayer(String name) {
		for (Player player : playersModel) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public Continent getContinent(String name) {
		for (Continent continent : continentsModel) {
			if (continent.getName().equals(name)) {
				return continent;
			}
		}
		return null;
	}

}
