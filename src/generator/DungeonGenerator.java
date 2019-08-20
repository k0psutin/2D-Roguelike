package generator;

import domain.Corridor;
import domain.NPC;
import domain.Player;
import domain.Room;
import enums.Direction;
import enums.Tiles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import utils.Coordinates;
import utils.Settings;

public class DungeonGenerator extends GridPane {

    private static int dungeonSizeX = 180;
    private static int dungeonSizeY = 130;

    private Tiles[][] dungeon = new Tiles[dungeonSizeY][dungeonSizeX];

    public static Map<Coordinates, NPC> npcList = new HashMap();
    private List<Room> rooms;
    private List<Corridor> corridors;
    public static Map<Coordinates, Tiles> tileMap;
    public static GridPane dungeonMap;

    public DungeonGenerator() {
        this.rooms = new ArrayList();
        this.corridors = new ArrayList();
        
        tileMap = new java.util.LinkedHashMap();

        generateDungeon();
        
        

        dungeonMap = createTileMap();
        dungeonMap.setBackground(new javafx.scene.layout.Background(new BackgroundFill[]{new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)}));
        dungeonMap.setPadding(new javafx.geometry.Insets(0.0D, 0.0D, 0.0D, 0.0D));
        dungeonMap.setBorder(javafx.scene.layout.Border.EMPTY);
        dungeonMap.setHgap(0.0D);
        dungeonMap.setVgap(0.0D);
        dungeonMap.setGridLinesVisible(false);

        Player player = new Player("Poopy");
        EnemyGenerator.spawnEnemies();
        addPlayer(player);
    }

    public static void removeNode(Node node) {
        dungeonMap.getChildren().remove(node);
    }

    public void addPlayer(Player player) {
        controller.PlayerController.player = player;
        ImageView image = controller.PlayerController.player.getType().getImageView();
        player.setMapLocX(image.getTranslateX());
        player.setMapLocY(image.getTranslateY());
        player.setMapNode(image);
        dungeonMap.add(image, player.getLocX(), player.getLocY());
    }

    public Map<Coordinates, Tiles> getTileMap() {
        return tileMap;
    }

    public GridPane createTileMap() {
        GridPane gridPane = new GridPane();
        for (int y = 0; y < dungeonSizeY; y++) {
            for (int x = 0; x < dungeonSizeX; x++) {
                Tiles tile = getDungeon(x, y);
                if (tile == null) {
                    tile = Tiles.EMPTY;
                }
                gridPane.add(Tiles.getTileImage(tile), x, y);
                tileMap.put(new Coordinates(x, y), tile);

                if (tile == Tiles.SPAWN) {
                    domain.Enemy enemy = EnemyGenerator.randomizeEnemy();
                    if (enemy != null) {
                        npcList.put(new Coordinates(x, y), enemy);
                    }
                }
            }
        }
        return gridPane;
    }

    public GridPane getDungeonMap() {
        return dungeonMap;
    }

    public static double getMapPositionX() {
        return dungeonMap.getTranslateX();
    }

    public static double getMapPositionY() {
        return dungeonMap.getTranslateY();
    }

    public static void moveMap(Direction dir) {
        switch (dir) {
            case UP:
                setMapPositionY(Settings.TILESIZE);
                break;
            case DOWN:
                setMapPositionY(-Settings.TILESIZE);
                break;
            case LEFT:
                setMapPositionX(Settings.TILESIZE);
                break;
            case RIGHT:
                setMapPositionX(-Settings.TILESIZE);
        }
    }

    public static void setMapPositionX(double x) {
        if ((getMapPositionX() + x <= 0.0D) & (getMapPositionX() + x >= -2080.0)) {
            dungeonMap.setTranslateX(getMapPositionX() + x);
        }
    }

    public static void setMapPositionY(double y) {
        if ((getMapPositionY() + y <= 0.0) & (getMapPositionY() + y >= -1472.0)) {
            dungeonMap.setTranslateY(getMapPositionY() + y);
        }
    }

    public void generateDungeon() {
        Boolean startRoom = Boolean.valueOf(true);

        for (int x = 0; x < dungeonSizeX - 35; x += 35) {
            for (int y = 0; y < dungeonSizeY - 25; y += 25) {
                if (this.rooms.size() > 0) {
                    startRoom = false;
                }
                if ((x < dungeonSizeX - 35) && (y < dungeonSizeY - 25)) {
                    this.rooms.add(new Room(x, y, startRoom));
                }
            }
        }
        for (int i = 0; i < this.rooms.size(); i++) {
            this.corridors.add(new Corridor());
        }

        for (int i = 1; i < this.corridors.size(); i++) {
            if (i % 5 != 0) {
                ((Corridor) this.corridors.get(i)).connectRooms((Room) this.rooms.get(i - 1), (Room) this.rooms.get(i), this.dungeon);
            }
        }

        for (int i = 1; i < this.corridors.size() - 4; i++) {
            ((Corridor) this.corridors.get(i)).connectRooms((Room) this.rooms.get(i + 4), (Room) this.rooms.get(i - 1), this.dungeon);
        }

        for (int b = 0; b < this.rooms.size(); b++) {
            ((Room) this.rooms.get(b)).placeRoom(this.dungeon);
        }
    }

    public Tiles getDungeon(int x, int y) {
        return this.dungeon[y][x];
    }

    public Tiles getDungeonTile(int x, int y) {
        return getDungeon(x, y);
    }

    public int getDungeonSizeX() {
        return dungeonSizeX - 1;
    }

    public int getDungeonSizeY() {
        return dungeonSizeY - 1;
    }

    public boolean edgeCheck(int x, int y) {
        if ((x < 0) | (y < 0)) {
            return false;
        }
        if ((x > this.dungeon.length - 1) | (y > this.dungeon.length - 1)) {
            return false;
        }
        return true;
    }

    public void setNpcList(Map<Coordinates, NPC> npcList) {
        this.npcList = npcList;
    }

    public static Map<Coordinates, NPC> getNpcList() {
        return npcList;
    }
}
