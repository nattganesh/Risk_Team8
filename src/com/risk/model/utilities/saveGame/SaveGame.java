/**
 * This class is necessary for saving a game to file
 *
 * @author Natheepan
 * @version 3.0
 */
package com.risk.model.utilities.saveGame;

import com.risk.model.GamePhaseModel;
import com.risk.model.MapModel;
import com.risk.model.PlayerPhaseModel;
import com.risk.model.card.Card;
import com.risk.model.map.Country;
import com.risk.model.player.Player;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.PrintStream;
import java.util.ArrayList;


public class SaveGame 
{

    /**
     * This is a constructor for the Output class
     */
    private SaveGame()
    {
    }

    
    /**
     *
     * @param gameFileName name of file to save game
     * @param mapName name of map file used in game
     * @return true if file has been generated, otherwise false
     */
    public static boolean generate(String gameFileName, String mapName)
    {
        ArrayList<Country> countries = MapModel.getMapModel().getCountries();
        ArrayList<Player> players = PlayerPhaseModel.getPlayerModel().getPlayers();
        Player currentPlayer = PlayerPhaseModel.getPlayerModel().getCurrentPlayer();
        String currentPhaseName = GamePhaseModel.getGamePhaseModel().getPhase();
        PrintStream printOut = System.out;

        try (PrintStream fileOut = new PrintStream("src/com/risk/main/savedGameFiles/" + gameFileName + ".txt"))
        {
            System.setOut(fileOut);

            System.out.println("NAME OF MAP FILE");
            System.out.println(mapName);

            System.out.println("SET PLAYER ORDER AND CARDS");
            for (Player player : players)
            {
                // Player Name
                System.out.print(player.getName());
                // Player Type
                String playerType = player.getName().substring(0, player.getName().length()-1);
                int i = playerType.lastIndexOf(".");
                System.out.print("," + playerType.substring(i + 1));
                // Cards
                for (Card card : player.getCards())
                {
                    System.out.print("," + card.getCatagory());
                }
                System.out.print("\n");
            }
            System.out.println("SET PLAYERS AND ARMY IN COUNTRY");
            for (Country country : countries)
            {
                System.out.println(country.getName() + "," + country.getRuler().getName() + "," + country.getArmyCount());
            }
            System.out.println("CURRENT PHASE");
            System.out.println(currentPhaseName);
            System.out.println("CURRENT PLAYER");
            System.out.println(currentPlayer.getName());
            fileOut.close();
            System.setOut(printOut);
            return true;
        }
        catch (Exception ex)
        {
            Logger.logMsg(0, ex.getMessage());
            return false;
        }
        finally
        {
            System.setOut(System.out);
        }
    }

}
