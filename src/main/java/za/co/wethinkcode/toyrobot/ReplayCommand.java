package za.co.wethinkcode.toyrobot;

public class ReplayCommand extends Command {
    private String argument;

    public ReplayCommand(String argument) {
        super("replay", argument);
        this.argument = argument;
    }

    @Override
    public boolean execute(Robot target) {
        for (Command cmd:target.getExecutedCommands()) {
            cmd.execute(target);
            System.out.println(target);
        }
        target.setStatus("replayed "+target.getExecutedCommands().size()+" commands.");
        return true;
    }
}
