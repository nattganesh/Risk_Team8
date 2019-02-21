/** 
 * This class is necessary for initializing the game play after number of player has been determined.
 * It calculates round robin, assigns countries to players and determines the number of initial armies.
 *
 * @author Natheepan
 * @author Tianyi
 * @version 1.0
 */
package com.risk.army;

import java.util.ArrayList;

import com.risk.dice.Dice;
import com.risk.map.Country;
import com.risk.model.Model;


public class PlayerController {

    private Model model;
    
	/**
	 * This is the constructor of PlayerController. It initializes the model, containing parsed map file.
	 * 
	 * @param m this is the model of the game
	 */
    public PlayerController(Model m)
    {
        this.model = m;
    }
    
	/**
	 * This method gets the current player of the game.
	 * 
	 * @return returns the current player
	 */
    public Player getCurrentPlayer()
    {
        return model.getCurrentPlayer();
    }
    
	/**
	 * This method sets players with color based on number.
	 *  
	 * @param numberOfPlayer number of player
	 */
    public void setPlayers(int numberOfPlayer) 
    {
		int a = 0;
		while (a < numberOfPlayer) 
        {
			model.addPlayer(new Player(Player.PLAYERCOLOR[a])); 
			a++;
		}
	}

	/**
	 * This method returns the next player of the game. 
	 * But, if the next player has lost then it skips that player, or if that next player has won 
	 * then it returns the winner.
	 * 
	 * @return player returns the next player of the game
	 */
    public Player getNextPlayer()
    {
        Player player = model.getNextPlayer();
        if (player.numbOccupied() == Country.MAX_NUMBER_OF_COUNTRIES)
        {
            return model.setWinner(player);
        }
        else if (player.isPlayerLost())
        {
            getNextPlayer();
        }
        return player;
    }

    

	/**
	 *  This method calculates the round robin.
	 *  It first roll the dice to determine who goes first. 
	 *  The player who rolls the highest number starts the game. 
	 *  Then the play order goes clockwise from the starting player. 
	 *  The game starts after the order of play has been determined.
	 */
    public void determinePlayersStartingOrder()
    {
        int[] diceRolls = new int[model.getPlayerSize()];

        for (int i = 0; i < diceRolls.length; i++)
        {
            diceRolls[i] = Dice.roll();
        }
        int maxRollIndex = 0;
        for (int i = 1; i < diceRolls.length; i++)
        {
            if (diceRolls[maxRollIndex] < diceRolls[i])
            {
                maxRollIndex = i;
            }
        }
        boolean tieBreakingNeeded = false;
        for (int i = 1; i < diceRolls.length; i++)
        {
            if (diceRolls[maxRollIndex] == diceRolls[i] && maxRollIndex != i)
            {
                tieBreakingNeeded = true;
            }
        }
        if (tieBreakingNeeded)
        {
            determinePlayersStartingOrder();
        }
        else
        {
//            System.out.println("\n---------------------------------------------");
            System.out.println("The Dice Roll Results are as follows");
            for (int i = 0; i < diceRolls.length; i++)
            {
                System.out.println(model.getPlayers().get(i).getName() + " --> " + diceRolls[i]);
            }
            System.out.println();
            System.out.println("The player: " + model.getPlayers().get(maxRollIndex).getName()
                    + " has won the dice roll with a roll of " + diceRolls[maxRollIndex]
                    + ".\nHe/She will start first, and the play order goes clockwise from the starting player.");

            System.out.println("\n---------------------------------------------");
            for (int i = 0; i < maxRollIndex; i++)
            {
                Player temp = model.getPlayers().remove(0);
                model.addPlayer(temp);
            }
            initializeCurrentPlayer();
            System.out.println("The Play Order is as follows:");
            for (int i = 0; i < diceRolls.length; i++)
            {
                System.out.println(model.getPlayers().get(i).getName());
            }
            System.out.println("\n---------------------------------------------");
        }
    }

