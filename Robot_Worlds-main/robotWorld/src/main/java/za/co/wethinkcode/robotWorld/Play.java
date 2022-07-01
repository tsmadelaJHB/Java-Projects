package za.co.wethinkcode.robotWorld;

import za.co.wethinkcode.robotWorld.commands.Command;

import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");
        System.out.print("Hello Kiddo!"  + "\n");
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(3);
        robot = new Robot(name, position, 5, gun);

        Robot dummy = new Robot(name, new Position(2, 2), 5, gun);
        world.addRobot(dummy);

        Command command;
        boolean shouldContinue = true;
        do {
            command = null;
            String instruction = robot.getName() + " " + getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = command.execute(world);
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, I did not understand '" + instruction + "'.");
            }
            try {
                System.out.print(world.getRobot(robot.getName()).getResponse().toString() + "\n");
            }catch (NullPointerException e){
                System.out.print("bye");
            }
        } while (shouldContinue);

    }

    private static String getInput(String prompt) {
        System.out.print(prompt + "\n");
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.print(prompt  + "\n");
            input = scanner.nextLine();
        }
        return input;
    }
}
