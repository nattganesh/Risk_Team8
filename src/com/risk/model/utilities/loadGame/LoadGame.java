
/**
 * This class is necessary for loading a game from a file
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.utilities.loadGame;

import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.risk.model.utilities.FileParser;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class LoadGame 
{

	static String currentPhase;
    /**
     * This is a constructor for the Output class
     */
    private LoadGame()
    {
    }

    
    /**
     * @param gameFileName name of file to load game from
     * @return true if file has been generated, otherwise false
     */
    public static boolean generate(String gameFileName)
    {
    	PrintStream printOut = System.out;
        String gameFile = "src/com/risk/main/savedGameFiles/" + gameFileName + ".txt";
        String text = "";
        try (Scanner inputGame = new Scanner(new File(gameFile)))
        {
            if (inputGame.hasNext())
            {
                text = inputGame.nextLine();
                if (text.equalsIgnoreCase("NAME OF MAP FILE") && inputGame.hasNextLine())
                {
                    text = inputGame.nextLine();
                  
                    MapModel.getMapModel().setMapType(text);
                    String mapFile = "src/com/risk/main/mapTextFiles/" + text + ".txt";
               
                    try (Scanner inputMap = new Scanner(new File(mapFile)))
                    {
                        FileParser mapFileParser = new FileParser();
                        mapFileParser.init(inputMap);
                    }
                }
                ArrayList<Country> countries = MapModel.getMapModel().getCountries();
    
                if (countries.isEmpty())
                {
                    return false;
                }
            }
            ArrayList<Country> countries = MapModel.getMapModel().getCountries();
            ArrayList<Player> players = PlayerPhaseModel.getPlayerModel().getPlayers();
            if (inputGame.hasNext())
            {
                text = inputGame.nextLine();
                if (text.equalsIgnoreCase("SET PLAYER ORDER AND CARDS") && inputGame.hasNextLine())
                {
                    text = inputGame.nextLine();
                   
                    while (inputGame.hasNextLine() && !text.equals("SET PLAYERS AND ARMY IN COUNTRY"))
                    {
                        String nameOfPlayer = text.substring(0, text.indexOf(","));
                        text = text.substring(text.indexOf(",") + 1, text.length());
                        String typeOfPlayer;
                        if (!text.contains(","))
                        {
                            typeOfPlayer = text;
                            text = "";
                        }
                        else
                        {
                            typeOfPlayer = text.substring(0, text.indexOf(","));
                            text = text.substring(text.indexOf(",") + 1, text.length());
                        }
                       
                        Player p = new Player(nameOfPlayer);                         
                        PlayerPhaseModel.getPlayerModel().addPlayer(p);
                        if (!text.trim().isEmpty() && !text.contains(","))
                        {
                        	System.out.println("WTF");
                            p.addCard(new Card(text, p));
                        }
                        else if (!text.trim().isEmpty())
                        {
                            while (text.indexOf(",") != 0)
                            {
                                String cardName = text.substring(0, text.indexOf(","));
                                p.addCard(new Card(cardName, p));
                                text = text.substring(text.indexOf(",") + 1, text.length());
                                if (text.trim().length() <= 1)
                                {
                                    break;
                                }
                                if (!text.trim().contains(","))
                                {
                                    p.addCard(new Card(text, p));
                                    break;
                                }
                            }
                        }
                        text = inputGame.nextLine();
                    }
                }
                if (text.equalsIgnoreCase("SET PLAYERS AND ARMY IN COUNTRY") && inputGame.hasNextLine())
                {
                    text = inputGame.nextLine();
                    while (inputGame.hasNextLine() && !text.equals("CURRENT PHASE"))
                    {
                        String nameOfCountry = text.substring(0, text.indexOf(","));
                        text = text.substring(text.indexOf(",") + 1, text.length());
                        String nameOfPlayer = text.substring(0, text.indexOf(","));
                        text = text.substring(text.indexOf(",") + 1, text.length());
                        int numberOfArmy = Integer.parseInt(text);

                        for (Country c : countries)
                        {
                            if (c.getName().equals(nameOfCountry))
                            {
                                for (Player p : players)
                                {
                                    if (p.getName().equals(nameOfPlayer))
                                    {
                                        c.setRuler(p);
                                        c.setArmyCount(numberOfArmy);
                                        c.setIsOccupied(true);
                                        p.addCountry(c);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        text = inputGame.nextLine();
                    }
                }
                if (text.equalsIgnoreCase("CURRENT PHASE") && inputGame.hasNextLine())
                {
                    text = inputGame.nextLine();
                    currentPhase = text;
                    text = inputGame.nextLine();
                }
                if (text.equalsIgnoreCase("CURRENT PLAYER") && inputGame.hasNextLine())
                {
                    text = inputGame.nextLine();
                    System.out.println("CURRENT PLAYER FROM LOAD IS: " + text);
                    for (int i = 0; i < players.size(); i++)
                    {
                        if (players.get(i).getName().equals(text))
                        {
                            PlayerPhaseModel.getPlayerModel().setCurrentPlayer(i);
                            System.out.println("THAT PLAYER INDEX IS : " + i);
                            GamePhaseModel.getGamePhaseModel().setPhase("setup complete");
                            GamePhaseModel.getGamePhaseModel().setPhase(currentPhase);
                            
                            break;
                        }
                    }
                }

            }
            System.setOut(printOut);
            return true;
        }
        catch (Exception ex)
        {
            Logger.logMsg(0, ex.getMessage());
            return false;
        }
    }

}
