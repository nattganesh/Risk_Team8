/**
 * Necessary to create a player
 *
 * @author Natheepan
 *
 *
 */
package com.risk.model.player;

import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.dice.Dice;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Player extends Observable {

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    private ObservableList<Card> cards = FXCollections.observableArrayList();

    private int totalArmy;
    private int startingPoints;

    private boolean playerLost = false;

    /**
     * This method increases total army of player object by count
     * 
     * @param count number of army
     */
    public void setTotalArmy(int count)
    {
        totalArmy = totalArmy + count;
    }

    /**
     * This method reduces the total army of player object by count
     * 
     * @param count number of army
     */
    public void reduceTotalArmy(int count)
    {
        totalArmy = totalArmy - count;
    }

    /**
     * This method returns the total army
     * 
     * @return total number of army
     */
    public int getTotalArmy()
    {
        return totalArmy;
    }

    /**
     * Constructor for Player class
     *
     * @param name the name of the player in string
     */
    public Player(String name)
    {
        this.name = name;

    }

    /**
     * Sets the number of armies needed to start the game for a player
     *
     * @param i the count of the armies you need to start the game
     */
    public void setStartingPoints(int i)
    {
        this.startingPoints = i;
    }

    /**
     * Gets the starting points of armies left
     *
     * @return
     */
    public int getStartingPoints()
    {
        return startingPoints;
    }

    /**
     * gets the name of player
     *
     * @return name name of player
     */
    public String getName()
    {
        return name;
    }

    /**
     * gets the country occupied by player in arrayList
     *
     * @return occupiedCountries returns ArrayList<Country> occupiedCountries
     */
    public ArrayList<Country> getOccupiedCountries()
    {
        return occupiedCountries;
    }

    /**
     *
     * @param country country to be added in the player's occupied territories
     */
    public void addCountry(Country country)
    {
        occupiedCountries.add(country);
        setChanged();
        notifyObservers(country);
    }

    /**
     *
     * @param country country to be removed in the player's occupied territories
     */
    public void removeCountry(Country country)
    {

        occupiedCountries.remove(country);
        setChanged();
        notifyObservers(country);
    }

    /**
     * gets the size of occupied country
     *
     * @return returns length of the ArrayList<Country> occupiedCountries
     */
    public int numbOccupied()
    {
        return occupiedCountries.size();
    }


    /**
     * gets country object associated with the name of the string
     * @param occupiedCountries 
     */
    public void setOccupiedCountries(ArrayList<Country> occupiedCountries)
    {
        this.occupiedCountries = occupiedCountries;
    }

    /**
     * 
     * @param name string name of the country
     * @return returns country associated with the name
     */
    public Country getCountry(String name)
    {
        for (Country country : occupiedCountries)
        {
            if (country.getName().equalsIgnoreCase(name))
            {
                return country;
            }
        }
        return null;
    }

    /**
     * Gets the starting number of armies when the game first starts
     *
     * @return startingPoints number of armies
     */
    public ObservableList<Card> getCards()
    {
        return cards;
    }

    /**
     * This method adds a card to player
     * 
     * @param card the card being added to player
     */
    public void addCard(Card card)
    {
        cards.add(card);
    }

    /**
     * This method removes the card from player
     * 
     * @param card the card to be removed from player
     */
    public void removeCard(Card card)
    {
        for (Card c : cards)
        {
            if (c.getCatagory().equals(card.getCatagory()))
            {
                c.setOwner(null);
            	cards.remove(c);
                break;
            }
        }
    }

    /**
     * This metohd sets the card observable list
     * 
     * @param cards
     */
    public void setCards(ObservableList<Card> cards)
    {
        this.cards = cards;
    }

    /**
     * gets the value representing the lost status of player.
     *
     * @return playerLost returns true if player has lost, false otherwise
     */
    public boolean isPlayerLost()
    {
        return playerLost;
    }

    /**
     * sets the lost status of player
     *
     * @param playerLost the boolean value representing what playerLost should
     * be set by
     */
    public void setPlayerLost(boolean playerLost)
    {
        this.playerLost = playerLost;
    }
    
	/**
	 * This method is necessary to attack
	 * 
	 * @param attack country that is attacking
	 * @param defend country being attacked
	 * @param caseType the type of attack
	 */
    public void attack(Country attack, Country defend, int caseType)
    {
        switch (caseType)
        {
            case 1:
                defend.reduceArmyCount(1);
                break;
            case 2:
                defend.setRuler(this);
                defend.getRuler().removeCountry(defend);
                addCountry(defend);
                break;
            case 3:
                attack.reduceArmyCount(1);
                break;
        }
    }

    /**
     * This method is necessary for reinforcement
     * 
     * @param myCountry the country to be reinforced
     * @param Armyinput the number of army to reinforce
     */
    public void reinforce(Country myCountry, int Armyinput)
    {
        myCountry.setArmyCount(Armyinput);
    }

    /**
     * this method is necessary for fortify a country
     * 
     * @param from country origin of army
     * @param to country destination of army
     * @param Armyinput number of army to move
     */
    public void fortify(Country from, Country to, int Armyinput)
    {
        from.reduceArmyCount(Armyinput);
        to.setArmyCount(Armyinput);
    }

    /**
     * This method is used to get all accessible countries of the country player
     * chooses to move armies from
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return The list of accessible countries corresponding to the country
     *
     */
    public ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry, ArrayList<Country> countries)
    {
        for (Country c : country.getConnectedCountries())
        {
            Player player = c.getRuler();
            if (player.getName().equals(getName()))
            {
                if (isCountryDuplicated(c, firstCountry, countries))
                {
                    countries.add(c);
                    countries = getCountriesArrivedbyPath(c, firstCountry, countries);
                }
            }
        }
        return countries;
    }

    /**
     * This method is used to check if the country accessible is already in the
     * result list And avoid adding the origin country the player choose to the
     * result list
     *
     * @param country The country which is being checked to find all accessible
     * countries to it
     * @param firstCountry The country the player chooses to move armies from
     * @param countries The list that saves all countries accessible to the
     * country the player chooses
     * @return true if the country accessible is not in the result list;
     * otherwise return false
     */
    public boolean isCountryDuplicated(Country country, Country firstCountry, ArrayList<Country> countries)
    {
        int i = 0;
        if (country.getName().equalsIgnoreCase(firstCountry.getName()))
        {
            i = 1;
        }
        else
        {
            for (Country c : countries)
            {
                if (c.getName().equalsIgnoreCase(country.getName()))
                {
                    i = 1;
                }
            }
        }
        if (i == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * This method checks if there is any country has accessible countries and
     * the country which has accessible countries has enough armies for move
     *
     * @return true if there is a country which has accessible countries and it
     * has enough armies for move. Otherwise, return false.
     */
    public boolean isAnyCountriesConnected()
    {
        int i = 0;

        for (Country c : getOccupiedCountries())
        {
            ArrayList<Country> result = new ArrayList<>();
            if ((!getCountriesArrivedbyPath(c, c, result).isEmpty()) && (c.getArmyCount() > 1))
            {
                i = 1;
                break;
            }
        }
        return i == 1;
    }
    
    /**
     * This method is used to calculate the extra armies earned if the player
     * has occupied continents
     *
     * @param currentPlayer The player who is in his reinforcement round
     * @return The result corresponding to the countries the player occupied
     */
    public int calculateReinforcementContinentControl()
    {
    	int reinforcement = 0;
    	HashSet<Continent> occupycontinent = new HashSet<Continent>();
    	for (Country country : occupiedCountries)
    	{
    		boolean control = true;
    		for (Country countryInContinent : country.getContinent().getCountries())
    		{
    			if (!countryInContinent.getRuler().getName().equals(name))
    			{
    				control = false;
    				break;
    			}
    		}
    		if (control)
    		{
    			occupycontinent.add(country.getContinent());
    		}
    	}
    	for(Continent c: occupycontinent) {
    		reinforcement = reinforcement + c.getPointsWhenFullyOccupied();
    	}
    	return reinforcement;
    }
    
    /**
     * This method is used to calculate the extra armies based on the number of
     * countries the player already occupied
     *
     * @param currentPlayer The player who is in his reinforcement round
     * @return The result corresponding to the countries the player occupied
     */
    public int calculateReinforcementOccupiedTerritory()
    {
        int reinforcement = (int) Math.floor(numbOccupied() / 3);
        return reinforcement;
    }
    
    /**
     * This method is used to calculate the extra armies earned by exchanging
     * cards
     *
     * @return The result corresponding to the total exchange time.
     */
    public int calculateReinforcementFromCards()
    {
        int currentExchange = MapModel.getMapModel().getExchangeTime();
        int reinforcement = (currentExchange + 1) * 5;
        MapModel.getMapModel().setExchangeTime(currentExchange + 1);
        return reinforcement;
    }
    
    /**
     * This method is used to get the number of army from occupied continent and countries
     * 
     *
     * @return The result corresponding to the number of continents and countries occupied by the player
     */
    public int getReinforcementArmy() {
        int TotalReinforcement = calculateReinforcementContinentControl() + calculateReinforcementOccupiedTerritory();
        if (TotalReinforcement < 3)
        {
            TotalReinforcement = 3;
        }
        return TotalReinforcement;
    }
    
    
    /**
    *
    * @return returns true if two countries have been selected
    */
   public boolean validateTerritorySelections(Country firstCountry, Country secondCountry)
   {
       if (firstCountry!= null && secondCountry!= null)
       {
           return true;
       }
       else 
       {
    	   return false;
       }
   }
   
   /**
   *
   * @return returns true if two countries have been selected
   */
  public boolean validateTerritorysTheSame(Country firstCountry, Country secondCountry)
  {
      if (firstCountry!=secondCountry)
      {
          return true;
      }
      else 
      {
   	   return false;
      }
  }
   
   /**
    * @return returns true if defender and attacker both selected dice roll
    */
   public boolean validateDiceSelections(Integer attackDice, Integer defendDice)
   {
       if (attackDice != null && defendDice != null)
       {
    	   return true;
       }
       else 
       {
    	   return false;
       }
   }
   
   /**
    * @return returns true if defender and attacker both selected dice roll
    */
   public boolean validateAttackerHasEnoughArmy(Country attack)
   {
       if (attack.getArmyCount()> 1)
       {
           return true;
       }
       else 
       {
    	   return false;
       }
   }
   
   /**
    * this sets the limit of roll depending the country chosen
    * 
    * @param attack country that is attacking
    * @param defend country that is defending 
    * @return returns array of roll limit for attacker and defender
    */
   public int[] setRollLimit(Country attack, Country defend)
   {
       int dicerange_attack;
       int dicerange_defend;
       int[] result = new int[2];
       if ((attack.getArmyCount() - 1) > 3)
       {
           dicerange_attack = 3;
       }
       else
       {
           dicerange_attack = attack.getArmyCount() - 1;
       }
       if (defend.getArmyCount() >= 2)
       {
           dicerange_defend = 2;
       }
       else
       {
           dicerange_defend = 1;
       }
       result[0] = dicerange_attack;
       result[1] = dicerange_defend;
       return result;
   }

   /**
    * 
    * 
    * @param diceattack
    * @param dicedefend
    * @return
    */
   public int setRollTime(int diceattack, int dicedefend)
   {
       int rolltime;
       if (diceattack >= dicedefend)
       {
           rolltime = dicedefend;
       }
       else
       {
           rolltime = diceattack;
       }
       return rolltime;
   }

   public int[] rollResult(int diceNumber)
   {
       int[] result = new int[diceNumber];
       for (int i = 0; i < diceNumber; i++)
       {
           result[i] = Dice.roll();
       }
       int tmp;
       for (int i = 0; i < diceNumber; i++)
       {
           for (int j = i + 1; j < diceNumber; j++)
           {
               if (result[i] < result[j])
               {
                   tmp = result[i];
                   result[i] = result[j];
                   result[j] = tmp;
               }
           }
       }
       return result;
   }
   
   public boolean checkIfCardsMaximum() {
	   if (getCards().size() >= 5) {
		   return true;
	   }
	   else {
		   return false;
	   }
   }
   
   /**
    * This method is used to validate the cards the player chooses to exchange
    *
    * @param selectedCards A list of cards the player chooses to exchange for
    * armies
    * @return The result corresponding to the countries the player occupied
    */
   public boolean cardValidation(ObservableList<Card> selectedCards)
   {
       if (((selectedCards.get(0).getCatagory().equals(selectedCards.get(1).getCatagory()))
               && (selectedCards.get(0).getCatagory().equals(selectedCards.get(2).getCatagory())))
               || ((!(selectedCards.get(0).getCatagory().equals(selectedCards.get(1).getCatagory())))
               && (!(selectedCards.get(0).getCatagory().equals(selectedCards.get(2).getCatagory())))
               && (!(selectedCards.get(1).getCatagory().equals(selectedCards.get(2).getCatagory())))))
       {
           return true;
       }
       else
       {
           return false;
       }
   }
   
   /**
    * This method is used to remove the cards which the player chooses to exchange
    *
    * @param observableList A list of cards the player chooses to exchange for
    * armies
    * 
    */
   public void exchangeCards(ObservableList<Card> observableList) 
   {
	   for (Card c : observableList)
       {
		   removeCard(c);
       }
   }
}
