package domain;

import enums.Tiles;
import enums.Type;
import generator.DungeonGenerator;
import java.util.Map;
import javafx.scene.Node;
import utils.Coordinates;

public abstract class NPC {

    private int HP, MANA, STA, STR, INT, LEVEL, DEF, DMG, experience, gold, locX, locY, range;
    private int maxHP = this.HP;
    private int maxMANA = this.MANA;

    public static int baseHP = 25;
    public static int baseMANA = 25;
    
    private double mapLocX, mapLocY, screenLocX, screenLocY;

    private boolean alive, hostile;

    private Node mapNode;

    private NPC target;

    private String name;

    private Type type;

    public NPC(String name, Type type) {
        this.name = name;
        this.type = type;
        this.alive = true;
        this.hostile = false;
        this.range = 1;
    }

    public NPC(String name) {
        this.name = name;
        this.alive = true;
        this.hostile = false;
    }

    public boolean isInRange(NPC npc) {
        int distanceX = Math.abs(npc.getLocX() - npc.getLocX());
        int distanceY = Math.abs(npc.getLocY() - npc.getLocY());

        if (((distanceX == 1 ? 1 : 0) & (distanceY == 1 ? 1 : 0)) != 0) {
            return true;
        }
        return false;
    }

    public abstract void takeDMG(int paramInt);

    public boolean hasTarget() {
        return getTarget() != null;
    }

    public void gainExperience(int amount) {
    }

    public NPC getNPC() {
        return this;
    }

    public NPC getNPC(int x, int y) {
        NPC npc = (NPC) DungeonGenerator.npcList.getOrDefault(new Coordinates(x, y), null);
        if (npc != null) {
            npc.setTarget(this);
        }
        return npc;
    }

    public boolean isNPC(int x, int y) {
        return getNPC(x, y) == null;
    }

    public boolean isObstacle(int x, int y) {
        if (!DungeonGenerator.tileMap.isEmpty()) {
            return !((Tiles) DungeonGenerator.tileMap.get(new Coordinates(x, y))).equals(Tiles.WALL);
        }
        return false;
    }

    public double getScreenLocX() {
        return screenLocX;
    }

    public double getScreenLocY() {
        return screenLocY;
    }

    public void setScreenLocX(double screenLocX) {
        this.screenLocX = screenLocX;
    }

    public void setScreenLocY(double screenLocY) {
        this.screenLocY = screenLocY;
    }
    
    public double getMapLocX() {
        return this.mapLocX;
    }

    public double getMapLocY() {
        return this.mapLocY;
    }

    public void setMapLocX(double mapLocX) {
        this.mapLocX += mapLocX;
    }

    public void setMapLocY(double mapLocY) {
        this.mapLocY += mapLocY;
    }

    public void setMapNode(Node mapNode) {
        this.mapNode = mapNode;
    }

    public Node getMapNode() {
        return this.mapNode;
    }

    public int getLocX() {
        return this.locX;
    }

    public int getLocY() {
        return this.locY;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public void addHP(int amount) {
        if (getHP() + amount <= getMaxHP()) {
            setHP(getHP() + amount);
        } else {
            replenishHP();
        }
    }

    public void addMANA(int amount) {
        if (getMANA() + amount <= getMaxMANA()) {
            setMANA(getMANA() + amount);
        } else {
            replenishMANA();
        }
    }

    public void replenishHP() {
        this.HP = this.maxHP;
    }

    public void replenishMANA() {
        this.MANA = this.maxMANA;
    }

    public abstract void die();

    public void interact(NPC target) {
    }

    public void gainGold(int amount) {
        setGold(getGold() + amount);
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void giveLoot() {
    }

    public void getLoot(Item item) {
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public void setLEVEL(int LEVEL) {
        this.LEVEL = LEVEL;
    }

    public void setMANA(int MANA) {
        this.MANA = MANA;
    }

    public void setMaxMANA(int maxMANA) {
        this.maxMANA = maxMANA;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setSTA(int STA) {
        this.STA = STA;
        calculateHP();
    }
    
    public void calculateHP() {
        this.maxHP = this.getSTA() * NPC.baseHP;
        System.out.println("New max HP is " + this.getMaxHP());
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public void setTarget(NPC target) {
        this.target = target;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean isHostile() {
        return this.hostile;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDEF() {
        return this.DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public NPC getTarget() {
        return this.target;
    }

    public int getSTA() {
        return this.STA;
    }

    public int getSTR() {
        return this.STR;
    }

    public int getHP() {
        return this.HP;
    }

    public int getGold() {
        return this.gold;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getINT() {
        return this.INT;
    }

    public int getLEVEL() {
        return this.LEVEL;
    }

    public int getMANA() {
        return this.MANA;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public String getName() {
        return this.name;
    }

    public int getMaxMANA() {
        return this.maxMANA;
    }

    public Type getType() {
        return this.type;
    }

    public int getDMG() {
        return this.DMG;
    }

    public void setDMG(int DMG) {
        this.DMG += DMG;
    }

    @Override
    public String toString() {
        return "x: " + getLocX() + " y: " + getLocY() + " Name: " + getName() + " Hostile: " + isHostile();
    }
}
