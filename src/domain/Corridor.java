package domain;

import enums.Tiles;

public class Corridor {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private boolean linked = false;

    public void buildCorridor(Tiles[][] map, int startX, int startY, enums.Direction direction) {
        switch (direction) {
            case UP:
                map[(startY - 1)][(startX - 1)] = Tiles.WALL;
                map[(startY - 1)][startX] = Tiles.WALL;
                map[(startY - 1)][(startX + 1)] = Tiles.WALL;
                map[startY][(startX - 1)] = Tiles.WALL;
                map[startY][startX] = Tiles.FLOOR;
                map[startY][(startX + 1)] = Tiles.WALL;
                break;

            case DOWN:
                map[startY][(startX - 1)] = Tiles.WALL;
                map[startY][startX] = Tiles.FLOOR;
                map[startY][(startX + 1)] = Tiles.WALL;
                map[(startY + 1)][(startX - 1)] = Tiles.WALL;
                map[(startY + 1)][startX] = Tiles.WALL;
                map[(startY + 1)][(startX + 1)] = Tiles.WALL;
                break;

            case LEFT:
                if ((startY > 0) & (startX > 0)) {
                    map[(startY - 1)][(startX - 1)] = Tiles.WALL;
                    map[(startY - 1)][startX] = Tiles.WALL;
                    map[startY][(startX - 1)] = Tiles.WALL;
                    map[startY][startX] = Tiles.FLOOR;
                    map[(startY + 1)][(startX - 1)] = Tiles.WALL;
                    map[(startY + 1)][startX] = Tiles.WALL;
                }

                break;
            case RIGHT:
                if ((startY > 0) & (startX > 0)) {
                    map[(startY - 1)][startX] = Tiles.WALL;
                    map[(startY - 1)][(startX + 1)] = Tiles.WALL;
                    map[startY][startX] = Tiles.FLOOR;
                    map[startY][(startX + 1)] = Tiles.WALL;
                    map[(startY + 1)][startX] = Tiles.WALL;
                    map[(startY + 1)][(startX + 1)] = Tiles.WALL;
                }
                break;
        }

    }

    public void burrow(Tiles[][] map, int startX, int startY, int endX, int endY) {
        int middle = (startY + endY) / 2;

        while (startY != middle) {
            if (startY > endY) {
                startY--;
                buildCorridor(map, startX, startY, enums.Direction.UP);
            } else if (startY < endY) {
                startY++;
                buildCorridor(map, startX, startY, enums.Direction.DOWN);
            }
        }

        while (startX != endX) {
            if (startX > endX) {
                startX--;
                buildCorridor(map, startX, startY, enums.Direction.LEFT);
            } else if (startX < endX) {
                startX++;
                buildCorridor(map, startX, startY, enums.Direction.RIGHT);
            }
        }

        while (startY != endY) {
            if (startY > endY) {
                buildCorridor(map, startX, startY, enums.Direction.UP);
                startY--;
            } else if (startY < endY) {
                startY++;
            }
            buildCorridor(map, startX, startY, enums.Direction.DOWN);
        }
    }

    public boolean checkArea(Tiles[][] map, int x, int y) {
        return map[y][x].ordinal() > 0;
    }

    public void connectRooms(Room first, Room second, Tiles[][] map) {
        int startX = first.roomWidth() / 2;
        int startY = first.roomHeight() / 2;
        int endY = second.roomHeight() / 2;
        int endX = second.roomWidth() / 2;

        boolean roomAtRight = false;
        boolean roomAtLeft = false;

        for (int x = first.getEndX(); x <= second.getRoomLocationX(); x++) {
            int y = first.getEndY();
            if (x == second.getRoomLocationX()) {
                roomAtRight = true;
            }
        }

        for (int x = first.getRoomLocationX(); x >= second.getEndX(); x--) {
            int y = startX;
            if (x == second.getEndX() & !roomAtRight)  {
                roomAtLeft = true;
            }
        }

        setStartX(first.getRoomLocationX() + startX);
        setStartY(first.getRoomLocationY() + first.roomHeight() - 1);

        setEndX(endX + second.getRoomLocationX());
        setEndY(second.getRoomLocationY());

        int lenghtY = Math.abs(getStartY() - getEndY()) - 1;
        int lenghtX = Math.abs(getStartX() - getEndX()) - 1;

        if (roomAtLeft) {
            first.insertRoomTile(0, endY, Tiles.FLOOR);
            burrow(map, getEndX(), endY + getEndY(), getStartX(), endY + getEndY());
            second.insertRoomTile(second.roomWidth() - 1, endY, Tiles.FLOOR);
        } else if (roomAtRight) {
            first.insertRoomTile(first.getEndX(), endY, Tiles.FLOOR);
            second.insertRoomTile(second.getEndX() + 1, endY, Tiles.FLOOR);
            burrow(map, getStartX(), getStartY(), getEndX(), getEndY());
        } else {
            first.insertRoomTile(startX, first.roomHeight() - 1, Tiles.FLOOR);
            second.insertRoomTile(endX, 0, Tiles.FLOOR);
            burrow(map, getStartX(), getStartY(), getEndX(), getEndY());
        }
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }
}
