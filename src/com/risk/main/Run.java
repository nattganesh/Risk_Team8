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
package com.risk.main;

import com.risk.controller.GamePhaseController;
import com.risk.controller.MapEditorController;
import com.risk.model.exceptions.CannotFindException;
import com.risk.model.exceptions.CountLimitException;
import com.risk.model.exceptions.DuplicatesException;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;



public class Run extends Application {

//	  /**
//		 * This method rolls dices of attacker and defender at attack phase. 
//		 * The number of armies of both countries decreases according to the rolling results
//		 * 
//		 * @param diceattack The number of dice attacker wants to roll
//		 * @param dicedefend The number of dice defender wants to roll
//		 * @param attack The country which invokes the attack
//		 * @param defend The country which is attacked
//		 */
//	    public static void rollDice(int diceattack, int dicedefend, Country attack, Country defend) 
//	    {
//			int[] dattack = new int[diceattack];
//			int[] ddefend = new int[dicedefend];
//			int rolltime;
//			for (int i = 0; i < diceattack; i++) 
//			{
//				dattack[i] = Dice.roll();
//			}
//			for (int i = 0; i < dicedefend; i++)
//			{
//				ddefend[i] = Dice.roll();
//			}
//			Arrays.sort(dattack);
//			Arrays.sort(ddefend);
//			if (diceattack >= dicedefend)
//			{
//				rolltime = dicedefend;
//			} else 
//			{
//				rolltime = diceattack;
//			}
//			for (int i = 0; i < rolltime; i++)
//			{
//				if (defend.getArmyCount() != 0) 
//				{
//					if (dattack[diceattack - 1 - i] > ddefend[dicedefend - 1 - i]) 
//					{
//						defend.reduceArmyCount(1);
//					} else 
//					{
//						attack.reduceArmyCount(1);
//					}
//				} else 
//				{
//					System.out.println("You have already occupied this country!");
//					break;
//				}
//			}
//		}
//		
//	    /**
//	   	 * This method gets the maximum of number of dices the attacker and defender can roll
//	   	 * 
//	   	 * @param attack The country which invokes the attack
//	   	 * @param defend The country which is attacked
//	   	 * @return The result corresponding to the number of armies of attacker and defender
//	   	 */
//		public static int[] setRollLimt(Country attack, Country defend)
//		{
//			int dicerange_attack;
//			int dicerange_defend;
//			int[] result = new int[2];
//			if ((attack.getArmyCount() - 1) > 3) 
//			{
//				dicerange_attack = 3;
//			} else 
//			{
//				dicerange_attack = attack.getArmyCount() - 1;
//			}
//			if (defend.getArmyCount() >= 2) 
//			{
//				dicerange_defend = 2;
//			} else 
//			{
//				dicerange_defend = 1;
//			}
//			result[0] = dicerange_attack;
//			result[1] = dicerange_defend;
//			return result;
//		}
//		
//		 /**
//	   	 * This method checks if the country player inputs is belonged to the player
//	   	 * Return the instance of Country if it is belonged to the player
//	   	 * 
//	   	 * @param c The name of country the player inputs
//	   	 * @param p The player who is in his turn
//	   	 * @return The result corresponding to name and the player
//	   	 */
//		public static Country isCountryBelongedtoPlayer(String c, Player p) 
//		{
//			String countryNeedtoCheck = c;
//			Country result = null;
//			for (Country country : p.getOccupiedCountries()) 
//			{
//				if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
//				{
//					result = country;
//					break;
//				}
//			}
//			return result;
//		}
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
//		}else{
//		result[0] = "1";}
//		return result;
//	}
	
//		 /**
//	   	 * This method checks if the country defender inputs is connected to the attacker
//	   	 * Return the instance of Country if it is connected to the attacker
//	   	 * 
//	   	 * @param c The name of country the defender inputs
//	   	 * @param attack The country which invokes the attack
//	   	 * @return The result corresponding to name and the attacker
//	   	 */
//		public static Country isCountryConnectedtoAttacker(String c, Country attack) 
//		{
//			String countryNeedtoCheck = c;
//			Country result = null;
//			for (Country country : attack.getConnectedCountries()) 
//			{
//				if (country.getName().equalsIgnoreCase(countryNeedtoCheck)) 
//				{
//					result=country;
//					break;
//				}
//			}
//			return result;
//		}
//		
//		/**
//	   	 * This method checks if the country defender inputs is belonged to the player, 
//	   	 * and if the number of armies is enough or not
//	   	 * Return the result to be displayed
//	   	 * 
//	   	 * @param c The name of country the player inputs
//	   	 * @param p The player who is in his turn
//	   	 * @return The result corresponding to name and the player
//	   	 */
//		public static String[] checkCountryValidation(String c, Player p) 
//		{
//			String countryNeedtoCheck = c;
//			String[] result = new String[2];
//			Country attack = isCountryBelongedtoPlayer(countryNeedtoCheck, p);
//			if (attack == null) 
//			{
//				result[0] = "0";
//				result[1] = "This country does not belong to you! Please input again!";
//			} else if (attack.getArmyCount() < 2) 
//			{
//				result[0] = "0";
//				result[1] = "You don't have enough armies in this country! Please input again!";
//			}
//			else{result[0] = "1";}
//			return result;
//		}
//		
//		/**
//	   	 * This method get all accessible countries to the country the player inputs 
//	   	 * The method sends back the result to the player
//	   	 * 
//	   	 * @param country The country whose connected countries are being checked
//	   	 * @param firstcountry The country the player wants to find accessible countries to it
//	   	 * @param countries The list saves the countries accessible
//	   	 * @return The result corresponding to country the player input
//	   	 */
//		public static ArrayList<Country> getCountriesArrivedbyPath(Country country, Country firstCountry,ArrayList<Country> countries)
//		{
//			Player p = country.getRuler();
//			for(Country c: country.getConnectedCountries()) 
//			{
//				Player player =c.getRuler();
//				if(player.getName().equals(p.getName())) 
//				{
//					if(isCountryDuplicated(c,firstCountry, countries)) 
//					{
//						countries.add(c);
//						countries = getCountriesArrivedbyPath(c,firstCountry, countries);
//					}
//				}
//			}
//			return countries;
//		}
//		
//		/**
//	   	 * This method checks if any country belonged to the player has an accessible country
//	   	 * The method sends back the result to the player
//	   	 * 
//	   	 * @param countries The countries occupied by the player
//	   	 * @return The result corresponding to countries
//	   	 */
//		public static ArrayList<Country> isAnyCountriesConnected(ArrayList<Country> countries) {
//			ArrayList<Country> result = new ArrayList<>();
//			for(int i = 0; i< countries.size()-1; i++) {
//				for(int j = i+1; j < countries.size() ; j++ ) {
//					ArrayList<Country> temp = new ArrayList<>();
//					temp = getCountriesArrivedbyPath(countries.get(i),countries.get(i),temp);
//					if(temp.contains(countries.get(j))) {
//						result.add(countries.get(i));
//						result.add(countries.get(j));
//					}
//				}
//			}
//			return result;
//		}
//		
//
//		/**
//	   	 * This method checks if the country which is newly found accessible is already in the result list
//	   	 * The method sends back the result
//	   	 * 
//	   	 * @param country The country whose connected countries are being checked
//	   	 * @param firstcountry The country the player wants to find accessible countries to it
//	   	 * @param countries The list saves the countries accessible
//	   	 * @return The result corresponding to the list
//	   	 */
//		public static boolean isCountryDuplicated(Country country, Country firstCountry, ArrayList<Country> countries) 
//		{
//			int i = 0;
//			if (country.getName().equals(firstCountry.getName())) 
//			{
//				i = 1;
//			} else 
//			{
//				for (Country c : countries) 
//				{
//					if (c.getName().equalsIgnoreCase(country.getName())) 
//					{
//						i = 1;
//					}
//				}
//			}
//			if (i == 0) 
//			{
//				return true;
//			}
//			return false;
//		}
//		
//
//		/**
//	   	 * This method checks if the country the player inputs is included in the list of accessible countries
//	   	 * 
//	   	 * @param secondcountry The name of the country the player wants to move armies to
//	   	 * @param countries The list saves the countries accessible
//	   	 * @return The result corresponding to the list
//	   	 */
//		public static boolean isCountryBelongedtoAccessibleCountries(String secondcountry, ArrayList<Country> countries) 
//		{
//			int i=0;
//			for(Country c: countries) 
//			{
//				if(c.getName().equalsIgnoreCase(secondcountry)) 
//				{
//					i=1;
//				}
//			}
//			if(i==1) 
//			{
//				return true;
//			}
//			else 
//			{
//				return false;
//			}
//		}
//		
//		/**
//	   	 * This method checks if the country the player inputs is available to attack
//	   	 * This method sends the result back to the player
//	   	 * 
//	   	 * @param s The name of country the player inputs
//	   	 * @param attack The country which invokes an attack
//	   	 * @return The result corresponding to the name and the attacker
//	   	 */
//		public static String[] isDefenderCorrect(String c, Country attack) 
//		{
//			String countryNeedtoCheck = c;
//			Player p = attack.getRuler();
//			String[] result = new String[2];
//			if (isCountryBelongedtoPlayer(countryNeedtoCheck, p) != null) 
//			{
//				result[0] = "0";
//				result[1] = "You can't attack your own country! Please input again!";
//			} else if (isCountryConnectedtoAttacker(countryNeedtoCheck, attack)==null) 
//			{
//				result[0] = "0";
//				result[1] = "The defender you input is not connected to your attacker. Please try again!";
//			} else 
//			{
//				result[0] = "1";
//				result[1] = "The attacker and the defender are determined!";
//			}
//			return result;
//		}
//	public static boolean doesPlayerHaveEnoughArmies(Player p) {
//		ArrayList<Country> countries = p.getOccupiedCountries();
//		int i=0;
//		for(Country c: countries) {
//			if(c.getArmyCount()>1) {
//				i=1;
//			}
//		}
//		if(i==1) {
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
//	
//	public static boolean checkCardNumberInput(int cardPlayerChoose[]) {
//		int check = 0;
//		for(int i=0; i<3; i++) {
//			for(int j=i+1; j<3; j++) {
//				if(cardPlayerChoose[i]==cardPlayerChoose[j]) {
//					check = 1;
//				}
//			}
//		}
//		if(check == 1) {
//			return false;
//		}
//		else {
//			return true;
//		}
//	}
//
//	public static boolean checkCardCategory(int cardPlayerChoose[],ArrayList<Card> cards) {
//		String[] cardCategory = new String[3];
//		for (int i = 0; i < 3; i++) {
//			card[i] = cards.get(cardPlayerChoose[i]);
//			cardCategory[i] = card[i].getCatagory();
//		}
//		if (((cardCategory[0].equals(cardCategory[1])) && (cardCategory[0].equals(cardCategory[2])))
//				|| ((!(cardCategory[0].equals(cardCategory[1])))
//						&& (!(cardCategory[0].equals(cardCategory[2])))
//						&& (!(cardCategory[1].equals(cardCategory[2]))))) {
//			return true;
//		} else {
//			return false;
//		}
//	}
    public static void main(String[] args)
    {
        launch(args);
        
       
//        Output.generate("NewOutputFileTEMP");
    }

