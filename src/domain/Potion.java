package domain;

import enums.Stat;
import java.io.PrintStream;

public class Potion extends Item {

    private int amount;
    private int turnDuration;
    private String prefix;
    private Stat[] stat;

    public Potion(String name, javafx.scene.image.Image image, String prefix, Stat stat, int amount) {
        super(name, image, stat);
        this.prefix = prefix;
        this.amount = amount;
        setMaxStackSize(10);
        setStackSize(1);
    }

    public void useItem(Player player) {
        if (getStackSize() >= 1) {
            switch (getStat()) {
                case HP:
                    player.addHP(this.amount);
                    System.out.println("\n" + toString() + " healed you for " + this.amount + " hp!");
                    System.out.println("Current hp: " + player.getHP());
                    setStackSize(getStackSize() - 1);
                    if (getStackSize() == 0) {
                        destroyItem();
                    }
                    break;
                case MANA:
                    player.addMANA(this.amount);
                    System.out.println(toString() + " healed you for " + this.amount + " hp!");
                    setStackSize(getStackSize() - 1);
                    break;

                case POISON:
                    player.addHP(-this.amount);
                    System.out.println("\n" + toString() + " damaged you for " + this.amount + " hp!");
                    System.out.println("Current hp: " + player.getHP());
                    setStackSize(getStackSize() - 1);
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case STA:
                    player.setSTA(player.getSTA() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " stamina!");
                    System.out.println("Current stamina: " + player.getSTA());
                    setStackSize(getStackSize() - 1);
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case STR:
                    player.setSTR(player.getSTR() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " strength!");
                    System.out.println("Current strength: " + player.getSTR());
                    setStackSize(getStackSize() - 1);
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case DEF:
                    player.setDEF(player.getDEF() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " defense!");
                    System.out.println("Current defense: " + player.getDEF());
                    setStackSize(getStackSize() - 1);
                    if (getStackSize() == 0) {
                        destroyItem();
                    }
                    break;
            }
        } else {
            System.out.println("Nothing to use.");
        }
    }

    public void destroyItem() {
        Inventory.inventory.remove(this);
    }

    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.amount;
        hash = 41 * hash + this.turnDuration;
        hash = 41 * hash + java.util.Objects.hashCode(this.prefix);
        hash = 41 * hash + java.util.Objects.hashCode(this.stat);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Potion other = (Potion) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (this.turnDuration != other.turnDuration) {
            return false;
        }
        if (!java.util.Objects.equals(this.prefix, other.prefix)) {
            return false;
        }
        if (this.stat != other.stat) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.prefix + getName();
    }
}
