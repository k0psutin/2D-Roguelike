package generator;

import domain.Enemy;
import domain.NPC;
import enums.Type;
import javafx.scene.Node;
import utils.Coordinates;
import utils.Utils;

public class EnemyGenerator {
    public static void spawnEnemies() {
        if (DungeonGenerator.getNpcList() != null) {
            DungeonGenerator.getNpcList().keySet().forEach(key -> {
                Coordinates cords = new Coordinates(key.getX(), key.getY());
                NPC enemy = (NPC)DungeonGenerator.getNpcList().get((Object)cords);
                enemy.setLocX(key.getX());
                enemy.setLocY(key.getY());
                DungeonGenerator.dungeonMap.add(enemy.getMapNode(), key.getX(), key.getY());
            });
        }
    }

    public static Enemy randomizeEnemy() {
        int enemyType = Utils.randomize((int)4, (int)0);
        Enemy enemy = null;
        switch (enemyType) {
            case 0: {
                enemy = EnemyGenerator.orc();
                break;
            }
            case 1: {
                enemy = EnemyGenerator.skeleton();
                break;
            }
            case 2: {
                enemy = EnemyGenerator.zombie();
                break;
            }
            case 3: {
                enemy = EnemyGenerator.ogre();
            }
        }
        return enemy;
    }

    public static Enemy orc() {
        String name = "Orc";
        Enemy orc = new Enemy(name, Type.ORC);
        double exponent = Utils.exponent();
        orc.setLEVEL(1);
        orc.setHP(Utils.randomize((int)30, (int)30) * orc.getLEVEL());
        orc.setDMG(Utils.randomize((int)4, (int)2) * orc.getLEVEL());
        orc.setGold(Utils.randomize((int)50, (int)15) * orc.getLEVEL());
        orc.setExperience(Utils.randomize((int)75, (int)75) * orc.getLEVEL());
        orc.setMapNode((Node)Type.ORC.getImageView());
        return orc;
    }

    public static Enemy skeleton() {
        String name = "Skeleton";
        Enemy skeleton = new Enemy(name, Type.SKELETON);
        double exponent = Utils.exponent();
        skeleton.setLEVEL(1);
        skeleton.setHP(Utils.randomize((int)15, (int)15) * skeleton.getLEVEL());
        skeleton.setDMG(Utils.randomize((int)4, (int)2) * skeleton.getLEVEL());
        skeleton.setGold(Utils.randomize((int)15, (int)15) * skeleton.getLEVEL());
        skeleton.setExperience(Utils.randomize((int)75, (int)25) * skeleton.getLEVEL());
        skeleton.setMapNode((Node)Type.SKELETON.getImageView());
        return skeleton;
    }

    public static Enemy zombie() {
        String name = "Zombie";
        Enemy zombie = new Enemy(name, Type.ZOMBIE);
        double exponent = Utils.exponent();
        zombie.setLEVEL(1);
        zombie.setHP(Utils.randomize((int)20, (int)20) * zombie.getLEVEL());
        zombie.setDMG(Utils.randomize((int)5, (int)2) * zombie.getLEVEL());
        zombie.setGold(Utils.randomize((int)25, (int)15) * zombie.getLEVEL());
        zombie.setExperience(Utils.randomize((int)75, (int)50) * zombie.getLEVEL());
        zombie.setMapNode((Node)Type.ZOMBIE.getImageView());
        return zombie;
    }

    public static Enemy ogre() {
        String name = "Ogre";
        Enemy ogre = new Enemy(name, Type.OGRE);
        double exponent = Utils.exponent();
        ogre.setLEVEL(1);
        ogre.setHP(Utils.randomize((int)50, (int)50) * ogre.getLEVEL());
        ogre.setDMG(Utils.randomize((int)10, (int)5) * ogre.getLEVEL());
        ogre.setGold(Utils.randomize((int)125, (int)125) * ogre.getLEVEL());
        ogre.setExperience(Utils.randomize((int)150, (int)100) * ogre.getLEVEL());
        ogre.setMapNode((Node)Type.OGRE.getImageView());
        return ogre;
    }
}