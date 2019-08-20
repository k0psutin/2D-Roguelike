package domain;

import generator.DungeonGenerator;
import generator.ItemGenerator;
import java.util.*;
import utils.Settings;
import utils.Utils;

public class Enemy extends NPC implements ICombat {

    private Player player;

    public Enemy(String name, enums.Type type) {
        super(name, type);
        this.setHostile(true);
    }

    public void takeDMG(int amount) {
        amount -= calcRES();
        setHP(getHP() - amount);
        if (getHP() <= 0) {
            die();
        }
        if (isAlive()) {
            attack(getTarget());
        }
    }

    public void interact(NPC target) {
        if (target != null) {
            attack(target);
        }
    }

    public void attack(NPC target) {
        if ((getTarget() == null | getTarget().isAlive())) {
            int dmg = calcDMG();
            getTarget().takeDMG(dmg);
        } else {
            System.out.println(getTarget().getName() + " is dead already!");
        }
    }

    public void npcTurn() {
        npcMove();
        System.out.println(getName() + " trying to move");
    }

    public void die() {

        setAlive(false);

        DungeonGenerator.removeNode(getMapNode());
        DungeonGenerator.npcList.remove(new utils.Coordinates(getLocX(), getLocY()));

        System.out.println("\n" + getName() + " died!");

        giveLoot();

        getTarget().setTarget(null);
        setTarget(null);
    }

    @Override
    public void giveLoot() {
        System.out.println("You received " + getGold() + " gold and " + getExperience() + " experience!");
        getTarget().setGold(getGold() + getGold());
        getTarget().gainExperience(getExperience());
        List<Item> itemList = ItemGenerator.randomDrop();
        if (itemList.isEmpty()) {
            System.out.println("Empty loot list. :(");
        }
        itemList.forEach(item -> {
            getTarget().getLoot(item);
        });
    }

    public int calcDMG() {
        return Math.abs(Utils.randomize(getDMG() + getSTR(), getDMG()));
    }

    public int calcRES() {
        return getDEF();
    }

    public void npcMove() {
        int direction = Utils.randomize(4, 0);
        System.out.println(getName() + " trying to move!");
        chase();
    }

    public void chase() {
    }
}
