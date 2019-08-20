package domain;

import controller.PlayerController;
import domain.ICombat;
import domain.Inventory;
import domain.Item;
import domain.NPC;
import enums.Type;
import utils.Settings;
import utils.Utils;

public class Player
        extends NPC
        implements ICombat {

    private Inventory inventory = new Inventory();
    private static final int baseEXP = 350;
    private int nextLevel = 350;

    public Player(String name) {
        super(name);
        this.increaseStats();
        this.setType(Type.PLAYER);
        this.setLocX(5);
        this.setLocY(5);
        this.setDMG(3);
        this.setLEVEL(1);
        this.setScreenLocX(this.getLocX() * Settings.TILESIZE);
        this.setScreenLocY(this.getLocY() * Settings.TILESIZE);
        PlayerController.setUpPlayer((Player) this);
    }

    public void pickUpItem(Item item) {
        this.inventory.addItem(item);
    }

    public void useItem(Item item) {
        if (this.inventory.equals((Object) item)) {
            this.inventory.getInventory().forEach(object -> object.useItem(this));
        }
    }

    public void interact(NPC target) {
        if (target != null) {
            this.attack(target);
        }
    }

    public void attack(NPC target) {
        if (this.getTarget() == null | this.getTarget().isAlive()) {
            int dmg = this.calcDMG();
            CombatText combatText = new CombatText(String.valueOf(dmg), "WHITE", this.getScreenLocX(), this.getScreenLocY());
            System.out.println("You hit " + this.getTarget().getName() + " for " + dmg + " dmg.");
            this.getTarget().takeDMG(dmg);
        } else {
            System.out.println(this.getTarget().getName() + " is dead already!");
        }
    }

    @Override
    public void getLoot(Item item) {
        if (item.checkStackSize()) {
            this.inventory.addItem(item);
        } else {
            System.out.println("Can't have more of those.");
        }
    }

    public Inventory playerInventory() {
        return this.inventory;
    }

    public void die() {
        this.setAlive(false);
        System.out.println("You died!");
    }

    public void takeDMG(int amount) {
        this.setHP(this.getHP() - (amount -= this.calcRES()));
        if (this.getTarget() != null) {
            CombatText combatText = new CombatText(String.valueOf(amount), "RED", this.getScreenLocX() + 16, this.getScreenLocY());
            System.out.println(this.getTarget().getName() + " hits you for " + amount + " dmg.");
        }
        if (this.getHP() <= 0) {
            this.die();
        }
    }

    public int calcRES() {
        return this.getDEF();
    }

    public void npcContact(int x, int y) {
        this.setTarget(PlayerController.player.getNPC(x, y));
        this.interact(PlayerController.player.getTarget());
    }

    public int calcDMG() {
        return Math.abs(Utils.randomize((int) (this.getDMG() + this.getSTR()), (int) this.getDMG()));
    }

    public String toString() {
        return "HP: " + this.getHP() + " Mana: " + this.getMANA() + "\nExperience: " + this.getExperience() + " Next level in: " + this.getNextLevel() + "\nInventory size: " + this.inventory.getInventorySize();
    }

    public void levelUp() {
        System.out.println("");
        System.out.println("Leveling up!");
        System.out.println("Old stats: ");
        System.out.println("STR: " + this.getSTR() + " STA: " + this.getSTA() + " INT: " + this.getINT() + " DMG: " + this.getDMG());
        int nextLevel = (int) Math.floor(350.0 * ((double) this.getLEVEL() * 1.5));
        this.setNextLevel(this.getNextLevel() + nextLevel);
        this.increaseStats();
        System.out.println("");
        System.out.println("New stats: ");
        System.out.println("STR: " + this.getSTR() + " STA; " + this.getSTA() + " INT: " + this.getINT() + " DMG: " + this.getDMG());
        System.out.println("Experience needed for next level: " + (this.getNextLevel() - this.getExperience()));
        System.out.println("Experience: " + this.getExperience() + " Next level at: " + this.getNextLevel());
    }

    public void gainExperience(int amount) {
        this.setExperience(this.getExperience() + amount);
        if (this.isLevelingUp()) {
            this.levelUp();
        }
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getNextLevel() {
        return this.nextLevel;
    }

    public boolean isLevelingUp() {
        return this.getExperience() >= this.getNextLevel();
    }

    public void increaseStats() {
        this.setSTA(this.getSTA() + 2);
        this.setSTR(this.getSTR() + 2);
        this.setINT(this.getINT() + 1);
        this.setLEVEL(this.getLEVEL() + 1);
        this.setMaxHP(this.getSTA() * NPC.baseHP);
        this.setMaxMANA(this.getINT() * NPC.baseMANA);
        this.setDMG(this.getDMG() + 1);
        this.replenishHP();
        this.replenishMANA();
    }
}
