package events;

import com.fasterxml.jackson.databind.JsonNode;
import structures.basic.Board;
import akka.actor.ActorRef;
import demo.CommandDemo;
import demo.Loaders_2024_Check;
import structures.GameState;
import structures.basic.Tile;
import structures.basic.Player;
import structures.basic.Avatar;
import structures.basic.AiPlayer;
import utils.StaticConfFiles;
import utils.BasicObjectBuilders;
import structures.basic.BetterUnit;
import commands.BasicCommands;
import structures.basic.Deck;
import structures.basic.Card;
import structures.basic.Hand;

/**
 * Indicates that both the core game loop in the browser is starting, meaning
 * that it is ready to recieve commands from the back-end.
 * 
 * { 
 *   messageType = “initalize”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Initalize implements EventProcessor{

	private static final int P1_X = 1, P1_Y = 2;
	private static final int P2_X = 7, P2_Y = 2;

	private static final int HAND_LIMIT = 6;
	private static final int START_HAND_SIZE = 3;

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {


		gameState.gameInitalised = true;
		gameState.something = true;
		gameState.isGameActive = true;

		// create board
		gameState.board = new Board(out);

		Tile p1Tile = gameState.board.returnTile(P1_X,P1_Y);
		Tile p2Tile = gameState.board.returnTile(P2_X,P2_Y);

		// create avatar
		gameState.avatar = new Avatar("P1_AVATAR");
		gameState.aiAvatar = new Avatar("P2_AVATAR");

		// create players (deck and hand are in Player class)
		gameState.player1 = new Player();
		gameState.player2 = new AiPlayer();

		// build 2 deck
		buildHumanDeck(gameState.player1);
		buildAIDeck(gameState.player2);

		//draw three cards
		gameState.player1.drawCards(3);
		gameState.player2.drawCards(3);
		renderHand(out, gameState.player1, 0);

		try { Thread.sleep(600); } catch (Exception e) {}

		// BetterUnit
		BetterUnit p1Unit = (BetterUnit) BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 40, BetterUnit.class);
		BetterUnit p2Unit = (BetterUnit) BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 41, BetterUnit.class);

		p1Unit.spawnOnBoard(out, p1Tile, gameState.board);
		p2Unit.spawnOnBoard(out, p2Tile, gameState.board);



		try { Thread.sleep(200); } catch (Exception e) {}

		p1Unit.syncFromAvatar(out, gameState.avatar);
		p2Unit.syncFromAvatar(out, gameState.aiAvatar);


		try { Thread.sleep(200); } catch (Exception e) {}

		// Make player HP equal to avatar HP
		gameState.player1.setHealth(gameState.avatar.getHealth());
		gameState.player2.setHealth(gameState.aiAvatar.getHealth());

		try { Thread.sleep(200); } catch (Exception e) {}
		BasicCommands.setPlayer1Health(out, gameState.player1);
		BasicCommands.setPlayer2Health(out, gameState.player2);
		// update UI for HP and Mana

		BasicCommands.setPlayer1Health(out, gameState.player1);
		BasicCommands.setPlayer2Health(out, gameState.player2);
		BasicCommands.setPlayer1Mana(out, gameState.player1);
		BasicCommands.setPlayer2Mana(out, gameState.player2);

		//BasicCommands.addPlayer1Notification(out, "Game Started - Your Turn", 2);

		gameState.player1Turn = true;
		gameState.playerTurnNumber = 1;
		gameState.aiTurnNumber = 1;


		//CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//Loaders_2024_Check.test(out);
	}

	private void renderHand(ActorRef out, Player player, int mode) {

		Hand hand = player.getHand();

		for (int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if (c == null) continue;

			int position = i + 1; // UI 位置 1~6
			BasicCommands.drawCard(out, c, position, mode);

			try { Thread.sleep(150); } catch (Exception e) {}
		}
	}

	private int addTwoCopies(Deck deck, String path, int id) {
		deck.addCard(BasicObjectBuilders.loadCard(path, id++, Card.class));
		deck.addCard(BasicObjectBuilders.loadCard(path, id++, Card.class));
		return id;
	}

	// Human deck: Abyssian Swarm (20 cards, two of each)
	private void buildHumanDeck(Player p) {
		Deck deck = p.getDeck();
		int id = 1;

		id = addTwoCopies(deck, "conf/gameconfs/cards/1_1_c_u_bad_omen.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_2_c_s_hornoftheforsaken.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_3_c_u_gloom_chaser.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_4_c_u_shadow_watcher.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_5_c_s_wraithling_swarm.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_6_c_u_nightsorrow_assassin.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_7_c_u_rock_pulveriser.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_8_c_s_dark_terminus.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_9_c_u_bloodmoon_priestess.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/1_a1_c_u_shadowdancer.json", id);

		deck.shuffle();
	}

	// AI deck (20 cards, two of each)

	private void buildAIDeck(Player p) {
		Deck deck = p.getDeck();
		int id = 101;

		id = addTwoCopies(deck, "conf/gameconfs/cards/2_1_c_u_skyrock_golem.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_2_c_u_swamp_entangler.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_3_c_u_silverguard_knight.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_4_c_u_saberspine_tiger.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_5_c_s_beamshock.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_6_c_u_young_flamewing.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_7_c_u_silverguard_squire.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_8_c_u_ironcliff_guardian.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_9_c_s_sundrop_elixir.json", id);
		id = addTwoCopies(deck, "conf/gameconfs/cards/2_a1_c_s_truestrike.json", id);

		deck.shuffle();
	}

}


