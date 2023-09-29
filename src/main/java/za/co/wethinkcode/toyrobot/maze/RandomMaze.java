package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze extends AbstractMaze {
    public RandomMaze() {
        super();
    }

    private static List<Obstacle> generateRandomObstacles(int numObstacles) {
        Random random = new Random();
        List<Obstacle> obstacles = new ArrayList<>();

        for (int i = 0; i < numObstacles; i++) {
            int x = random.nextInt(201) - 100; // Random x between -100 and 100
            int y = random.nextInt(201) - 100; // Random y between -100 and 100
            obstacles.add(new SquareObstacle(x, y));
        }

        return obstacles;
    }
}
