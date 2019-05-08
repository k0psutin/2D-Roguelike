package enums;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public enum Tiles
{
  EMPTY(" ", 0, new Image("file:src/images/dungeon_tiles/empty.png")), 
  FLOOR(".", 1, new Image("file:src/images/dungeon_tiles/floor_1.png")), 
  WALL("#", 2, new Image("file:src/images/dungeon_tiles/wall_mid.png")), 
  DOOR("+", 3, new Image("file:src/images/dungeon_tiles/floor_1.png")), 
  SPAWN("x", 9, new Image("file:src/images/dungeon_tiles/floor_1.png"));
  

  private int tileID;
  private String tileType;
  private Image image;
  private static double imageScaleY = 1.0D;
  private static double imageScaleX = 1.0D;
  
  private Tiles(String tileType, int tileID, Image image) {
    this.tileType = tileType;
    this.tileID = tileID;
    this.image = image;
  }
  
  private int getTileID() {
    return this.tileID;
  }
  
  private String getTileType() {
    return this.tileType;
  }
  
  public Image getImage() {
    return this.image;
  }
  
  public static Tiles getTileType(Tiles tile) {
    return tile;
  }
  
  public static ImageView getTileImage(Tiles tile) {
    Image image = tile.getImage();
    
    if (image == null) image = new Image("file:src/images/dungeon_tiles/empty.png");
    ImageView imageView = new ImageView(image);
    
    imageView.setScaleX(imageScaleY);
    imageView.setScaleY(imageScaleY);
    
    return new ImageView(image);
  }
}
