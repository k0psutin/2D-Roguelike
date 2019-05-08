package domain;

import controller.PlayerController;
import enums.Stat;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.image.Image;

public abstract class Item {

    private String name;
    private Image image;
    private Node node;
    private Stat stat;
    private int stackSize;
    private int maxStackSize;
    private Player player = PlayerController.player;

    public Item(String name, Image image, Stat stat) {
        this.name = name;
        this.image = image;
        this.stat = stat;
    }

    public Stat getStat() {
        return this.stat;
    }

    public void dmgTarget(int amount) {
        this.player.takeDMG(amount);
    }

    public void healTarget(int amount) {
        this.player.addHP(amount);
    }

    public void refreshMana(int amount) {
        this.player.addMANA(amount);
    }

    public void addStamina(int amount) {
        this.player.setSTA(this.player.getSTA() + amount);
    }

    public void addStrength(int amount) {
        this.player.setSTR(this.player.getSTR() + amount);
    }

    public void addDefense(int amount) {
        this.player.setDEF(this.player.getDEF() + amount);
    }

    public boolean isEmpty() {
        return getStackSize() == 0;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public void setStackSize(int stackSize) {
        if (this.stackSize != this.maxStackSize) {
            this.stackSize = stackSize;
        } else {
            System.out.println("You can't have more of these items.");
        }
    }

    public int getStackSize() {
        return this.stackSize;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public void useItem(Player player) {
    }

    public void destroyItem() {
    }

    public void giveItem() {
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public Node getNode() {
        return this.node;
    }

    public String toString() {
        return this.name;
    }

    public Item getItem() {
        return this;
    }

    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.image);
        hash = 11 * hash + Objects.hashCode(this.node);
        hash = 11 * hash + Objects.hashCode(this.stat);
        hash = 11 * hash + this.stackSize;
        hash = 11 * hash + this.maxStackSize;
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
        Item other = (Item) obj;
        if (this.stackSize != other.stackSize) {
            return false;
        }
        if (this.maxStackSize != other.maxStackSize) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        if (this.stat != other.stat) {
            return false;
        }
        return true;
    }
}
