package za.co.wethinkcode.toyrobot;

// Define the InterfaceType enum
public enum InterfaceType {
    TEXT,
    TURTLE
}
//package za.co.wethinkcode.toyrobot.world;
//
//import za.co.wethinkcode.toyrobot.Direction;
//import za.co.wethinkcode.toyrobot.ForwardCommand;
//import za.co.wethinkcode.toyrobot.Position;
//import za.co.wethinkcode.toyrobot.RobotController;
//import za.co.wethinkcode.toyrobot.maze.Maze;
//import ch.aplu.turtle.Turtle;
//
//public class TurtleWorld extends AbstractWorld {
//    private Turtle turtle;
//
//
//
//    public TurtleWorld(Maze maze) {
//        super(maze);
//        this.turtle = new Turtle(); // Create a Turtle object for graphical output
//        this.turtle.speed(1); // Set turtle speed (adjust as needed)
//    }
//
//    @Override
//    public void showObstacles() {
//        // Implement displaying obstacles in the Turtle world if needed
//        // You may use turtle graphics to draw obstacles on the screen
//    }
//
//    @Override
//    //turtle needs to be inside
//    public UpdateResponse updatePosition(int nrSteps) {
//        int newX = this.position.getX();
//        int newY = this.position.getY();
//
//        if (Direction.UP.equals(this.getCurrentDirection())) {
//            newY = newY + nrSteps;
//        } else if (Direction.DOWN.equals(this.getCurrentDirection())) {
//            newY = newY - nrSteps;
//        } else if (Direction.RIGHT.equals(this.getCurrentDirection())) {
//            newX = newX + nrSteps;
//        } else if (Direction.LEFT.equals(this.getCurrentDirection())) {
//            newX = newX - nrSteps;
//        }
//
//        Position newPosition = new Position(newX, newY);
//        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
//            this.position = newPosition;
//            RobotController controller = RobotController.getInstance();
//            controller.addExecutedCommand(new ForwardCommand(String.valueOf(nrSteps)));
//
//            return UpdateResponse.SUCCESS;
//        }
//        return UpdateResponse.FAILED_OUTSIDE_WORLD;
//    }
//
//    @Override
//    public void updateDirection(boolean turnRight) {
//        IWorld.Direction currentDirection = getCurrentDirection();
//        if (turnRight) {
//            switch (currentDirection) {
//                case UP:
//                    setCurrentDirection(Direction.RIGHT);
//                    break;
//                case RIGHT:
//                    setCurrentDirection(Direction.DOWN);
//                    break;
//                case DOWN:
//                    setCurrentDirection(Direction.LEFT);
//                    break;
//                case LEFT:
//                    setCurrentDirection(Direction.UP);
//                    break;
//            }
//        } else {
//            switch (currentDirection) {
//                case UP:
//                    setCurrentDirection(Direction.LEFT);
//                    break;
//                case RIGHT:
//                    setCurrentDirection(Direction.UP);
//                    break;
//                case DOWN:
//                    setCurrentDirection(Direction.RIGHT);
//                    break;
//                case LEFT:
//                    setCurrentDirection(Direction.DOWN);
//                    break;
//            }
//        }
//    }
//
//    // Implement other methods of the IWorld interface as needed for turtle-based output
//}
