package structures.basic;

/**
 * A basic representation of the Player. A player
 * has health and mana.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Player {

	int health;
	int mana;
	private final Deck deck = new Deck();
	private final Hand hand = new Hand(6);


	public Player() {
		super();
		this.health = 20;
		this.mana = 0;
	}
	public Player(int health, int mana) {
		super();
		this.health = health;
		this.mana = mana;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public Deck getDeck(){ return deck; }
	public Hand getHand(){ return hand; }


	public boolean drawCard(){
		Card c = deck.draw();
		if (c == null) return false;
		return hand.add(c);
	}

	public void drawCards(int n){
		for (int i = 0; i < n; i++){
			if (!drawCard()) break;
		}
	}
}

	

