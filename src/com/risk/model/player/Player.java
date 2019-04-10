/**
 * This Player class contains the methods for creating a player
 * and also the implementation of the reinforcement, attack and fortification
 * The functions for reinforcement are done by Nat
 * The functions for attack, fortification and cards are done by Tianyi
 *
 * @author Natheepan
 * @author Tianyi
 * @author DKM
 * 
 * @version 3.0
 *
 *
 */
package com.risk.model.player;

import com.risk.model.MapModel;
import com.risk.model.card.Card;
import com.risk.model.dice.Dice;
import com.risk.model.map.Continent;
import com.risk.model.map.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import com.risk.model.strategy.Strategy;

public class Player extends Observable 
{

    private String name;
    private ArrayList<Country> occupiedCountries = new ArrayList<>();
    private ObservableList<Card> cards = FXCollections.observableArrayList();
    private int totalArmy;
    private int startingPoints;
    private boolean playerLost = false;
    private boolean isComputerPlayer;
    private Strategy strategy;
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
     * This method returns boolean isComputerPlayer
     * 
     * @return true if player is CPU else false
     */
    public boolean isComputerPlayer()
    {
        return isComputerPlayer;
    }
    
    /**
     * This method checks if there is any enemy around
     * 
     * @return returns true if there is enemey around 
     */
    public boolean checkIfEnemyAround()
    {
    	for (Country c : occupiedCountries)
    	{
    		if (c.getConnectedEnemy().size() > 0) 
    		{
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * This method sets the player as computer
     * 
     * @param isComputerPlayer true if player is CPU, else false
     */
    public void setIsComputerPlayer(boolean isComputerPlayer)
    {
        this.isComputerPlayer = isComputerPlayer;
    }

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
        if (totalArmy <= 0)
        {
            System.out.println(getName() + " has lost");
            playerLost = true;
        }

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
     * @return startingPoints The number of armies for set up
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
     * @return occupiedCountries The countries occupied by the player
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
     * This method is used to get the number of occupied countries
     *
     * @return returns the number of countries occupied by the player
     */
    public int numbOccupied()
    {
        return occupiedCountries.size();
    }

    /**
     * This method is used to set occupied countries to the player
     *
     * @param occupiedCountries The arraylist of occupied countries
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
    
    public ArrayList<Card> getCardList()
    {
    	ArrayList<Card> card = new ArrayList<Card>();
    	for(Card c: cards) 
    	{
    		card.add(c);
    	}
    	return card;
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
     * This method sets the card observable list to player directly
     *
     * @param cards The list of cards that are owned by the player
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
     * This method gets the strategy fo the player 
     * 
     * @return returs the strategy
     */
    public Strategy getStrategy()
    {
        return strategy;
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
        return (i == 0);
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
        for (Continent c : occupycontinent)
        {
            reinforcement = reinforcement + c.getPointsWhenFullyOccupied();
        }
        return reinforcement;
    }

    /**
     * This method is used to calculate the extra armies based on the number of
     * countries the player already occupied
     *
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
     * This method is used to get the number of army from occupied continent and
     * countries
     *
     *
     * @return The result corresponding to the number of continents and
     * countries occupied by the player
     */
    public int getReinforcementArmy()
    {
        int TotalReinforcement = calculateReinforcementContinentControl() + calculateReinforcementOccupiedTerritory();
        if (TotalReinforcement < 3)
        {
            TotalReinforcement = 3;
        }
        return TotalReinforcement;
    }

    /**
     * This method is used to validate if the player selects two countries
     *
     * @param firstCountry The first country the player selected
     * @param secondCountry The second country the player selected
     * @return returns true if two countries have been selected. Otherwise,
     * return false.
     */
    public boolean validateTerritorySelections(Country firstCountry, Country secondCountry)
    {
        return (firstCountry != null && secondCountry != null);
    }

    /**
     * This method is used to validate if the two countries the player selects
     * are the same
     *
     * @param firstCountry The first country the player selected
     * @param secondCountry The second country the player selected
     * @return returns true if two countries selected are not the same country.
     * Otherwise, return false.
     */
    public boolean validateTerritorysTheSame(Country firstCountry, Country secondCountry)
    {
        return (firstCountry != secondCountry);
    }

    /**
     * This method is used to validate if both attacker and defender select how
     * many dice to roll
     *
     * @param attackDice The number of dice attacker chooses to roll
     * @param defendDice The number of dice defender chooses to roll
     * @return returns true if defender and attacker both selected how many dice
     * to roll. Otherwise, return false
     */
    public boolean validateDiceSelections(Integer attackDice, Integer defendDice)
    {
        return (attackDice != null && defendDice != null);
    }

    /**
     * This method is used to validate if the country the player selects has
     * enough armies for attack or fortification
     *
     * @param attack The country which invokes an attack or fortification
     * @return returns true if the country has enough armies. Otherwise, return
     * false.
     */
    public boolean validateAttackerHasEnoughArmy(Country attack)
    {
        return (attack.getArmyCount() > 1);
    }

    /**
     * This method is used to set the roll limit according to the number of
     * armies the attacker and defender have
     *
     * @param attack The country that is attacking
     * @param defend The country that is defending
     * @return result The array of roll limit for attacker and defender
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
     * This method is used to set the times of rolling dice according to the
     * number of dice the attacker and defender choose to roll
     *
     * @param diceattack The number of dice the attacker chooses to roll
     * @param dicedefend The number of dice the defender chooses to roll
     * @return rolltime The times of rolling dice
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

    /**
     * This method is used to set the result of rolling dice according to the
     * number of dice And the result is sorted from the highest to lowest
     *
     * @param diceNumber The number of dice the player chooses to roll
     * @return result The result of rolling dice
     */
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

    /**
     * This method is used to check if player has 5 or more than 5 cards
     *
     * @return returns true if the player has 5 or more than 5 cards. Otherwise,
     * return false.
     */
    public boolean checkIfCardsMaximum()
    {
        System.out.println("check if maximum: " + (getCards().size()));
        return (getCards().size() >= 5);
    }

    /**
     * This method is used to validate the cards the player chooses to exchange
     *
     * @param selectedCards A list of cards the player chooses to exchange for
     * armies
     * @return The result corresponding to the category of cards the player
     * chooses
     */
    public boolean cardValidation(ObservableList<Card> selectedCards)
    {
        return (((selectedCards.get(0).getCatagory().equals(selectedCards.get(1).getCatagory()))
                && (selectedCards.get(0).getCatagory().equals(selectedCards.get(2).getCatagory())))
                || ((!(selectedCards.get(0).getCatagory().equals(selectedCards.get(1).getCatagory())))
                && (!(selectedCards.get(0).getCatagory().equals(selectedCards.get(2).getCatagory())))
                && (!(selectedCards.get(1).getCatagory().equals(selectedCards.get(2).getCatagory())))));
    }

    /**
     * This method is used to remove the cards which the player chooses to
     * exchange
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

    /**
     * This method gets the strongest country
     * 
     * @return returns the country that is strongest
     */
    public Country getStrongestCountry()
    {
    	ArrayList<Country> strongest = new ArrayList<Country>();
    	int tmp = 0;
    	int numOfNeighbor = 0;
        for (int i = 0; i < occupiedCountries.size(); i++)
        {
            if (occupiedCountries.get(i).getArmyCount() > tmp&&(occupiedCountries.get(i).getConnectedEnemyArrayList().size()>=numOfNeighbor))
            {
            	numOfNeighbor=occupiedCountries.get(i).getConnectedEnemyArrayList().size();
            	tmp = occupiedCountries.get(i).getArmyCount();
            	strongest.clear();
            	strongest.add(occupiedCountries.get(i));
            }
        }
        return strongest.get(0);
    }

    /**
     * This method gets the weakest country 
     * 
     * @return returns arraylist of weakest country
     */
    public ArrayList<Country> getWeakestCountries()
    {
    	int tmp = Integer.MAX_VALUE;
        ArrayList<Country> weakCountries = new ArrayList<>();
        for (int i = 0; i < getOccupiedCountries().size(); i++)
        {
            if (getOccupiedCountries().get(i).getArmyCount() < tmp)
            {
                weakCountries.clear();
                tmp = getOccupiedCountries().get(i).getArmyCount();
                weakCountries.add(getOccupiedCountries().get(i));
            }
            else if (getOccupiedCountries().get(i).getArmyCount() == tmp)
            {
                weakCountries.add(getOccupiedCountries().get(i));
            }
        }
        return weakCountries;
    }
    
    /**
	 * This method is used to decrease the army amount or change the owner of country when it is occupied
	 * In case1, the defender lost in dice so it lost 1 army
	 * In case2, the attacker conquered the defender, so the owner of defender changes
	 * In case3, the attacker lost in dice so it lost 1 army 
	 * 
	 * @param attack country that is attacking
	 * @param defend country being attacked
	 * @param caseType the type of attack
	 * 
	 */
    public void attack(Country attack, Country defend, int caseType)
    {
        switch (caseType)
        {
            case 1:
                defend.reduceArmyCount(1);
                break;
            case 2:
            	defend.getRuler().removeCountry(defend);
                defend.setRuler(this);
                addCountry(defend);
                break;
            case 3:
                attack.reduceArmyCount(1);
                break;
        }
    }

    /**
     * This method is necessary for reinforcement
     * The number of armies in the country will be add with the number the player inputs
     * 
     * @param myCountry the country to be reinforced
     * @param Armyinput the number of army to reinforce
     */
    public void reinforce(Country myCountry, int Armyinput)
    {
        myCountry.setArmyCount(Armyinput);
    }

    /**
     * This method is necessary for fortify a countrys
     * 
     * @param from The country which the player moves the armies from
     * @param to The country which the player moves the armies to
     * @param Armyinput The number of armies to move
     */
    public void fortify(Country from, Country to, int Armyinput)
    {
        from.reduceArmyCount(Armyinput);
        to.setArmyCount(Armyinput);
    }

    /**
     * This method is used to set strategy according to the type of player
     * 
     * @param strategy The strategy according to the type of player
     */
    public void setStrategy(Strategy strategy) 
    {
    	isComputerPlayer = true;
    	this.strategy = strategy;
    }
    
    /**
     * This method is used to invoke attack strategy according to the type of computer player
     * 
     * @param p The player who is going to attack
     */
    public void attackStrategy(Player p) 
    {
    	this.strategy.attack(p);
    }
    
    /**
     * This method is used to invoke reinforce strategy according to the type of computer player
     * 
     * @param p The player who is going to reinforce
     */
    public void reinforceStrategy(Player p) 
    {
    	this.strategy.reinforce(p);
    }
    
    /**
     * This method is used to invoke fortify strategy according to the type of computer player
     * 
     * @param p The player who is going to fortify
     */
    public void fortifyStrategy(Player p) 
    {
    	this.strategy.fortify(p);
    }
    
    /**
     * This method is used to invoke setup strategy according to the type of computer player
     * 
     * @param p The player who is going to setup
     */
    public void setupStrategy(Player p) 
    {
    	this.strategy.setup(p);
    }
    
    /**
     * This method is used to invoke conquer strategy according to the type of computer player
     * 
     * @param c1 country to move army from
     * @param c2 country to move army to
     * @param i number of army
     */
    public void conquerStrategy(Country c1, Country c2, int i) 
    {
    	this.strategy.conquer(c1, c2, i);
    }
    
    /**
     * This method is used to exchange cards automatically for computer players
     * 
     * @return returns true if computer was able to exchange, else false
     */
    public boolean exchangeCardForComputer() 
    {
    	boolean exchange = false;
    	while(getCardList().size()>=5) 
    	{
    		ArrayList<Card> cards = getCardList();
    		ArrayList<String> exchangecards = new ArrayList<String>();
    		Map<String, Integer> map = new HashMap<>();
    		for(Card c: cards) 
    		{
    			String s = c.getCatagory();
                if (map.containsKey(s)) 
                {
                    map.put(s, map.get(s) + 1);
                } else 
                {
                    map.put(s, 1);
                }
    		}
    		if(map.size()>=3) 
    		{
    			while(exchangecards.size()<3) 
    			{
    				for(Card card: cards) 
    				{
    					String s = card.getCatagory();
        				if(exchangecards.contains(s)) 
        					continue;
        				else 
        				{
        					exchangecards.add(s);
        					removeCard(card);
        				}
        			}
    			}
    			exchange = true;
    		}else 
    		{
    			int i =0;
        		for(String s: map.keySet()) 
        		{
        			if(map.get(s)>=3) 
        			{
        				for(Card card: cards) 
        				{
        					if(card.getCatagory().equals(s)) 
        					{
        						removeCard(card);
        						i++;
        						if(i==3) 
        						{
        							exchange = true;
        							break;
        						}
        					}
        				}
        			}
        		}
    		}
    	}
    	return exchange;
    }
    
    /**
     * This method is used to move cards from the player who lost the last country to the player
     * who conquered that country
     * 
     * @param a The player who lost the last country of his own
     */
    public void moveCards(Player a) 
    {
    	ObservableList<Card> loserCard = a.getCards();
    	for(Card c: loserCard) 
    	{
    		addCard(c);
    		c.setOwner(this);
    	}
    	a.getCards().clear();
    }
}
