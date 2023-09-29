package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.RobotController;
import za.co.wethinkcode.toyrobot.maze.Maze;


import java.util.ArrayList;
import java.util.List;

public class AbstractWorld implements IWorld {
    public final Position TOP_LEFT = new Position(-100, 200);
    public final Position BOTTOM_RIGHT = new Position(100, -200);
    private static final Position CENTRE = new Position(0,0);
    private Direction currentDirection;
    Position position;
    public List<Obstacle> myObstacles = new ArrayList<>();

    public AbstractWorld(Maze maze) {
        this(0, 0);
        generateRandomObstacles();
    }

    // Add a method to generate random obstacles
    private void generateRandomObstacles() {
        for (int i = 0; i < 3; i++) { // Adjust the number of obstacles as needed
            int x = (int) (Math.random() * (TOP_LEFT.getX() + 1));
            int y = (int) (Math.random() * (TOP_LEFT.getY() + 1));
            myObstacles.add(new SquareObstacle(x, y)); // 5x5 size for each obstacle

        }
    }

    public AbstractWorld(int i, int i1) {
        this.currentDirection = Direction.UP;
        this.position = CENTRE;
    }
    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            RobotController controller = RobotController.getInstance();
            controller.addExecutedCommand(new ForwardCommand(String.valueOf(nrSteps)));

            return UpdateResponse.SUCCESS;
        }
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
    }


    public void setCurrentDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }





    @Override
    public Position getPosition() {
        return this.position;
    }


    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

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




    @Override
    public boolean isAtEdge() {
//        int x = position.getX();
//        int y = position.getY();
//
//        boolean atTopEdge = y == TOP_LEFT.getY();
//        boolean atBottomEdge = y == BOTTOM_RIGHT.getY();
//        boolean atLeftEdge = x == TOP_LEFT.getX();
//        boolean atRightEdge = x == BOTTOM_RIGHT.getX();
//
//        return atTopEdge || atBottomEdge || atLeftEdge || atRightEdge;
        boolean withinTop = this.position.getY() == TOP_LEFT.getY();
        boolean withinBottom = this.position.getY() == BOTTOM_RIGHT.getY();
        boolean withinLeft = this.position.getX()== TOP_LEFT.getX();
        boolean withinRight = this.position.getX() == BOTTOM_RIGHT.getX();
        return withinTop || withinBottom && withinLeft || withinRight;
    }



    @Override
    public void reset() {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.myObstacles.clear();

    }

    @Override
    public List<Obstacle> getObstacles() {
        return myObstacles;
    }

    @Override
    public void showObstacles() {

    }


    
}
