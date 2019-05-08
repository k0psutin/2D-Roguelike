package enums;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Type {
    PLAYER(new Image("file:src/images/npc/knight_f_idle_anim_f1.png")),
    ORC(new Image("file:src/images/npc/orc_warrior_idle_anim_f0.png")),
    SKELETON(new Image("file:src/images/npc/skelet_idle_anim_f0.png")),
    ZOMBIE(new Image("file:src/images/npc/zombie_idle_anim_f0.png")),
    OGRE(new Image("file:src/images/npc/ogre_idle_anim_f0.png"));

    private static double imageScaleY = 1.0D;
    private static double imageScaleX = 1.0D;
    private Image image;

    private Type(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public ImageView getImageView() {
        Image image = getImage();
        ImageView newImage = new ImageView(image);
        newImage.setFitHeight(16.0D);
        newImage.setFitWidth(10.0D);
        newImage.setScaleX(imageScaleX);
        newImage.setScaleY(imageScaleY);
        return newImage;
    }

    public static Type getORC() {
        return ORC;
    }

    public static Type getPLAYER() {
        return PLAYER;
    }

    public static Type getSKELETON() {
        return SKELETON;
    }

    public static Type getZOMBIE() {
        return ZOMBIE;
    }

    public static Type getOGRE() {
        return OGRE;
    }
}