	/**
	 * This method assigns countries to players.
	 */
    public void assignCountriesToPlayers()
    {
        boolean[] countryOccupied = new boolean[Country.MAX_NUMBER_OF_COUNTRIES];
        int i = 0;
        while (i < Country.MAX_NUMBER_OF_COUNTRIES)
        {
            for (Player p : model.getPlayers())
            {
                int random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                while (countryOccupied[random])
                {
                    random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                }
                if (!countryOccupied[random])
                {
                    model.getCountries().get(random).setRuler(p);
                    model.getCountries().get(random).setIsOccupied(true);
                    model.getCountries().get(random).setArmyCount(1);

                    countryOccupied[random] = true;
                    p.getOccupiedCountries().add(model.getCountries().get(random));
                    i++;
                }
                if (i >= Country.MAX_NUMBER_OF_COUNTRIES)
                {
                    break;
                }
            }
        }
        boolean[] armiesRemaining = new boolean[model.getPlayerSize()];
        boolean done = false;
        System.out.println("dfdf");
        while (!done)
        {
            for (int ii = 0; ii < model.getPlayerSize(); ii++)
            {
                if (model.getPlayers().get(ii).armiesLeft() > 0)
                {
                    int random = (int) (Math.random() * model.getPlayers().get(ii).numbOccupied());
                    model.getPlayers().get(ii).getOccupiedCountries().get(random).setArmyCount(1);
                }
                else
                {
                    armiesRemaining[ii] = true;
                }
            }
            int countP = 0;
            for (boolean d : armiesRemaining)
            {
                if (d)
                {
                    countP++;
                }
            }
            if (countP == model.getPlayerSize())
            {
                done = true;
            }
        }
    }

	/**
	 * This method returns the countries occupied by the current player
	 * 
	 * @return country ArrayList<Country> occupied by current player
	 */
    public ArrayList<Country> getCurPlayerCountries()
    {
        ArrayList<Country> country = new ArrayList<Country>();
        Player player = model.getCurrentPlayer();
        for (Country c : player.getOccupiedCountries())
        {
            country.add(c);
        }
        return country;
    }

	/**
	 * This method sets the observable list for the country occupied by current player.	 * 
	 */
    public void initializeCurrentPlayer()
    {
        model.setCurrentPlayerCountryObs();
    }

	/**
	 * This method sets the starting armies during initialization based on number of players.
	 */
    public void setStartingPoints()
    {
        for (Player player : model.getPlayers())
        {
            player.setStartingPoints(setStartingPointsHelper(model.getPlayerSize()));
        }
    }


	/**
	 * This is a helper method for setStartingPoints. Based on the number of players it returns 
	 * inital army count.
	 * 
	 * @param getPlayerSize number of players
	 * @return startingP initial army count
	 */
    public int setStartingPointsHelper(int getPlayerSize)
    {
        int startingP = 0;
        switch (getPlayerSize)
        {
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
    public void printCountry()
    {
        for (Player p : model.getPlayers())
        {
            System.out.printf("The Player %-6s %-42s", p.getName(), " has occupied the following countries: ");
            for (Country country : p.getOccupiedCountries())
            {
                System.out.print(country.getName() + " , ");
            }
            System.out.println();
        }
    }

    /**
     * not required for functionality. For debugging purpose.
     *
     */
    public void printArmy()
    {
        for (Player p : model.getPlayers())
        {
            int s = 0;
            for (Country t : p.getOccupiedCountries())
            {
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
    public void printPlayerInfo()
    {
        for (Player p : model.getPlayers())
        {
            int s = 0;
            for (Country t : p.getOccupiedCountries())
            {
                s += t.getArmyCount();
            }
            System.out.println(p.getName() + " has a total of " + s + " armies.");
        }
        System.out.println("\n---------------------------------------------");
        for (Player p : model.getPlayers())
        {
            System.out.println(p.getName() + " has occupied " + p.getOccupiedCountries().size() + " countries.");
        }
        System.out.println("\n---------------------------------------------");
        for (Player p : model.getPlayers())
        {
            System.out.printf("The Player %-6s %-42s", p.getName(), " has occupied the following countries: ");
            for (Country country : p.getOccupiedCountries())
            {
                System.out.print(country.getName() + " , ");
            }
            System.out.println();
        }
    }
}
