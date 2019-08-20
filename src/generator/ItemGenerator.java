package generator;

import domain.Item;
import domain.Potion;
import enums.Stat;
import java.util.*;
import javafx.scene.image.Image;

public class ItemGenerator {

    private static List<Item> potionList;

    public ItemGenerator() {
        this.potionList = new ArrayList<>();
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_red.png"), "Small Potion of Health", Stat.HP, 20, 10));
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_big_red.png"), "Potion of Health", Stat.HP, 50, 10));
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_blue.png"), "Small Potion of Mana", Stat.MANA, 20, 10));
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_big_blue.png"), "Potion of Mana", Stat.MANA, 50, 10));
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_green.png"), "Potion of Poison", Stat.POISON, 25, 5));
        this.potionList.add(new Potion(new Image("file:src/images/items/potions/flask_yellow.png"), "Potion of Stamina", Stat.STA, 1, 5));
        System.out.println(this.potionList.get(0).equals(this.potionList.get(2)));
        System.out.println(this.potionList.get(1).equals(this.potionList.get(3)));
    }

    public static void setPotionList(List<Item> potionList) {
        ItemGenerator.potionList = potionList;
    }

    public static Item returnItem(int index) {
        Item item = ItemGenerator.potionList.get(index);
        System.out.println("Got item: " + item + " with number of " + index);
        return item;
    }

    public static Item returnRandomItem() {
        int randomNumber = utils.Utils.randomize(ItemGenerator.potionList.size(), 1) - 1;
        System.out.println("Random number: " + randomNumber);
        return returnItem(randomNumber);
    }
    
    public static List<Item> randomDrop() {
        int numberOFDrops = utils.Utils.randomize(1, 1);
        List<Item> dropList = new ArrayList<>();
        
        if(numberOFDrops != 0) {
            for(int drop = 1; drop <= numberOFDrops; drop++) {
                dropList.add(returnRandomItem());
            }
        }
        
        return dropList;
    }
}
