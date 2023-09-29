package za.co.wethinkcode.toyrobot;

public class ForwardCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(nrSteps)){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }

        // Add the executed command to RobotController
        RobotController controller = RobotController.getInstance();
        controller.addExecutedCommand(this);

        return true;
    }

    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}

