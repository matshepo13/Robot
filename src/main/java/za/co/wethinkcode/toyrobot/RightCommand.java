package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command {
    public RightCommand(String argument) {
        super("right", argument);
    }

    @Override
    public boolean execute(Robot target) {
        // Implement right turn logic
        target.turnRight();
        target.setStatus("Turned right.");
        return true;
    }
}
