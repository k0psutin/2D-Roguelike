package ui;

import domain.Inventory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.InventorySlot;

public class InventoryUI {

    private int maxSlots = 20;
    private VBox inventoryPanel;
    private Scene inventoryView;
    private Stage inventory = new Stage();
    private Font font;
    private boolean active = false;

    public InventoryUI() {
        this.inventory.setTitle("Inventory");
        this.inventoryPanel = new VBox();
        this.inventoryView = new Scene((Parent) this.inventoryPanel);
        this.inventoryPanel.autosize();
        this.font = new Font("Arial", 20.0);
        this.inventory.initStyle(StageStyle.UNDECORATED);
        this.inventory.setFullScreen(false);
        this.inventory.setWidth(400.0);
        this.inventory.setScene(this.inventoryView);
        this.inventoryView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.I) {
                this.show();
            }
        });
    }

    public VBox getInventoryPanel() {
        return this.inventoryPanel;
    }

    public Scene getInventoryView() {
        return this.inventoryView;
    }

    public Stage getStage() {
        return this.inventory;
    }

    public void updateInventory() {
        if (Inventory.inventory.isEmpty()) {
            this.inventoryPanel.getChildren().clear();
            this.inventoryPanel.getChildren().add(new InventorySlot());
        } else {
            this.inventoryPanel.getChildren().clear();
            Inventory.inventory.forEach(item -> this.inventoryPanel.getChildren().add(new InventorySlot(item)));
        }
    }

    public void show() {
        if (this.active) {
            this.getStage().close();
            this.active = !this.active;
        } else {
            this.updateInventory();
            this.getStage().show();
            this.active = !this.active;
        }
    }
}
