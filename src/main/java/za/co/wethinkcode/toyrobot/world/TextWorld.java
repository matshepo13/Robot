package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.RobotController;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public class TextWorld extends AbstractWorld {
    public TextWorld(Maze maze) {
        super(maze);
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.getCurrentDirection())) {
            newY = newY + nrSteps;
        } else if (Direction.DOWN.equals(this.getCurrentDirection())) {
            newY = newY - nrSteps;
        } else if (Direction.RIGHT.equals(this.getCurrentDirection())) {
            newX = newX + nrSteps;
        } else if (Direction.LEFT.equals(this.getCurrentDirection())) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            if (isNewPositionAllowed(newPosition)) {
                this.position = newPosition;
                RobotController controller = RobotController.getInstance();
                controller.addExecutedCommand(new ForwardCommand(String.valueOf(nrSteps)));

                System.out.println("Moved forward by " + nrSteps + " steps.");
                return UpdateResponse.SUCCESS;
            } else {
                System.out.println("Sorry, there is an obstacle in the way.");
                return UpdateResponse.FAILED_OBSTACLE;
            }
        }
        System.out.println("Sorry, the robot cannot move outside the world boundaries.");
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        IWorld.Direction currentDirection = getCurrentDirection();
        if (turnRight) {
            switch (currentDirection) {
                case UP:
                    setCurrentDirection(Direction.RIGHT);
                    break;
                case RIGHT:
                    setCurrentDirection(Direction.DOWN);
                    break;
                case DOWN:
                    setCurrentDirection(Direction.LEFT);
                    break;
                case LEFT:
                    setCurrentDirection(Direction.UP);
                    break;
            }
        } else {
            switch (currentDirection) {
                case UP:
                    setCurrentDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    setCurrentDirection(Direction.UP);
                    break;
                case DOWN:
                    setCurrentDirection(Direction.RIGHT);
                    break;
                case LEFT:
                    setCurrentDirection(Direction.DOWN);
                    break;
            }
        }

        System.out.println("Turned " + (turnRight ? "right" : "left") + ".");
    }

    @Override
    public void showObstacles() {
        List<Obstacle> obstacles = getObstacles();
        if (obstacles.isEmpty()) {
            System.out.println("There are no obstacles in this world.");
        } else {
            System.out.println("There are some obstacles:");
            for (Obstacle obstacle : obstacles) {
                System.out.println("- At position " + obstacle.getBottomLeftX() + "," + obstacle.getBottomLeftY() +
                        " (to " + (obstacle.getBottomLeftX() + obstacle.getSize() - 1) + "," +
                        (obstacle.getBottomLeftY() + obstacle.getSize() - 1) + ")");
            }
        }
    }

    // Override the isNewPositionAllowed method to check for obstacles
    @Override
    public boolean isNewPositionAllowed(Position position) {
        if (!position.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            return false; // Position is outside the world bounds
        }

        for (Obstacle obstacle : myObstacles) {
            if (obstacle.blocksPosition(position)) {
                return false; // Position is blocked by an obstacle
            }
        }

        return true; // Position is allowed
    }
}
