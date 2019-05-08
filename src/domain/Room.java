package domain;

import enums.Tiles;
import java.util.Random;

public class Room {

    private final int roomMaxWidth = 20;
    private final int roomMaxHeight = 12;
    private final int roomMinWidth = 12;
    private final int roomMinHeight = 12;

    private int roomSizeX = new Random().nextInt(20) + 12;
    private int roomSizeY = new Random().nextInt(12) + 12;
    private int roomLocationX;
    private int roomLocationY;
    private Boolean startRoom;
    private Tiles[][] generatedRoom;

    public Room(int locationX, int locationY, Boolean startRoom) {
        this.generatedRoom = new Tiles[this.roomSizeY][this.roomSizeX];
        this.roomLocationX = locationX;
        this.roomLocationY = locationY;
        this.startRoom = startRoom;

        makeRoom();
        generateSpawnSpots(this.startRoom);
    }

    public int randomize(int max) {
        return new Random().nextInt(max) + 1;
    }

    public void spawn() {
        int x = randomize(this.roomSizeX - 3);
        int y = randomize(this.roomSizeY - 3);

        if (getRoomTile(x, y) != Tiles.SPAWN) {
            insertRoomTile(x, y, Tiles.SPAWN);
        } else {
            spawn();
        }
    }

    public void generateSpawnSpots(Boolean startRoom) {
        if (!startRoom) {
            int spots = 0;

            if (((this.roomSizeX > 12 ? 1 : 0) & (this.roomSizeY > 4 ? 1 : 0)) != 0) {
                spots = 10;
            } else {
                spots = 6;
            }
            for (int i = 0; i < spots; i++) {
                spawn();
            }
        }
    }

    public int getStartX() {
        return this.roomLocationX;
    }

    public int getEndX() {
        return this.roomLocationX + roomWidth();
    }

    public int getStartY() {
        return this.roomLocationY;
    }

    public int getEndY() {
        return this.roomLocationY + roomHeight();
    }

    public int roomHeight() {
        return this.roomSizeY - 1;
    }

    public int roomWidth() {
        return this.roomSizeX - 1;
    }

    public int getRoomLocationX() {
        return this.roomLocationX;
    }

    public int getRoomLocationY() {
        return this.roomLocationY;
    }

    public Tiles getRoomTile(int x, int y) {
        return this.generatedRoom[y][x];
    }

    public void insertRoomTile(int x, int y, Tiles tile) {
        if (x > roomWidth()) {
            x = roomWidth();
        }
        if (y > roomHeight()) {
            y = roomHeight();
        }
        this.generatedRoom[y][x] = tile;
    }

    public void placeRoom(Tiles[][] map) {
        int dy = 0;
        int dx = 0;
        for (int y = getStartY(); y < getEndY(); y++) {
            for (int x = getStartX(); x < getEndX(); x++) {
                map[y][x] = getRoomTile(dx, dy);
                dx++;
            }
            dy++;
            dx = 0;
        }
    }

    public void makeRoom() {
        for (int y = 0; y < this.roomSizeY; y++) {
            for (int x = 0; x < this.roomSizeX; x++) {
                insertRoomTile(x, y, Tiles.WALL);
            }
        }

        for (int y = 1; y < this.roomSizeY - 2; y++) {
            for (int x = 1; x < this.roomSizeX - 2; x++) {
                insertRoomTile(x, y, Tiles.FLOOR);
            }
        }
    }

    public String toString() {
        return "getStartX: " + getStartX() + " getStartY: " + getStartY() + "\ngetEndX: " + getEndX() + " getEndY:" + getEndY();
    }
}
