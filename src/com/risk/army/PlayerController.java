/**
 * 
 */
package com.risk.army;

import java.util.ArrayList;

import com.risk.dice.Dice;
import com.risk.map.Country;
import com.risk.run.Model;

/**
 *
 * @author Natheepan
 * @author Tianyi
 */
public class PlayerController {
	private Model model;

	public PlayerController(Model m) {
		this.model = m;
	}

	public Player getCurrentPlayer() {
		return model.getCurrentPlayer();
	}

	public Player getNextPlayer() {
		Player player = model.getNextPlayer();
		if (player.numbOccupied() == Country.MAX_NUMBER_OF_COUNTRIES) {
			return model.setWinner(player);
		} else if (player.isPlayerLost()) {
			getNextPlayer();
		}
		return player;
	}

	public void determinePlayersStartingOrder() {
//        Roll the dice to determine who goes first. 
//        The player who rolls the highest number starts the game. 
//        Then the play order goes clockwise from the starting player. 
//        The game starts after the order of play has been determined.
		int[] diceRolls = new int[model.getPlayerSize()];

		for (int i = 0; i < diceRolls.length; i++) {
			diceRolls[i] = Dice.roll();
		}
		int maxRollIndex = 0;
		for (int i = 1; i < diceRolls.length; i++) {
			if (diceRolls[maxRollIndex] < diceRolls[i]) {
				maxRollIndex = i;
			}
		}
		boolean tieBreakingNeeded = false;
		for (int i = 1; i < diceRolls.length; i++) {
			if (diceRolls[maxRollIndex] == diceRolls[i] && maxRollIndex != i) {
				tieBreakingNeeded = true;
			}
		}
		if (tieBreakingNeeded) {
			determinePlayersStartingOrder();
		} else {
//            System.out.println("\n---------------------------------------------");
			System.out.println("The Dice Roll Results are as follows");
			for (int i = 0; i < diceRolls.length; i++) {
				System.out.println(model.getPlayers().get(i).getName() + " --> " + diceRolls[i]);
			}
			System.out.println();
			System.out.println("The player: " + model.getPlayers().get(maxRollIndex).getName()
					+ " has won the dice roll with a roll of " + diceRolls[maxRollIndex]
					+ ".\nHe/She will start first, and the play order goes clockwise from the starting player.");

			System.out.println("\n---------------------------------------------");
			for (int i = 0; i < maxRollIndex; i++) {
				Player temp = model.getPlayers().remove(0);
				model.addPlayer(temp);
			}
			initializeCurrentPlayer();
			System.out.println("The Play Order is as follows:");
			for (int i = 0; i < diceRolls.length; i++) {
				System.out.println(model.getPlayers().get(i).getName());
			}
			System.out.println("\n---------------------------------------------");
		}
	}

	public void assignCountriesToPlayers() {
		boolean[] countryOccupied = new boolean[Country.MAX_NUMBER_OF_COUNTRIES];
		int i = 0;
		while (i < Country.MAX_NUMBER_OF_COUNTRIES) {
			for (Player p : model.getPlayers()) {
				int random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
				while (countryOccupied[random]) {
					random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
				}
				if (!countryOccupied[random]) {
					model.getCountries().get(random).setRuler(p);
					model.getCountries().get(random).setIsOccupied(true);
					model.getCountries().get(random).setArmyCount(1);

					countryOccupied[random] = true;
					p.getOccupiedCountries().add(model.getCountries().get(random));
					i++;
				}
				if (i >= Country.MAX_NUMBER_OF_COUNTRIES) {
					break;
				}
			}
		}
		boolean[] armiesRemaining = new boolean[model.getPlayerSize()];
		boolean done = false;
		System.out.println("dfdf");
		while (!done) {
			for (int ii = 0; ii < model.getPlayerSize(); ii++) {
				if (model.getPlayers().get(ii).armiesLeft() > 0) {
					int random = (int) (Math.random() * model.getPlayers().get(ii).numbOccupied());
					model.getPlayers().get(ii).getOccupiedCountries().get(random).setArmyCount(1);
				} else {
					armiesRemaining[ii] = true;
				}
			}
			int countP = 0;
			for (boolean d : armiesRemaining) {
				if (d) {
					countP++;
				}
			}
			if (countP == model.getPlayerSize()) {
				done = true;
			}
		}
	}

	public ArrayList<Country> getCurPlayerCountries() {
		ArrayList<Country> country = new ArrayList<Country>();
		Player player = model.getCurrentPlayer();
		for (Country c : player.getOccupiedCountries()) {
			country.add(c);
		}
		return country;
	}

	public void initializeCurrentPlayer() {
		model.setCurrentPlayerCountryObs();
	}

	public void setStartingPoints() {
		for (Player player : model.getPlayers()) {
			player.setStartingPoints(setStartingPointsHelper(model.getPlayerSize()));
		}
	}

	public int setStartingPointsHelper(int getPlayerSize) {
		int startingP = 0;
		switch (getPlayerSize) {
		case 2:
			startingP = 40;
			break;
		case 3:
			startingP = 35;
			break;
		case 4:
			startingP = 30;
			break;
		case 5:
			startingP = 25;
			break;
		case 6:
			startingP = 20;
			break;
		}
		return startingP;

	}

	/**
	 * not required for functionality. For debugging purpose.
	 * 
	 */
	public void printCountry() {
		for (Player p : model.getPlayers()) {
			System.out.printf("The Player %-6s %-42s", p.getName(), " has occupied the following countries: ");
			for (Country country : p.getOccupiedCountries()) {
				System.out.print(country.getName() + " , ");
			}
			System.out.println();
		}
	}

	/**
	 * not required for functionality. For debugging purpose.
	 * 
	 */
	public void printArmy() {
		for (Player p : model.getPlayers()) {
			int s = 0;
			for (Country t : p.getOccupiedCountries()) {
				s += t.getArmyCount();
			}
			System.out.println(p.getName() + " has a total of " + s + " armies.");
		}
		System.out.println("-------");
	}

	/**
	 * not required for functionality. For debugging purpose.
	 * 
	 */
	public void printPlayerInfo() {
		for (Player p : model.getPlayers()) {
			int s = 0;
			for (Country t : p.getOccupiedCountries()) {
				s += t.getArmyCount();
			}
			System.out.println(p.getName() + " has a total of " + s + " armies.");
		}
		System.out.println("\n---------------------------------------------");
		for (Player p : model.getPlayers()) {
			System.out.println(p.getName() + " has occupied " + p.getOccupiedCountries().size() + " countries.");
		}
		System.out.println("\n---------------------------------------------");
		for (Player p : model.getPlayers()) {
			System.out.printf("The Player %-6s %-42s", p.getName(), " has occupied the following countries: ");
			for (Country country : p.getOccupiedCountries()) {
				System.out.print(country.getName() + " , ");
			}
			System.out.println();
		}
	}
}