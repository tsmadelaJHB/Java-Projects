package za.co.wethinkcode.client.UserInterface;

import za.co.wethinkcode.client.Display;
import za.co.wethinkcode.client.DynamicConnection;
import za.co.wethinkcode.client.GameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameNavigation {
    public static String command;
    public static String maze;
    public static java.util.List<String> temp = new ArrayList<>();


    public static class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();

            switch (userInterface.whatsNext) {
                case "robotName":
                    GameRunner.RobotName();
                    if ("inputName".equals(yourChoice)) {
//                        userInterface.chooseMaze();
                        userInterface.PlayerTypes();
                    }
                    break;
                case "chooseMaze":
                    switch (yourChoice) {
                        case "option1":
                            GameRunner.connect2World("EmptyMaze 127.0.0.1", "y");
                            temp.add("127.0.0.1");
                            maze = "EmptyMaze";
                            userInterface.launchRobot();
                            break;
                        case "option2":
                        case "option3":
                            GameRunner.connect2World("RandomMaze 127.0.0.1", "y");
                            maze = "RandomMaze";
                            temp.add("127.0.0.1");
                            userInterface.launchRobot();
                            break;
                        case "option4":

                            if (GameRunner.getWorlds().size() == 0) {
                                userInterface.NoIPs();
                            } else {
                                userInterface.availableIPs();
                                System.out.println(GameRunner.getWorlds().size());
                            }

                            break;
                    }
                    break;
                case "launchRobot":
                    switch (yourChoice) {
                        case "option1":
                            GameRunner.loop1();
                            userInterface.commands();
                            break;
                        case "option2":
                            command = "close";
                            userInterface.close();
                            break;
                        case "option3":
                            userInterface.window.dispose();
                            new userInterface();
                            break;
                    }
                    break;
                case "commands":
                case "moveY":
                case "turn":
                case "moveX":
                    switch (yourChoice) {
                        case "c1":
                            playerButtons("forward 5");
                            break;
                        case "c2":
                            playerButtons("back 5");
                            break;
                        case "c3":
                            playerButtons("turn left");
                            break;
                        case "c4":
                            playerButtons("turn right");
                            break;
                    }
                    break;
            }
        }
    }


    public void back(){
        command = "back 5";
        imports();
    }

    public void forward() {
        command = "forward 5";
        imports();
    }

    public void left() {
        command = "turn left";
        imports();
        userInterface.turn();
    }

    public void right() {
        command = "turn right";
        imports();
        userInterface.turn();
    }

    public void imports() {
        GameRunner.command0.add(command);
        GameRunner.loop2();
        GameRunner.loop1();
    }

    /**shorten getting button movements from maze**/
    public static void playerButtons(String command){
        userInterface.world.movePlayerButtons(command);
    }


    public static void reload() {
        userInterface.mainTextArea1.setText(userInterface.robotName() + ": Reloading...");
        userInterface.imports("reload");
    }

    public static void fire() {
        userInterface.mainTextArea1.setText(userInterface.robotName() + ": Shots fired!!! I hope I'm not just wasting bullets.");
        userInterface.imports("fire");
    }

    public static void repair() {
        userInterface.mainTextArea1.setText(userInterface.robotName() + ": Repairing.. I hope no one sees me.");
        userInterface.imports("repair");
    }


    public static void look(){
        userInterface.mainTextArea1.setText(userInterface.robotName() + ": What am I looking at here??");
        userInterface.imports("look");

        JFrame frame = new JFrame("WHATS AHEAD??");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel textLabel = new JLabel("" +"<html>" +
                Display.look.get(0).replace(".", ".<br><br>") + "</html>");

        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        Display.look.clear();
    }
}
