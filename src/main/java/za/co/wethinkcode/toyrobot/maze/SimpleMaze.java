package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;

public class SimpleMaze extends AbstractMaze{
    public SimpleMaze() {
        obstacles = List.of(new SquareObstacle(1, 1));
    }
}
