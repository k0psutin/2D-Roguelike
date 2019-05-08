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
    }

    public void addItem(Item item) {
        if (item != null) {
            if (!inventory.contains(item)) {
                inventory.add(item);
            } else {
                inventory.forEach(items -> items.setStackSize(items.getStackSize() + 1));
            }
        }
    }

    public void removeItem(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getInventorySize() {
        return inventory.size();
    }

    static {
        inventoryUI = new InventoryUI();
    }
}
