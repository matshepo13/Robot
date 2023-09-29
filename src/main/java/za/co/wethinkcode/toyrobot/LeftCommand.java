package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command {
    public LeftCommand(String argument) {
        super("left", argument);
    }

    @Override
    public boolean execute(Robot target) {
        // Implement left turn logic
        target.turnLeft();
        target.setStatus("Turned left.");
        return true;
    }
}

