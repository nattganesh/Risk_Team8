/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.run;

import com.risk.army.Player;
import com.risk.dice.Dice;
import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.map.Continent;
import com.risk.map.Country;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**adsf
 *
 * @author Natheepan
 * @author Tianyi
 */
public class Run {

    public static ArrayList<Continent> continents = new ArrayList<>();
    public static ArrayList<Country> countries = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    private static String text = "";

    public static Country getCountry(String name)
    {
        for (Country country : countries)
        {
            if (country.getName().equals(name))
            {
                return country;
            }
        }
        return null;
    }

    public static Player getPlayer(String name)
    {
        for (Player player : players)
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }
        return null;
    }

    public static Continent getContinent(String name)
    {
        for (Continent continent : continents)
        {
            if (continent.getName().equals(name))
            {
                return continent;
            }
        }
        return null;
    }

    public static void setPlayers(Scanner input) throws CountLimitException, CannotFindException
    {
        if (input.hasNextInt())
        {
            int numberOfPlayers = input.nextInt();
            if (numberOfPlayers < 2 || numberOfPlayers > 6)
            {
                CountLimitException ex = new CountLimitException("Players", numberOfPlayers, 2, 6);
                throw ex;
            }
            else
            {
                int a = 0;
                input.nextLine();
                while (a != numberOfPlayers)
                {
                    players.add(new Player(input.nextLine()));
                    a++;
                }
            }
        }
        else
        {
            CannotFindException ex = new CannotFindException(
                    "The first line of Input Text Document is reserved for the number of players playing the game. This is undefined. This number should be between 2 and 6. Please resolve this issue.");
            throw ex;
        }
    }

    public static void setCountriesInContinents(Scanner input) throws CannotFindException, DuplicatesException
    {
        if (input.hasNextLine())
        {
            text = input.nextLine();
            if (text.equalsIgnoreCase("SET COUNTRIES IN CONTINENTS") && input.hasNextLine())
            {
                text = input.nextLine();
                int b = 1;
                while (input.hasNextLine() && b <= Country.MAX_NUMBER_OF_COUNTRIES)
                {
                    b++;
                    String nameOfContinent = text.substring(0, text.indexOf(","));
                    String nameOfCountry = text.substring(text.indexOf(",") + 1, text.length());
                    boolean countryExists = false;
                    for (Country country : countries)
                    {
                        if (country.getName().equalsIgnoreCase(nameOfCountry))
                        {
                            countryExists = true;
                        }
                    }
                    if (!countryExists)
                    {
                        boolean continentExists = false;

                        Country c = new Country(nameOfCountry, nameOfContinent);
                        countries.add(c);

                        for (Continent cont : continents)
                        {
                            if (cont.getName().equalsIgnoreCase(nameOfContinent))
                            {
                                continentExists = true;
                                cont.getCountries().add(c);
                            }
                        }

                        if (!continentExists)
                        {
                            continents.add(new Continent(nameOfContinent, 10));
                            continents.get(continents.size() - 1).getCountries().add(c);
                        }
                    }
                    else
                    {
                        DuplicatesException ex1 = new DuplicatesException("Country: " + nameOfCountry);
                        throw ex1;
                    }
                    text = input.nextLine();
                }
            }
            else
            {
                CannotFindException ex = new CannotFindException(
                        "The tag 'SET COUNTRIES IN CONTINENTS' is not set. Please follow the Input Text Format Please resolve this issue.");
                throw ex;
            }
        }
        else
        {
            CannotFindException ex = new CannotFindException(
                    "Please follow the Input Text Format Please resolve this issue.");
            throw ex;
        }
    }

    public static void setNeighboringCountries(Scanner input) throws CannotFindException
    {
        if (input.hasNextLine() && text.equalsIgnoreCase("SET NEIGHBORS"))
        {
            while (input.hasNextLine())
            {
                text = input.nextLine();
                String nameOfCountry1 = text.substring(0, text.indexOf(","));
                String nameOfCountry2 = text.substring(text.indexOf(",") + 1, text.length());

                for (Country c : countries)
                {
                    if (c.getName().equalsIgnoreCase(nameOfCountry1))
                    {
                        for (Country c2 : countries)
                        {
                            if (c2.getName().equalsIgnoreCase(nameOfCountry2))
                            {
                                c.getConnectedCountries().add(c2);
                                c2.getConnectedCountries().add(c);
                            }
                        }
                    }
                }
            }

        }
        else
        {
            CannotFindException ex = new CannotFindException(
                    "The tag 'SET NEIGHBORS' is not set. Please follow the Input Text Format Please resolve this issue.");
            throw ex;
        }
    }

    public static void continentChecks() throws CannotFindException, CountLimitException
    {
        for (Continent cont : continents)
        {
            int count = cont.getCountries().size();
            int maxCount = 0;

            switch (cont.getName())
            {
                case "North America":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_NORTH_AMERICA;
                    break;
                case "South America":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_SOUTH_AMERICA;
                    break;
                case "Europe":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_EUROPE;
                    break;
                case "Asia":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_ASIA;
                    break;
                case "Africa":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_AFRICA;
                    break;
                case "Australia":
                    maxCount = Continent.MAX_NUMBER_OF_COUNTRIES_IN_AUSTRALIA;
                    break;
                default:
                    CannotFindException ex2 = new CannotFindException(cont.getName()
                            + " is not predefined. Size of continent is not known. Please resolve this issue.");
                    throw ex2;
            }

            if (maxCount != count)
            {
                CountLimitException ex3 = new CountLimitException(cont.getName(), count, maxCount);
                throw ex3;
            }
        }
    }

    public static void determinePlayersStartingOrder()
    {
//        Roll the dice to determine who goes first. 
//        The player who rolls the highest number starts the game. 
//        Then the play order goes clockwise from the starting player. 
//        The game starts after the order of play has been determined.
        int[] diceRolls = new int[players.size()];

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
                System.out.println(players.get(i).getName() + " --> " + diceRolls[i]);
            }
            System.out.println();
            System.out.println("The player: " + players.get(maxRollIndex).getName()
                    + " has won the dice roll with a roll of " + diceRolls[maxRollIndex]
                    + ".\nHe/She will start first, and the play order goes clockwise from the starting player.");
            System.out.println("\n---------------------------------------------");
            for (int i = 0; i < maxRollIndex; i++)
            {
                Player temp = players.remove(0);
                players.add(temp);
            }
            System.out.println("The Play Order is as follows:");
            for (int i = 0; i < diceRolls.length; i++)
            {
                System.out.println(players.get(i).getName());
            }
            System.out.println("\n---------------------------------------------");
        }
    }

    public static void assignCountriesToPlayers()
    {
        boolean[] countryOccupied = new boolean[Country.MAX_NUMBER_OF_COUNTRIES];
        int i = 0;
        while (i < Country.MAX_NUMBER_OF_COUNTRIES)
        {
            for (Player p : players)
            {
                int random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                while (countryOccupied[random])
                {
                    random = (int) (Math.random() * Country.MAX_NUMBER_OF_COUNTRIES);
                }
                if (!countryOccupied[random])
                {
                    countries.get(random).setRuler(p);
                    countries.get(random).setIsOccupied(true);
                    i++;
                    countries.get(random).setArmyCount(1);

                    countryOccupied[random] = true;
                    p.getOccupiedCountries().add(countries.get(random));
                }
                if (i >= Country.MAX_NUMBER_OF_COUNTRIES)
                {
                    break;
                }
            }
        }

        boolean[] armiesRemaining = new boolean[players.size()];
        boolean done = false;
        while (!done)
        {
            for (int ii = 0; ii < players.size(); ii++)
            {
                if (players.get(ii).playersLeft() > 0)
                {
                    int random = (int) (Math.random() * players.get(ii).getOccupiedCountries().size());
                    players.get(ii).getOccupiedCountries().get(random).setArmyCount(1);
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
            if (countP == players.size())
            {
                done = true;
            }
        }
    }

    public static void printPlayerInfo()
    {
        for (Player p : players)
        {
            int s = 0;
            for (Country t : p.getOccupiedCountries())
            {
                s += t.getArmyCount();
            }
            System.out.println(p.getName() + " has a total of " + s + " armies.");
        }
        System.out.println("\n---------------------------------------------");
        for (Player p : players)
        {
            System.out.println(p.getName() + " has occupied " + p.getOccupiedCountries().size() + " countries.");
        }
        System.out.println("\n---------------------------------------------");
        for (Player p : players)
        {
            System.out.printf("The Player %-6s %-42s", p.getName(), " has occupied the following countries: ");
            for (Country country : p.getOccupiedCountries())
            {
                System.out.print(country.getName() + " , ");
            }
            System.out.println();
        }
//        System.out.println("\n---------------------------------------------");
    }
    
    public static void rollDice(int diceattack, int dicedefend, Country attack, Country defend) 
    {
		int[] dattack = new int[diceattack];
		int[] ddefend = new int[dicedefend];
		int rolltime;
		for (int i = 0; i < diceattack; i++) 
		{
			dattack[i] = Dice.roll();
		}
		for (int i = 0; i < dicedefend; i++)
		{
			ddefend[i] = Dice.roll();
		}
		Arrays.sort(dattack);
		Arrays.sort(ddefend);
		if (diceattack >= dicedefend)
		{
			rolltime = dicedefend;
		} else 
		{
			rolltime = diceattack;
		}
		for (int i = 0; i < rolltime; i++)
		{
			if (defend.getArmyCount() != 0) 
			{
				if (dattack[diceattack - 1 - i] > ddefend[dicedefend - 1 - i]) 
				{
					defend.reduceArmyCount(1);
				} else 
				{
					attack.reduceArmyCount(1);
				}
			} else 
			{
				System.out.println("You have already occupied this country!");
				break;
			}
		}
	}
	
	public static int[] setRollLimt(Country attack, Country defend)
	{
		int dicerange_attack;
		int dicerange_defend;
		int[] result = new int[2];
		if ((attack.getArmyCount() - 1) > 3) 
		{
			dicerange_attack = 3;
		} else 
		{
			dicerange_attack = attack.getArmyCount() - 1;
		}
		if (defend.getArmyCount() >= 2) 
		{
			dicerange_defend = 2;
		} else 
		{
			dicerange_defend = 1;
		}
		result[0] = dicerange_attack;
		result[1] = dicerange_defend;
		return result;
	}
	
	public static Country isCountryBelongedtoPlayer(String c, Player p) 
	{
		String countryNeedtoCheck = c;
		Country result = null;
		for (Country country : p.getOccupiedCountries()) 
		{
			if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
			{
				result = country;
				break;
			}
		}
		return result;
	}
	
	public static Country isCountryConnectedtoAttacker(String c, Country attack) 
	{
		String countryNeedtoCheck = c;
		Country result = null;
		for (Country country : attack.getConnectedCountries()) 
		{
			if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
			{
				result=country;
				break;
			}
		}
		return result;
	}
	
	
	public static String[] isCountryBelongtoPlayer(String c, Player p) 
	{
		String countryNeedtoCheck = c;
		String[] result = new String[2];
		Country attack = isCountryBelongedtoPlayer(countryNeedtoCheck, p);
		if (attack == null) 
		{
			result[0] = "0";
			result[1] = "This country does not belong to you! Please input again!";
		} else if (attack.getArmyCount() < 2) 
		{
			result[0] = "0";
			result[1] = "You don't have enough armies in this country! Please input again!";
		}
		result[0] = "1";
		return result;
	}
	
	public static ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry,ArrayList<Country> countries)
	{
		Player p = country.getRuler();
		for(Country c: country.getConnectedCountries()) 
		{
			Player player =c.getRuler();
			if(player.getName().equals(p.getName())) 
			{
				if(isCountryDuplicated(c,firstCountry, countries)) 
				{
					countries.add(c);
					countries = getCountriesArrivedbyPath(c,firstCountry, countries);
				}
			}
		}
		return countries;
	}
	
	public static boolean isCountryDuplicated(Country country, Country firstCountry, ArrayList<Country> countries) 
	{
		int i = 0;
		if (country.getName().equals(firstCountry.getName())) 
		{
			i = 1;
		} else 
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
	
	public static boolean isCountryBelongedtoAccessibleCountries(String secondcountry, ArrayList<Country> countries) 
	{
		int i=0;
		for(Country c: countries) 
		{
			if(c.getName().equalsIgnoreCase(secondcountry)) 
			{
				i=1;
			}
		}
		if(i==1) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public static String[] isDefenderCorrect(String c, Country attack) 
	{
		String countryNeedtoCheck = c;
		Player p = attack.getRuler();
		String[] result = new String[2];
		if (isCountryBelongedtoPlayer(countryNeedtoCheck, p) != null) 
		{
			result[0] = "0";
			result[1] = "You can't attack your own country! Please input again!";
		} else if (isCountryConnectedtoAttacker(countryNeedtoCheck, attack)==null) 
		{
			result[0] = "0";
			result[1] = "The defender you input is not connected to your attacker. Please try again!";
		} else 
		{
			result[0] = "1";
			result[1] = "The attacker and the defender are determined!";
		}
		return result;
	}

    public static void main(String[] args)
    {

        try
        {
            Scanner input = new Scanner(new File("src/com/risk/run/inputtext/input.txt"));
            System.out.println("\n---------------------------------------------");
            System.out.println("                START-UP PHASE                ");
            System.out.println("---------------------------------------------\n");
            setPlayers(input);
            setCountriesInContinents(input);
            setNeighboringCountries(input);
            continentChecks();
            Player.setStartingPoints(players.size());
            determinePlayersStartingOrder();
            assignCountriesToPlayers();
            printPlayerInfo();
            System.out.println("\n---------------------------------------------");
            System.out.println("           LET'S START PLAYING             ");
            System.out.println("---------------------------------------------\n");
            boolean gameWon = false;
            Scanner key = new Scanner(System.in);
            while (!gameWon)
            {
                for (Player p : players)
                {
                    if (p.isPlayerLost())
                    {
                        continue;
                    }
                    if (p.getOccupiedCountries().size() == Country.MAX_NUMBER_OF_COUNTRIES)
                    {
                        gameWon = true;
                        System.out.println("\n---------------------------------------------");
                        System.out.println("Congradulations Player: " + p.getName() + " you have WON!!!");
                        System.out.println("---------------------------------------------\n");
                        break;
                    }

                    // For every three countries, the player gets one army.
                    // For example, if you had 11 countries, you would receive 3 armies;
                    // if you had 22 countries, you would receive 7 armies.
                    int availableArmies = 3 * p.getOccupiedCountries().size() % 3;
                    if (availableArmies < 3)
                    {
                        availableArmies = 3;
                    }

                    System.out.println(p.getName() + " You have " + availableArmies
                            + " armies to place, where would you like to place. (CountryName,NumberOfArmies)");
                    String text = key.nextLine();
//                    key.nextLine();
                    String name = text.substring(0, text.indexOf(","));
                    String num = text.substring(text.indexOf(",") + 1, text.length());
                    int number = Integer.parseInt(num);

                    Country additionalResources = null;
                    while (additionalResources == null || number > availableArmies || availableArmies != 0)
                    {
                        for (Country c : p.getOccupiedCountries())
                        {
                            if (c.getName().equalsIgnoreCase(name))
                            {
                                additionalResources = c;
                                break;
                            }
                        }
                        if (additionalResources == null)
                        {
                            System.out.println("Invalid Country!!! Please Enter a country you occupy.");
//                            key.nextLine();
                            name = key.nextLine();

                        }
                        else if (number > availableArmies)
                        {
                            System.out.println(
                                    "Invalid Number!!! Please Enter a number between 1 and " + availableArmies);
                            number = key.nextInt();
                            key.nextLine();
                        }
                        else
                        {
                            additionalResources.setArmyCount(number);
                            availableArmies -= number;
                            number = 0;

                            if (availableArmies != 0)
                            {
                                System.out.println(p.getName() + " You have " + availableArmies
                                        + " armies to place, where would you like to place. (CountryName,NumberOfArmies)");
                                text = key.nextLine();
//                    key.nextLine();
                                name = text.substring(0, text.indexOf(","));
                                num = text.substring(text.indexOf(",") + 1, text.length());
                                number = Integer.parseInt(num);
                                additionalResources = null;
                            }
                        }
                    }

                    System.out.println("\n---------------------------------------------");
                    System.out.println("Time To Attack");
                    System.out.println("---------------------------------------------\n");
                    String attacker = "";
                    String defender = "";
                    Country attack = null;
                    Country defend = null;

                    int dicerange_attack;
                    int dicerange_defend;
                    int diceattack;
                    int dicedefend;
                    int rolltime;
                    while (true) 
					{
						System.out.println("Do you want to invoke an attak? Y/N");
						String answer = key.nextLine();
						if (answer.equalsIgnoreCase("Y")) 
						{
							while (true) 
							{
								System.out.println("Please input the name of the attacker:");
								attacker = key.nextLine();
								String[] attackCheck = isCountryBelongtoPlayer(attacker, p);
								if (attackCheck[0]=="0") 
								{
									continue;
								} else 
								{	
									attack = isCountryBelongedtoPlayer(attacker, p);
									while (true) 
									{
										System.out.println("These are countries connected to the attacker:");
										for (Country c : attack.getConnectedCountries()) 
										{
											System.out.println(c.getName());
										}
										System.out.println("Please input the name of the defender:");
										defender = key.nextLine();
										String[] defenderCheck = isDefenderCorrect(defender, attack);
										System.out.println(defenderCheck[1]);
										if (defenderCheck[0].equals("0")) 
										{
											continue;
										} else 
										{
											defend = isCountryConnectedtoAttacker(defender, attack);
											break;
										}
									}
									break;

								}
							}

							while (true) 
							{
								int result[] = setRollLimt(attack, defend);
								dicerange_attack = result[0];
								dicerange_defend = result[1];
								System.out.println("Attacker army:" + attack.getArmyCount());
								System.out.println("defender army:" + defend.getArmyCount());
								System.out.println(
										"Attacker "+attack.getRuler().getName()+ ": Please input the number of dice you want to roll. It should be at most "
												+ dicerange_attack + " :");
								diceattack = key.nextInt();
								String clear = key.nextLine();
								if (diceattack > dicerange_attack) 
								{
									System.out.println("The number you input is unavailable. Please input again!");
									continue;
								} else 
								{
									System.out.println("Defender " + defend.getRuler().getName()
											+ ": Please input the number of dice you want to roll. It should be at most "
											+ dicerange_defend + " :");
									dicedefend = key.nextInt();
									String clear1 = key.nextLine();
									if (dicedefend > dicerange_defend) 
									{
										System.out.println("The number you input is unavailable. Please input again!");
										continue;
									} 
									rollDice(diceattack, dicedefend, attack, defend);
									System.out.println("Attacker army:" + attack.getArmyCount());
									System.out.println("defender army:" + defend.getArmyCount());
									if (defend.getArmyCount() == 0) 
									{
										defend.setRuler(p);
										System.out.println(defend.getRuler().getName());
										System.out.println("You have already occupied this country!");
										break;
									} else 
									{
										System.out.println("Do you want to continue attacking this country? Y/N");
										String continueornot;
										continueornot = key.nextLine();
										if (continueornot.equalsIgnoreCase("Y")) 
										{
											continue;
										} else 
										{
											break;
										}
									}

								}
							}
						} else 
						{
							break;
						}

					}

                    System.out.println("\n---------------------------------------------");
                    System.out.println("Time To Fortification");
                    System.out.println("---------------------------------------------\n");
                    String firstcountry="";
                    String secondcountry="";
                    Country country1;
                    Country country2;
                    int armiesformove;
                    ArrayList<Country> CountriesArrivedbyPath = new ArrayList<>();
					while (true) 
					{
						System.out.println("Please input the name of the first country:");
						firstcountry = key.nextLine();
						String[] result = new String[2];
						result = isCountryBelongtoPlayer(firstcountry,p);
						if(result[0]=="0") 
						{
							System.out.println(result[1]);
							continue;
						}
						country1 = p.getCountry(firstcountry);
						CountriesArrivedbyPath = getCountriesArrivedbyPath(country1, country1, CountriesArrivedbyPath);
						if(CountriesArrivedbyPath.isEmpty()) 
						{
							System.out.println("There is no accessible country to the first country!");
							continue;
						}
						System.out.println("There are accessible countries to the first country:");
						for(Country c: CountriesArrivedbyPath) 
						{
							System.out.println(c.getName());
						}
						break;
					}
					System.out.println("Please input the name of the second country:");
					while(true) 
					{
						secondcountry = key.nextLine();
						if(isCountryBelongedtoAccessibleCountries(secondcountry, CountriesArrivedbyPath)) 
						{
							System.out.println("The first and second countries are determined!");
							break;
						}
						System.out.println("The second country you input is unaccessible. Please input again:");
						continue;
					}
					country2 = p.getCountry(secondcountry);
					System.out.println("Number of armies on the first country: " + country1.getArmyCount());
					System.out.println("Number of armies on the second country: " + country2.getArmyCount());
					System.out.println("Please input the number of armies you want to move. It should be less at most "+ (country1.getArmyCount()-1) +":");
					while (true) 
					{
						armiesformove = key.nextInt();
						key.nextLine();
						if ((armiesformove < 1)||(armiesformove > (country1.getArmyCount()-1))) 
						{
							System.out.println("The number is invalid. Please input again:");
							continue;
						}
						break;
					}
					country1.reduceArmyCount(armiesformove);
					country2.setArmyCount(armiesformove);
					System.out.println("Move successfully");
					System.out.println("\n---------------------------------------------");
                    System.out.println("Next Round");
                    System.out.println("---------------------------------------------\n");
				}
			}

// FOR DEBUGGING PURPOSE -------------------------------------------------------------------------------------
//            int y = 0;
//            for (Continent cont : continents)
//            {
//                for (Country country : cont.getCountries())
//                {
//                    for (Country c : country.getConnectedCountries())
//                    {
//                        y++;
//                        System.out.printf("%-3s For Continent %-13s %s%-21s is neighbored by: %s%n", y, cont.getName(), "The country: ", country.getName(), c.getName());
//                    }
//                }
//            }
//-------------------------------------------------------------------------------------------------------------
// FOR DEBUGGING PURPOSE -------------------------------------------------------------------------------------
//            int q = 0;
//            for (Continent cont : continents)
//            {
//                for (Country country : cont.getCountries())
//                {
//                    q++;
//                    System.out.printf("%-3s For Continent %-13s %s%-21s is occupied by: %s has an army of %d%n", q, cont.getName(), "The country: ", country.getName(), country.getRuler().getName(), country.getArmyCount());
//                }
//            }
//-------------------------------------------------------------------------------------------------------------
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DuplicatesException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CountLimitException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (CannotFindException ex)
        {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printMenu()
    {
        System.out.println("\n---------------------------------------------");
        System.out.println("Option 1 --> ");
        System.out.println("Option 2 --> ");
        System.out.println("Option 3 --> ");
        System.out.println("Option 4 --> ");
        System.out.println("Option 5 --> ");
        System.out.println("Option 6 --> ");
        System.out.println("---------------------------------------------\n");
    }
}
