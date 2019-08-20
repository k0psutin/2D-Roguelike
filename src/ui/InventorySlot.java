package ui;

import controller.PlayerController;
import domain.Inventory;
import domain.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InventorySlot extends HBox {

    private Item item;
    private final Font font = new Font("Arial", 20.0);

    public InventorySlot(Item item) {
        this.item = item;
        VBox icon = new VBox();
        VBox desc = new VBox();
        VBox size = new VBox();
        this.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        this.setAlignment(Pos.BASELINE_CENTER);
        this.setSpacing(10.0);
        ImageView itemImage = new ImageView(item.getImage());
        itemImage.setFitWidth(20.0);
        itemImage.setFitHeight(20.0);
        Label description = new Label(item.toString());
        Label stackSize = new Label(String.valueOf(item.getStackSize()));
        description.setFont(this.font);
        stackSize.setFont(this.font);
        icon.getChildren().add(itemImage);
        desc.getChildren().add(description);
        if (item.getStackSize() > 1) {
            size.getChildren().add(stackSize);
        }
        this.getChildren().addAll(new Node[]{icon, desc, size});
        this.setOnMouseClicked(use -> {
            item.useItem(PlayerController.player);
            Inventory.inventoryUI.updateInventory();
            PlayerController.updatePlayerStats();
        });
    }

    public InventorySlot() {
        Label empty = new Label("No items in inventory!");
        empty.setFont(this.font);
        this.getChildren().add(empty);
    }
}
