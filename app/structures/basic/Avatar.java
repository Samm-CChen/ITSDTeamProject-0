package structures.basic;

public class Avatar {

    private final String id;
    private int health;
    private int attack;
    private final int maxHealth;

    public Avatar(String id) {
        this.id = id;
        this.maxHealth = 20;
        this.health = this.maxHealth;
        this.attack = 2;
    }

    public String getId() { return id; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(maxHealth, health));
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMaxHealth() { return maxHealth; }

    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }
}

