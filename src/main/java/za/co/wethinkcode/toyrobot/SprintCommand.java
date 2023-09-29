package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command {
    private int steps;

    public SprintCommand(int steps) {
        super("sprint");
        this.steps = steps;
    }

    @Override
    public boolean execute(Robot target) {

        for (int i = steps; i > 0; i--) {
            ForwardCommand fdCommand = new ForwardCommand(String.valueOf(i));
            fdCommand.execute(target);
            if (!(i == 1)) System.out.println(target);
        }


        return true;
    }
}
