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

/**
 *
 * @author Natheepan
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
                                for (Country c : p.getOccupiedCountries())
                                {
                                    System.out.println(c.getArmyCount());
                                }
                                System.out.println("Please input the name of the attacker:");
                                attacker = key.nextLine();
                                for (Country c : p.getOccupiedCountries())
                                {
                                    if (c.getName().equalsIgnoreCase(attacker))
                                    {
                                        attack = c;
                                        break;
                                    }
                                }
                                if (attack == null)
                                {
                                    System.out.println("This country does not belong to you! Please input again!");
                                    continue;
                                }
                                else
                                {
                                    if (attack.getArmyCount() < 2)
                                    {
                                        System.out.println(
                                                "You don't have enough armies in this country! Please input again!");
                                        continue;
                                    }
                                    else
                                    {
                                        while (true)
                                        {
                                            System.out.println("Please input the name of the defender:");
                                            defender = key.nextLine();
                                            Country tmp = null;
                                            for (Country c : p.getOccupiedCountries())
                                            {
                                                if (c.getName().equalsIgnoreCase(defender))
                                                {
                                                    tmp = c;
                                                    break;
                                                }
                                            }
                                            if (tmp != null)
                                            {
                                                System.out.println(
                                                        "You can't attack your own country! Please input again!");
                                                continue;
                                            }
                                            else
                                            {
                                                for (Country c : attack.getConnectedCountries())
                                                {
                                                    System.out.println(c.getName());
                                                }

                                                for (Country c : attack.getConnectedCountries())
                                                {
                                                    if (c.getName().equalsIgnoreCase(defender))
                                                    {
                                                        defend = c;
                                                        break;
                                                    }
                                                }
                                                if (defend == null)
                                                {
                                                    System.out.println(
                                                            "The defender you input is not connected to your attacker. Please try again!");
                                                    continue;
                                                }
                                                else
                                                {
                                                    System.out.println("The attacker and the defender are determined!");
                                                    break;
                                                }
                                            }

                                        }
                                        break;
                                    }
                                }
                            }

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

                            while (true)
                            {
                                System.out.println(
                                        "Attacker: Please input the number of dice you want to roll. It should be at most "
                                        + dicerange_attack + " :");
                                diceattack = key.nextInt();
                                String clear = key.nextLine();
                                if (diceattack > dicerange_attack)
                                {
                                    System.out.println("The number you input is unavailable. Please input again!");
                                    continue;
                                }
                                else
                                {
                                    System.out.println("Attacker army:" + attack.getArmyCount());
                                    System.out.println("defender army:" + defend.getArmyCount());
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
                                    else
                                    {
                                        int[] dattack = new int[diceattack];
                                        int[] ddefend = new int[dicedefend];
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
                                        }
                                        else
                                        {
                                            rolltime = diceattack;
                                        }
                                        for (int i = 0; i < rolltime; i++)
                                        {
                                            if (defend.getArmyCount() != 0)
                                            {
                                                if (dattack[diceattack - 1 - i] > ddefend[dicedefend - 1 - i])
                                                {
                                                    defend.reduceArmyCount();
                                                }
                                                else
                                                {
                                                    attack.reduceArmyCount();
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("You have already occupied this country!");
                                                break;
                                            }
                                        }
                                        System.out.println("Attacker army:" + attack.getArmyCount());
                                        System.out.println("defender army:" + defend.getArmyCount());
                                        if (defend.getArmyCount() == 0)
                                        {
                                            defend.setRuler(p);
                                            System.out.println(defend.getRuler().getName());
                                            System.out.println("You have already occupied this country!");
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("Do you want to continue attacking this country? Y/N");
                                            String continueornot;
                                            continueornot = key.next();
                                            if (continueornot.equalsIgnoreCase("Y"))
                                            {
                                                continue;
                                            }
                                            else
                                            {
                                                break;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                        else
                        {
                            break;
                        }

                    }

                    System.out.println("\n---------------------------------------------");
                    System.out.println("Time To Fortification");
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
