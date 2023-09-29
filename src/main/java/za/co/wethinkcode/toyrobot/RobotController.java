package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.List;

public class RobotController {
    private static RobotController instance = new RobotController();
    private List<Command> executedCommands = new ArrayList<>();

    private RobotController() {
    }

    public static RobotController getInstance() {
        return instance;
    }

    public void addExecutedCommand(Command command) {
        executedCommands.add(command);
    }

    public List<Command> getExecutedCommands() {
        return executedCommands;
    }
}