    /**
     * Start is called when JavaFX is ready to be initialized
     *
     * @throws IOException
     * @throws DuplicatesException
     * @throws CannotFindException
     * @throws CountLimitException
     *
     * @see javafx.application.Application
     */
    
    @Override
    public void start(Stage primaryStage) throws CountLimitException, CannotFindException, DuplicatesException, IOException
    {
//        PlayerModel players = new PlayerModel();
//        MapModel maps = new MapModel();
//        GamePhaseModel gamephase = new GamePhaseModel();
//
//        GamePhaseController gController = new GamePhaseController(gamephase, maps, players);
//        gController.setUp(primaryStage);
    	
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/MapSelector.fxml"));
			GamePhaseController gamephase = new GamePhaseController(primaryStage);
			MapEditorController mapController = new MapEditorController();
			loader.setController(mapController);
		
			Parent root = loader.load();
		    Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();
		    primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2); 
		    primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);  
		    primaryStage.setWidth(bounds.getWidth() / 2);
		    primaryStage.setHeight(bounds.getHeight() / 2);
	        primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();

//        try
//        {
//			PlayerModel players = new PlayerModel();
//			MapModel maps = new MapModel();
//			GamePhaseModel gamephase = new GamePhaseModel();
//
//			GamePhaseController gController = new GamePhaseController(gamephase, maps, players);
//			gController.setUp();
//			String inputFile = "src/com/risk/main/mapTextFiles/input.txt";
//			RiskMapParser riskMapParser = new RiskMapParser(maps, inputFile);
//			riskMapParser.setUp();
//			SetUpController selectController = new SetUpController(players, maps);
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/risk/view/SetUp.fxml"));
//			loader.setController(selectController);
//			Parent root = loader.load();
//		    Screen screen = Screen.getPrimary();
//		    Rectangle2D bounds = screen.getVisualBounds();
//		    primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2); 
//		    primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);  
//		    primaryStage.setWidth(bounds.getWidth() / 2);
//		    primaryStage.setHeight(bounds.getHeight() / 2);
//	        primaryStage.setScene(new Scene(root, 300, 275));
//	        primaryStage.show();
//
//	        System.out.println("\n---------------------------------------------");
//            System.out.println("Time To Attack");
//            System.out.println("---------------------------------------------\n");
//            String attacker = "";
//            String defender = "";
//            Country attack = null;
//            Country defend = null;
//
//            int dicerange_attack;
//            int dicerange_defend;
//            int diceattack;
//            int dicedefend;
//            int rolltime;
//            int occupyTime=0;
//            while (true) 
//			{
//				System.out.println("Do you want to invoke an attack? Y/N");
//				String answer1 = key.nextLine();
//				if (answer1.equalsIgnoreCase("Y")) 
//				{
//					while (true) 
//					{
//						System.out.println("Please input the name of the attacker:");
//						attacker = key.nextLine();
//						String[] attackCheck = isCountryBelongtoPlayer(attacker, p);
//						if (attackCheck[0]=="0") 
//						{
//							System.out.println(attackCheck[1]);
//							continue;
//						} else 
//						{	
//							attack = isCountryBelongedtoPlayer(attacker, p);
//							while (true) 
//							{
//								System.out.println("These are countries connected to the attacker:");
//								for (Country c : attack.getConnectedCountries()) 
//								{
//									if(!(c.getRuler().getName().equals(p.getName())))
//									System.out.println(c.getName());
//								}
//								System.out.println("Please input the name of the defender:");
//								defender = key.nextLine();
//								String[] defenderCheck = isDefenderCorrect(defender, attack);
//								System.out.println(defenderCheck[1]);
//								if (defenderCheck[0].equals("0")) 
//								{
//									continue;
//								} else 
//								{
//									defend = isCountryConnectedtoAttacker(defender, attack);
//									break;
//								}
//							}
//							break;
//
//						}
//					}
//
//					while (true) 
//					{
//						int result[] = setRollLimt(attack, defend);
//						dicerange_attack = result[0];
//						dicerange_defend = result[1];
//						System.out.println("Attacker army:" + attack.getArmyCount());
//						System.out.println("defender army:" + defend.getArmyCount());
//						System.out.println(
//								"Attacker "+attack.getRuler().getName()+ ": Please input the number of dice you want to roll. It should be at most "
//										+ dicerange_attack + " :");
//						diceattack = key.nextInt();
//						String clear = key.nextLine();
//						if (diceattack > dicerange_attack) 
//						{
//							System.out.println("The number you input is unavailable. Please input again!");
//							continue;
//						} else 
//						{
//							System.out.println("Defender " + defend.getRuler().getName()
//									+ ": Please input the number of dice you want to roll. It should be at most "
//									+ dicerange_defend + " :");
//							dicedefend = key.nextInt();
//							String clear1 = key.nextLine();
//							if (dicedefend > dicerange_defend) 
//							{
//								System.out.println("The number you input is unavailable. Please input again!");
//								continue;
//							} 
//							rollDice(diceattack, dicedefend, attack, defend);
//							System.out.println("Attacker army:" + attack.getArmyCount());
//							System.out.println("defender army:" + defend.getArmyCount());
//							if (defend.getArmyCount() == 0) 
//							{
//								occupyTime++;
//								defend.setRuler(p);
//								System.out.println(defend.getRuler().getName());
//								System.out.println("You have already occupied this country!");
//								break;
//							} else 
//							{
//								System.out.println("Do you want to continue attacking this country? Y/N");
//								String continueornot;
//								continueornot = key.nextLine();
//								if (continueornot.equalsIgnoreCase("Y")) 
//								{
//									continue;
//								} else 
//								{
//									break;
//								}
//							}
//
//						}
//					}
//				} else 
//				{
//					if(occupyTime>0) {
//						Card cardForOccupy = deck.sendCard(p.getName());
//						p.addCard(cardForOccupy);
//						System.out.println("You got one card in this round!: "+cardForOccupy.getCatagory());
//					}
//					break;
//				}
//
//			}
//
//			System.out.println("\n---------------------------------------------");
//			System.out.println("Time To Fortification");
//			System.out.println("---------------------------------------------\n");
//			String firstcountry = "";
//			String secondcountry = "";
//			Country country1;
//			Country country2;
//			int armiesformove;
//
//			ArrayList<Country> CountriesArrivedbyPath = new ArrayList<>();
//			ArrayList<Country> CountriesConnected = new ArrayList<>();
//			CountriesConnected = isAnyCountriesConnected(p.getOccupiedCountries());
//			if(doesPlayerHaveEnoughArmies(p)) {
//			if (!CountriesConnected.isEmpty()) {
//				System.out.println("Those are countries that are accessible:");
//				for (int i = 0; i < CountriesConnected.size(); i++) {
//					System.out.print(CountriesConnected.get(i).getName());
//					if (i % 2 == 1) {
//						System.out.println();
//					} else {
//						System.out.print(",");
//					}
//				}
//				while (true) {
//					System.out.println("Please input the name of the first country:");
//					firstcountry = key.nextLine();
//					String[] result = new String[2];
//					result = checkCountryValidation(firstcountry, p);
//					if (result[0] == "0") {
//						System.out.println(result[1]);
//						continue;
//					}
//					country1 = p.getCountry(firstcountry);
//					CountriesArrivedbyPath = getCountriesArrivedbyPath(country1, country1,
//							CountriesArrivedbyPath);
//					if (CountriesArrivedbyPath.isEmpty()) {
//						System.out.println("There is no accessible country to the first country!");
//						continue;
//					}
//					if (country1.getArmyCount() == 1) {
//						System.out.println("There is no more arimes to be moved from the first country!");
//						continue;
//					}
//					System.out.println("There are accessible countries to the first country:");
//					for (Country c : CountriesArrivedbyPath) {
//						System.out.println(c.getName());
//					}
//					break;
//				}
//				System.out.println("Please input the name of the second country:");
//				while (true) {
//					secondcountry = key.nextLine();
//					if (isCountryBelongedtoAccessibleCountries(secondcountry, CountriesArrivedbyPath)) {
//						System.out.println("The first and second countries are determined!");
//						break;
//					}
//					System.out.println("The second country you input is unaccessible. Please input again:");
//					continue;
//				}
//				country2 = p.getCountry(secondcountry);
//				System.out.println("Number of armies on the first country: " + country1.getArmyCount());
//				System.out.println("Number of armies on the second country: " + country2.getArmyCount());
//				System.out.println("Please input the number of armies you want to move. It should be at most "
//						+ (country1.getArmyCount() - 1) + ":");
//				while (true) {
//					armiesformove = key.nextInt();
//					key.nextLine();
//					if ((armiesformove < 1) || (armiesformove > (country1.getArmyCount() - 1))) {
//						System.out.println("The number is invalid. Please input again:");
//						continue;
//					}
//					break;
//				}
//				country1.reduceArmyCount(armiesformove);
//				country2.setArmyCount(armiesformove);
//				System.out.println("Move successfully");
//			} else {
//          	System.out.println("You have no occupied countries connected to each other");
//          }}
//			else {
//				System.out.println("None of your countries has enough armies for fortification.");
//			}
//			System.out.println("\n---------------------------------------------");
//            System.out.println("Next Round");
//            System.out.println("---------------------------------------------\n");
//		}
//	} END OF WHILE LOOP
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
//        }
//        catch (FileNotFoundException ex)
//        {
//            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        catch (DuplicatesException ex)
//        {
//            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        catch (CountLimitException ex)
//        {
//            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        catch (CannotFindException ex)
//        {
//            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
