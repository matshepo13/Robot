package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.maze.RandomMaze;
import za.co.wethinkcode.toyrobot.world.*;

import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");

        IWorld world;

        if (args.length > 0 && args[0].equalsIgnoreCase("turtle")) {
            world = new TurtleWorld(new EmptyMaze());
        } else {
            world = new TextWorld((Maze) new RandomMaze());
            System.out.println("Loaded RandomMaze.");
            ((TextWorld) world).showObstacles();
        }

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
                if (world instanceof TextWorld) {
                    System.out.println(robot);
                }
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
                System.out.println(robot.toString());
            }
        } while (shouldContinue);

        scanner.close();
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
