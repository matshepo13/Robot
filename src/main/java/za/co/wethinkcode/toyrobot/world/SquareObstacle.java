package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {
    private final int bottomLeftX;
    private final int bottomLeftY;
    private final int size;

    public SquareObstacle(int bottomLeftX, int bottomLeftY) {
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.size = 5;
    }

    @Override
    public int getBottomLeftX() {
        return bottomLeftX;
    }

    @Override
    public int getBottomLeftY() {
        return bottomLeftY;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean blocksPosition(Position position) {
        int x = position.getX();
        int y = position.getY();

        // Check if the position is within the obstacle's bounds
        return x >= bottomLeftX && x < bottomLeftX + size && y >= bottomLeftY && y < bottomLeftY + size;
    }



//    translate
    @Override
    public boolean blocksPath(Position a, Position b) {
        if (a.getY() == b.getY()) {
            int minX = Math.min(a.getX(), b.getX());
            int maxX = Math.max(a.getX(), b.getX());

            for (int xRange = minX; xRange <= maxX; xRange++) {
                Position position = new Position(xRange, a.getY());
                if (blocksPosition(position)) {
                    return true;
                }
            }
        }

        if (a.getX() == b.getX()) {
            int minY = Math.min(a.getY(), b.getY());
            int maxY = Math.max(a.getY(), b.getY());

            for (int yRange = minY; yRange <= maxY; yRange++) {
                Position position = new Position(a.getX(), yRange);
                if (blocksPosition(position)) {
                    return true;
                }
            }
        }

        return false;
    }
}
