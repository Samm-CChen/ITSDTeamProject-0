package structures;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;

import structures.basic.Deck;
import structures.basic.Hand;
import structures.basic.BetterUnit;
import structures.basic.Board;
import structures.basic.AiPlayer;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import  structures.basic.Avator;
/**
 * This class can be used to hold information about the on-going game.
 * Its created with the GameActor.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class GameState {

	
	public boolean gameInitalised = false;
	public boolean isGameActive = false;

	public boolean something = false;

	public Board board = null;

	public Player player1 = null;
	public Player player2 = null;

	public Avator avatar = null;      // p1 avatar on board
	public Avator aiAvatar = null;    // p2 avatar on board

	public ArrayList<Unit> summonedUnits = new ArrayList<>();

	public Deck player1Deck;
	public Deck player2Deck;

	public Hand player1Hand;
	public Hand player2Hand;




	// turn state

	public boolean player1Turn = true;
	public int playerTurnNumber = 1;
	public int aiTurnNumber =1;

}
