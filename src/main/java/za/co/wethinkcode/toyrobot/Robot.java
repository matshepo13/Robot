package za.co.wethinkcode.toyrobot;

import java.util.*;

public class Robot {
    private final Position TOP_LEFT = new Position(-200, 100);
    private final Position BOTTOM_RIGHT = new Position(100, -200);

    private final List<String> VALID = List.of("forward", "back" , "left", "right", "sprint" );

    private List<Command> executedCommands = new ArrayList<>();


    public static final Position CENTRE = new Position(0, 0);

    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
    }

    public String getStatus() {
        return this.status;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean handleCommand(Command command) {
        if (VALID.contains(command.getName())){

            executedCommands.add(command);
        }
        return command.execute(this);
    }


    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;

            // Add the executed command to RobotController
            RobotController controller = RobotController.getInstance();
            controller.addExecutedCommand(new ForwardCommand(String.valueOf(nrSteps)));

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] " + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void turnRight() {
        if (Direction.NORTH.equals(this.currentDirection)) {
            currentDirection = Direction.EAST;
        } else if (Direction.EAST.equals(this.currentDirection)) {
            currentDirection = Direction.SOUTH;
        } else if (Direction.SOUTH.equals(this.currentDirection)) {
            currentDirection = Direction.WEST;
        } else if (Direction.WEST.equals(this.currentDirection)) {
            currentDirection = Direction.NORTH;
        }
    }

    public void turnLeft() {
        if (Direction.NORTH.equals(this.currentDirection)) {
            currentDirection = Direction.WEST;
        } else if (Direction.WEST.equals(this.currentDirection)) {
            currentDirection = Direction.NORTH;
        } else if (Direction.SOUTH.equals(this.currentDirection)) {
            currentDirection = Direction.EAST;
        } else if (Direction.EAST.equals(this.currentDirection)) {
            currentDirection = Direction.SOUTH;
        }
    }

    public List<Command> getExecutedCommands() {
        return this.executedCommands;
    }
}
