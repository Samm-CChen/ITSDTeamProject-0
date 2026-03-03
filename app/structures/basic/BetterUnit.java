package structures.basic;

import java.util.HashSet;
import java.util.Set;
import akka.actor.ActorRef;
import commands.BasicCommands;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class BetterUnit extends Unit {

	Set<String> keywords;


	public BetterUnit() {}
	
	public BetterUnit(Set<String> keywords) {
		super();
		this.keywords = keywords;
	}


	public Set<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	};

	/** Draw this unit at tile and register it to the board. */
	public void spawnOnBoard(ActorRef out, Tile tile, Board board) {
		this.setPositionByTile(tile);
		BasicCommands.drawUnit(out, this, tile);
		board.addUnitToBoard(tile.getTilex(), tile.getTiley(), this);

	}

	/** Push model stats (Avatar) to UI + keep this Unit consistent. */
	public void syncFromAvatar(ActorRef out, Avatar avatar) {
		BasicCommands.setUnitHealth(out, this, avatar.getHealth());
		BasicCommands.setUnitAttack(out, this, avatar.getAttack());
	}


	
	public static void main(String[] args) {
		
		BetterUnit unit = (BetterUnit)BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, BetterUnit.class);
		Set<String> keywords = new HashSet<String>();
		keywords.add("MyKeyword");
		unit.setKeywords(keywords);
		
		System.err.println(unit.getClass());
		
	}
}
