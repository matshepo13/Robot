package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.List;

public class AbstractMaze implements Maze {

    //    CREATE LIST OF OBSTACLES
    List<Obstacle> obstacles;

    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        int ax = a.getX();
        int ay = a.getY();
        int bx = b.getX();
        int by = b.getY();

        // Determine the direction of the path
        int dx = Math.abs(bx - ax);
        int dy = Math.abs(by - ay);

        // Check if the path is vertical, horizontal, or diagonal
        if (dx == 0 && dy == 0) {
            // The positions are the same; no blocking
            return false;
        } else if (dx == 0) {
            // Vertical path
            int minY = Math.min(ay, by);
            int maxY = Math.max(ay, by);

//            // Check if the path intersects the obstacle's vertical boundary
//            return minY <= bottomLeftY + size && maxY >= bottomLeftY;
//        } else if (dy == 0) {
//            // Horizontal path
//            int minX = Math.min(ax, bx);
//            int maxX = Math.max(ax, bx);
//
//            // Check if the path intersects the obstacle's horizontal boundary
//            return minX <= bottomLeftX + size && maxX >= bottomLeftX;
//        } else {
//            // Diagonal path
//            int minX = Math.min(ax, bx);
//            int minY = Math.min(ay, by);
//            int maxX = Math.max(ax, bx);
//            int maxY = Math.max(ay, by);
//
//            // Check if the path intersects both horizontal and vertical boundaries of the obstacle
//            return (minX <= bottomLeftX + size && maxX >= bottomLeftX) &&
//                    (minY <= bottomLeftY + size && maxY >= bottomLeftY);
//        }
        }
        return false;
    }
}
