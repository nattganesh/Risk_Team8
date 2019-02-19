/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.run;

import com.risk.MapUtill.RiskMapParser;
import com.risk.army.Player;
import com.risk.army.PlayerController;
import com.risk.dice.Dice;
import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.map.Country;
import com.risk.run.Model;

import View.ReinforcementController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author Natheepan
 * @author Tianyi
 */

public class Run extends Application {

//	  THIS IS ATTACK PHASE
	
//    public static void rollDice(int diceattack, int dicedefend, Country attack, Country defend) 
//    {
//		int[] dattack = new int[diceattack];
//		int[] ddefend = new int[dicedefend];
//		int rolltime;
//		for (int i = 0; i < diceattack; i++) 
//		{
//			dattack[i] = Dice.roll();
//		}
//		for (int i = 0; i < dicedefend; i++)
//		{
//			ddefend[i] = Dice.roll();
//		}
//		Arrays.sort(dattack);
//		Arrays.sort(ddefend);
//		if (diceattack >= dicedefend)
//		{
//			rolltime = dicedefend;
//		} else 
//		{
//			rolltime = diceattack;
//		}
//		for (int i = 0; i < rolltime; i++)
//		{
//			if (defend.getArmyCount() != 0) 
//			{
//				if (dattack[diceattack - 1 - i] > ddefend[dicedefend - 1 - i]) 
//				{
//					defend.reduceArmyCount(1);
//				} else 
//				{
//					attack.reduceArmyCount(1);
//				}
//			} else 
//			{
//				System.out.println("You have already occupied this country!");
//				break;
//			}
//		}
//	}
//	
//	public static int[] setRollLimt(Country attack, Country defend)
//	{
//		int dicerange_attack;
//		int dicerange_defend;
//		int[] result = new int[2];
//		if ((attack.getArmyCount() - 1) > 3) 
//		{
//			dicerange_attack = 3;
//		} else 
//		{
//			dicerange_attack = attack.getArmyCount() - 1;
//		}
//		if (defend.getArmyCount() >= 2) 
//		{
//			dicerange_defend = 2;
//		} else 
//		{
//			dicerange_defend = 1;
//		}
//		result[0] = dicerange_attack;
//		result[1] = dicerange_defend;
//		return result;
//	}
//	
//	public static Country isCountryBelongedtoPlayer(String c, Player p) 
//	{
//		String countryNeedtoCheck = c;
//		Country result = null;
//		for (Country country : p.getOccupiedCountries()) 
//		{
//			if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
//			{
//				result = country;
//				break;
//			}
//		}
//		return result;
//	}
//	
//	public static Country isCountryConnectedtoAttacker(String c, Country attack) 
//	{
//		String countryNeedtoCheck = c;
//		Country result = null;
//		for (Country country : attack.getConnectedCountries()) 
//		{
//			if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
//			{
//				result=country;
//				break;
//			}
//		}
//		return result;
//	}
//	
//	
//	public static String[] isCountryBelongtoPlayer(String c, Player p) 
//	{
//		String countryNeedtoCheck = c;
//		String[] result = new String[2];
//		Country attack = isCountryBelongedtoPlayer(countryNeedtoCheck, p);
//		if (attack == null) 
//		{
//			result[0] = "0";
//			result[1] = "This country does not belong to you! Please input again!";
//		} else if (attack.getArmyCount() < 2) 
//		{
//			result[0] = "0";
//			result[1] = "You don't have enough armies in this country! Please input again!";
//		}
//		result[0] = "1";
//		return result;
//	}
//	
//	public static ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry,ArrayList<Country> countries)
//	{
//		Player p = country.getRuler();
//		for(Country c: country.getConnectedCountries()) 
//		{
//			Player player = c.getRuler();
//			if(player.getName().equals(p.getName())) 
//			{
//				if(isCountryDuplicated(c,firstCountry, countries)) 
//				{
//					countries.add(c);
//					countries = getCountriesArrivedbyPath(c,firstCountry, countries);
//				}
//			}
//		}
//		return countries;
//	}
//	
//	
//	public static boolean isCountryBelongedtoAccessibleCountries(String secondcountry, ArrayList<Country> countries) 
//	{
//		int i=0;
//		for(Country c: countries) 
//		{
//			if(c.getName().equalsIgnoreCase(secondcountry)) 
//			{
//				i=1;
//			}
//		}
//		if(i==1) 
//		{
//			return true;
//		}
//		else 
//		{
//			return false;
//		}
//	}
//	
//	public static String[] isDefenderCorrect(String c, Country attack) 
//	{
//		String countryNeedtoCheck = c;
//		Player p = attack.getRuler();
//		String[] result = new String[2];
//		if (isCountryBelongedtoPlayer(countryNeedtoCheck, p) != null) 
//		{
//			result[0] = "0";
//			result[1] = "You can't attack your own country! Please input again!";
//		} else if (isCountryConnectedtoAttacker(countryNeedtoCheck, attack)==null) 
//		{
//			result[0] = "0";
//			result[1] = "The defender you input is not connected to your attacker. Please try again!";
//		} else 
//		{
//			result[0] = "1";
//			result[1] = "The attacker and the defender are determined!";
//		}
//		return result;
//	}

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		try {
			Model model = new Model();
			RiskMapParser riskMapParser = new RiskMapParser(model);
			riskMapParser.setUp();
			PlayerController pController = new PlayerController(model);
			pController.setStartingPoints();
			pController.assignCountriesToPlayers();
			pController.determinePlayersStartingOrder();
			ReinforcementController rController = new ReinforcementController(model);	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Reinforcement.fxml"));
			loader.setController(rController);
			Parent root = loader.load();
	        primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();
	        primaryStage.setFullScreen(true);		
	        
//
//                    System.out.println("\n---------------------------------------------");
//                    System.out.println("Time To Attack");
//                    System.out.println("---------------------------------------------\n");
//                    String attacker = "";
//                    String defender = "";
//                    Country attack = null;
//                    Country defend = null;
//
//                    int dicerange_attack;
//                    int dicerange_defend;
//                    int diceattack;
//                    int dicedefend;
//                    int rolltime;
//                    while (true) 
//					{
//						System.out.println("Do you want to invoke an attak? Y/N");
//						String answer = key.nextLine();
//						if (answer.equalsIgnoreCase("Y")) 
//						{
//							while (true) 
//							{
//								System.out.println("Please input the name of the attacker:");
//								attacker = key.nextLine();
//								String[] attackCheck = isCountryBelongtoPlayer(attacker, p);
//								if (attackCheck[0]=="0") 
//								{
//									continue;
//								} else 
//								{	
//									attack = isCountryBelongedtoPlayer(attacker, p);
//									while (true) 
//									{
//										System.out.println("These are countries connected to the attacker:");
//										for (Country c : attack.getConnectedCountries()) 
//										{
//											System.out.println(c.getName());
//										}
//										System.out.println("Please input the name of the defender:");
//										defender = key.nextLine();
//										String[] defenderCheck = isDefenderCorrect(defender, attack);
//										System.out.println(defenderCheck[1]);
//										if (defenderCheck[0].equals("0")) 
//										{
//											continue;
//										} else 
//										{
//											defend = isCountryConnectedtoAttacker(defender, attack);
//											break;
//										}
//									}
//									break;
//
//								}
//							}
//
//							while (true) 
//							{
//								int result[] = setRollLimt(attack, defend);
//								dicerange_attack = result[0];
//								dicerange_defend = result[1];
//								System.out.println("Attacker army:" + attack.getArmyCount());
//								System.out.println("defender army:" + defend.getArmyCount());
//								System.out.println(
//										"Attacker "+attack.getRuler().getName()+ ": Please input the number of dice you want to roll. It should be at most "
//												+ dicerange_attack + " :");
//								diceattack = key.nextInt();
//								String clear = key.nextLine();
//								if (diceattack > dicerange_attack) 
//								{
//									System.out.println("The number you input is unavailable. Please input again!");
//									continue;
//								} else 
//								{
//									System.out.println("Defender " + defend.getRuler().getName()
//											+ ": Please input the number of dice you want to roll. It should be at most "
//											+ dicerange_defend + " :");
//									dicedefend = key.nextInt();
//									String clear1 = key.nextLine();
//									if (dicedefend > dicerange_defend) 
//									{
//										System.out.println("The number you input is unavailable. Please input again!");
//										continue;
//									} 
//									rollDice(diceattack, dicedefend, attack, defend);
//									System.out.println("Attacker army:" + attack.getArmyCount());
//									System.out.println("defender army:" + defend.getArmyCount());
//									if (defend.getArmyCount() == 0) 
//									{
//										defend.setRuler(p);
//										System.out.println(defend.getRuler().getName());
//										System.out.println("You have already occupied this country!");
//										break;
//									} else 
//									{
//										System.out.println("Do you want to continue attacking this country? Y/N");
//										String continueornot;
//										continueornot = key.nextLine();
//										if (continueornot.equalsIgnoreCase("Y")) 
//										{
//											continue;
//										} else 
//										{
//											break;
//										}
//									}
//
//								}
//							}
//						} else 
//						{
//							break;
//						}
//
//					}
//
//                    System.out.println("\n---------------------------------------------");
//                    System.out.println("Time To Fortification");
//                    System.out.println("---------------------------------------------\n");
//                    String firstcountry="";
//                    String secondcountry="";
//                    Country country1;
//                    Country country2;
//                    int armiesformove;
//                    ArrayList<Country> CountriesArrivedbyPath = new ArrayList<>();
//					while (true) 
//					{
//						System.out.println("Please input the name of the first country:");
//						firstcountry = key.nextLine();
//						String[] result = new String[2];
//						result = isCountryBelongtoPlayer(firstcountry,p);
//						if(result[0]=="0") 
//						{
//							System.out.println(result[1]);
//							continue;
//						}
//						country1 = p.getCountry(firstcountry);
//						CountriesArrivedbyPath = getCountriesArrivedbyPath(country1, country1, CountriesArrivedbyPath);
//						if(CountriesArrivedbyPath.isEmpty()) 
//						{
//							System.out.println("There is no accessible country to the first country!");
//							continue;
//						}
//						System.out.println("There are accessible countries to the first country:");
//						for(Country c: CountriesArrivedbyPath) 
//						{
//							System.out.println(c.getName());
//						}
//						break;
//					}
//					System.out.println("Please input the name of the second country:");
//					while(true) 
//					{
//						secondcountry = key.nextLine();
//						if(isCountryBelongedtoAccessibleCountries(secondcountry, CountriesArrivedbyPath)) 
//						{
//							System.out.println("The first and second countries are determined!");
//							break;
//						}
//						System.out.println("The second country you input is unaccessible. Please input again:");
//						continue;
//					}
//					country2 = p.getCountry(secondcountry);
//					System.out.println("Number of armies on the first country: " + country1.getArmyCount());
//					System.out.println("Number of armies on the second country: " + country2.getArmyCount());
//					System.out.println("Please input the number of armies you want to move. It should be less at most "+ (country1.getArmyCount()-1) +":");
//					while (true) 
//					{
//						armiesformove = key.nextInt();
//						key.nextLine();
//						if ((armiesformove < 1)||(armiesformove > (country1.getArmyCount()-1))) 
//						{
//							System.out.println("The number is invalid. Please input again:");
//							continue;
//						}
//						break;
//					}
//					country1.reduceArmyCount(armiesformove);
//					country2.setArmyCount(armiesformove);
//					System.out.println("Move successfully");
//					System.out.println("\n---------------------------------------------");
//                    System.out.println("Next Round");
//                    System.out.println("---------------------------------------------\n");
//				}
//			} END OF WHILE LOOP

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
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
		} catch (DuplicatesException ex) {
			Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CountLimitException ex) {
			Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CannotFindException ex) {
			Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
		}
	} // main end

	public void printMenu() {
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
