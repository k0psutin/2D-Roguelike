package domain;

import domain.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import ui.InventoryUI;

public class Inventory {

    public static List<Item> inventory;
    public static InventoryUI inventoryUI;

    public Inventory() {
        inventory = new ArrayList<Item>();
        inventoryUI = new InventoryUI();
    }

    public void addItem(Item item) {
        System.out.println("Item: " + item + " being evaluated.");
        if (item != null) {
            if (!this.inventory.contains(item)) {
                System.out.println("Added item: " + item);
                this.inventory.add(item);
            } else {
                this.inventory.forEach(items -> {
                    if (items.equals(item)) {
                        System.out.println("Item: " + item + " already in inventory, adding stack.");
                        items.growStackSize();
                    }
                });
            }
        }
    }

    public void removeItem(Item item) {
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
        }
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public int getInventorySize() {
        return inventory.size();
    }

}
