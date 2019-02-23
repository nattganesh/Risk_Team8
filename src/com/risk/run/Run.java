/**
 * This file is an entry point for JavaFX application. Necessary to parse the file,
 * instantiate the only Model, a selectPlayerController (controller) and SelectPlayer.fxml (view) for the selecting number of players.
 * 
 * The Model will be passed around different controller and view during this application.
 * 
 * @author Natheepan
 * @author Tianyi
 * @author Dong Jae Kim
 * @version 1.0
 * @since 1.0
 * 
 */
package com.risk.run;


import com.risk.controller.SetUpController;
import com.risk.exceptions.CannotFindException;
import com.risk.exceptions.CountLimitException;
import com.risk.exceptions.DuplicatesException;
import com.risk.model.Model;
import com.risk.utilities.RiskMapParser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Run extends Application {
    public static void main(String[] args)
    {
		/**
		 * @see javafx.application.Application
		 */
        launch(args);
    }

	/**
	 * Start is called when JavaFX is ready to be initialized
	 * 
	 * @see javafx.application.Application
	 */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
			Model model = new Model();
			String inputFile = "src/com/risk/run/inputtext/input.txt";
			RiskMapParser riskMapParser = new RiskMapParser(model, inputFile);
			riskMapParser.setUp();
			SetUpController selectController = new SetUpController(model);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/SetUp.fxml"));
			loader.setController(selectController);
			Parent root = loader.load();
		    Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();
		    primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2); 
		    primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);  
		    primaryStage.setWidth(bounds.getWidth() / 2);
		    primaryStage.setHeight(bounds.getHeight() / 2);
	        primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();

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

}
