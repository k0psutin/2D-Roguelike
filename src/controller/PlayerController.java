package controller;

import domain.Player;
import enums.Direction;
import generator.DungeonGenerator;
import javafx.scene.input.KeyCode;

public class PlayerController {

    public static Player player;

    public static void setUpPlayer(Player player) {
        PlayerController.player = player;
    }

    public static void movePlayer(KeyCode keyCode) {
        Direction dir = null;
        if (player.isAlive()) {
            switch (keyCode) {
                case W:
                    dir = Direction.UP;
                    break;
                case S:
                    dir = Direction.DOWN;
                    break;
                case D:
                    dir = Direction.RIGHT;
                    break;
                case A:
                    dir = Direction.LEFT;
                    break;
                case I:
                    domain.Inventory.inventoryUI.show();
            }
        }
        if (dir != null) {
            move(dir);
        }
    }

    public static void movePlayerMapPosY(int y, double amount) {
        player.setLocY(y);
        player.setMapLocY(amount);
        player.getMapNode().setTranslateY(player.getMapLocY());
    }

    public static void movePlayerMapPosX(int x, double amount) {
        player.setLocX(x);
        player.setMapLocX(amount);
        player.getMapNode().setTranslateX(player.getMapLocX());
    }

    public static boolean moveSceneY(Direction dir) {
        return ((player.getMapLocY() >= 208.0) & (player.getMapLocY() <= 1600.0) & (dir == Direction.DOWN) | (player.getMapLocY() >= 0.0) & (player.getMapLocY() <= 1600.0) & (dir == Direction.UP));
    }

    public static boolean moveSceneX(Direction dir) {
        return (player.getMapLocX() >= 368.0) & (player.getMapLocX() < 2400.0) & ((dir == Direction.LEFT) | (dir == Direction.RIGHT));
    }

    public static void cameraFollow(Direction dir) {
        if (moveSceneY(dir)) {
            DungeonGenerator.moveMap(dir);
        } else if (moveSceneX(dir)) {
            DungeonGenerator.moveMap(dir);
        }
    }

    public static void movePlayerOnMap(Direction dir, int x, int y, double tileSize) {
        if (((dir == Direction.UP ? 1 : 0) | (dir == Direction.DOWN ? 1 : 0)) != 0) {
            movePlayerMapPosY(y, tileSize);
        } else {
            movePlayerMapPosX(x, tileSize);
        }
        cameraFollow(dir);
    }

    public static void move(Direction dir) {
        if (player.isAlive()) {
            int x = player.getLocX();
            int y = player.getLocY();
            double tileSize = utils.Settings.TILESIZE;

            switch (dir) {
                case UP:
                    y--;
                    tileSize = -tileSize;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    tileSize = -tileSize;
                    break;
                case RIGHT:
                    x++;
            }
            moveTo(dir, x, y, tileSize);

            System.out.println("");
            System.out.println(player);
        }
    }

    public static void moveTo(Direction dir, int x, int y, double tileSize) {
        if (player.isObstacle(x, y)) {
            if (player.isNPC(x, y)) {
                movePlayerOnMap(dir, x, y, tileSize);
            } else {
                player.npcContact(x, y);
            }
        } else {
            System.out.println("\nOoof! That's a wall..");
        }
    }
}
