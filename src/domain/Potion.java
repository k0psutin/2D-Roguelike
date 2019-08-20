package domain;

import enums.Stat;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;

public class Potion extends Item {

    private int amount;
    private int turnDuration;
    private Stat stat;

    public Potion(javafx.scene.image.Image image, String name, Stat stat, int amount, int stackSize) {
        super(name, image, stat, stackSize);
        this.amount = amount;
    }

    public void useItem(Player player) {
        if (getStackSize() >= 1) {
            switch (getStat()) {
                case HP:
                    player.addHP(this.amount);
                    System.out.println("\n" + toString() + " healed you for " + this.amount + "!");
                    System.out.println("Current hp: " + player.getHP());
                    reduceStackSize();
                    if (getStackSize() == 0) {
                        destroyItem();
                    }
                    break;
                case MANA:
                    player.addMANA(this.amount);
                    System.out.println(toString() + " restored mana by " + this.amount + "!");
                    reduceStackSize();
                    if (getStackSize() == 0) {
                        destroyItem();
                    }
                    break;

                case POISON:
                    player.takeDMG(this.amount);
                    System.out.println("\n" + toString() + " damaged you for " + this.amount + " hp!");
                    System.out.println("Why would you drink poison?");
                    System.out.println("\nCurrent hp: " + player.getHP());
                    reduceStackSize();
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case STA:
                    player.setSTA(player.getSTA() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " stamina!");
                    System.out.println("Current stamina: " + player.getSTA());
                    reduceStackSize();
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case STR:
                    player.setSTR(player.getSTR() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " strength!");
                    System.out.println("Current strength: " + player.getSTR());
                    reduceStackSize();
                    if (getStackSize() == 0) {
                        destroyItem();
                    }

                    break;
                case DEF:
                    player.setDEF(player.getDEF() + this.amount);
                    System.out.println("\n" + toString() + " added " + this.amount + " defense!");
                    System.out.println("Current defense: " + player.getDEF());
                    reduceStackSize();
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
    
    @Override
    public String toString() {
        return getName();
    }
}
