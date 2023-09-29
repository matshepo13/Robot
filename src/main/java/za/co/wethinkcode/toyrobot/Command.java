package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name) {
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        String commandName = args[0];
        String argument = args.length > 1 ? args[1] : "";

        switch (commandName) {
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(argument);
            case "back":
                return new BackCommand(argument);
            case "left":
                return new LeftCommand(argument);
            case "right":
                return new RightCommand(argument);
            case "sprint":
                if (args.length > 1 && args[1].matches("\\d+")) {
                    int steps = Integer.parseInt(args[1]);
                    return new SprintCommand(steps);
                } else {
                    throw new IllegalArgumentException("Invalid sprint command format: " + instruction);
                }
//            case "replay":
//                return new ReplayCommand(argument);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

//    @Override
//    public String toString() {
//        return "Command{" +
//                "name='" + name + '\'' +
//                ", argument='" + argument + '\'' +
//                '}';
//    }
}