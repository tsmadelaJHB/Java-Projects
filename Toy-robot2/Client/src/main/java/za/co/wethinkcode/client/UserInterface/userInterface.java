package za.co.wethinkcode.client.UserInterface;

import za.co.wethinkcode.client.Display;
import za.co.wethinkcode.client.DynamicConnection;
import za.co.wethinkcode.client.GameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Arrays;
import java.util.List;


public class userInterface {

    public static JFrame window;
    public static Container con;
    public static JPanel titleNamePanel,exitButtonPanel, startButtonPanel, mainTextPanel, mainTextPanel1, exitPanel, lineTextPanel, lineTextPanel1, choiceButtonPanel, optionButtonPanel, playerPanel, playerPanel2, instructionPanel, actionPanel, ipPanel, heroPanel;

    public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    //status,shots,position,direction,shield
    public static JLabel titleNameLabel, statusLabel, statusLabelNumber, shotsLabel, shotLabelName, shieldLabel, shieldLabelName, directionLabel, directionLabelName, positionLabel, positionLabelName;
    public static Font titleFont = new Font(Font.SERIF, Font.PLAIN, 90);
    public static Font normalFont = new Font(Font.SERIF, Font.PLAIN, 25);
    public static Font textFont = new Font(Font.SERIF, Font.PLAIN, 22);
    public static Font exitFont = new Font(Font.SERIF, Font.PLAIN, 30);

    public static JButton startButton, choice1, choice2, choice3, choice4, choice5, option1, option2, option3, option4, exitButton, instructionButton, lookButton, repairButton, fireButton, reloadButton;
    public static JTextArea mainTextArea, mainTextArea1, exitTextArea, lineTestArea, lineTestArea1;
    public static JTextField nameInput, stepsInput;
    public static int shield;
    public static String whatsNext;
    public static JButton[] ipButton, heroButton;

    public static TitleScreenHandler tsHandler = new TitleScreenHandler();
    public static FrameNavigation.ChoiceHandler choiceHandler = new FrameNavigation.ChoiceHandler();
    public static gameExitHandler exit = new gameExitHandler();
    public static instructionHandler instruction = new instructionHandler();
    public static actionHandler action = new actionHandler();

    public static World world;
    public static List<String> hero = Arrays.asList("SniperMask", "GunSlinger", "Akitari", "Doc");
    public static List<String> playerDetails = new ArrayList<>();


    /**start up window**/
    public userInterface() {
        window = new JFrame();
        window.setSize(1250, 750);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setResizable(false);
        con = window.getContentPane();

        //for the info in the UI
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(250, 50, 800,screen.height);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("ROBOT WORLDS");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        //start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(560, 600, 150,45);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);
        startButtonPanel.setVisible(true);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
    }




    /**exit button**/
    public static void exit() {
        exitButtonPanel = new JPanel();
        exitButtonPanel.setBounds(1100, 665, 150,45);
        exitButtonPanel.setBackground(Color.black);

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(normalFont);
        exitButton.addActionListener(exit);
        exitButton.setFocusPainted(false);
        exitButtonPanel.add(exitButton);
        con.add(exitButtonPanel);
    }


    /**instruction button**/
    public static void instructions() {
        instructionPanel = new JPanel();
        instructionPanel.setBounds(960, 665, 250,45);
        instructionPanel.setBackground(Color.black);
        instructionPanel.setForeground(Color.white);

        instructionButton = new JButton("HELP!!");
        instructionButton.setBackground(Color.black);
        instructionButton.setForeground(Color.white);
        instructionButton.setFont(normalFont);
        instructionButton.addActionListener(instruction);
        instructionButton.setFocusPainted(false);
        instructionButton.setVisible(true);
        instructionPanel.add(instructionButton);
        con.add(instructionPanel);
    }


    public static void actionCommands() {
        //RELOAD
        actionPanel = new JPanel();
        actionPanel.setBounds(1020, 430, 140,45);
        actionPanel.setBackground(Color.black);

        reloadButton = new JButton("RELOAD");
        reloadButton.setBackground(Color.black);
        reloadButton.setForeground(Color.white);
        reloadButton.setFont(normalFont);
        reloadButton.addActionListener(action);
        reloadButton.setFocusPainted(false);
        actionPanel.add(reloadButton);
        con.add(actionPanel);

        //FIRE
        actionPanel = new JPanel();
        actionPanel.setBounds(1040, 480, 100,45);
        actionPanel.setBackground(Color.black);

        fireButton = new JButton("FIRE!!");
        fireButton.setBackground(Color.black);
        fireButton.setForeground(Color.white);
        fireButton.setFont(normalFont);
        fireButton.addActionListener(action);
        fireButton.setFocusPainted(false);
        actionPanel.add(fireButton);
        con.add(actionPanel);

        //REPAIR
        actionPanel = new JPanel();
        actionPanel.setBounds(1023, 530, 128,45);
        actionPanel.setBackground(Color.black);

        repairButton = new JButton("REPAIR");
        repairButton.setBackground(Color.black);
        repairButton.setForeground(Color.white);
        repairButton.setFont(normalFont);
        repairButton.addActionListener(action);
        repairButton.setFocusPainted(false);
        actionPanel.add(repairButton);
        con.add(actionPanel);

        //LOOK
        actionPanel = new JPanel();
        actionPanel.setBounds(1035, 580, 100,45);
        actionPanel.setBackground(Color.black);

        lookButton = new JButton("LOOK");
        lookButton.setBackground(Color.black);
        lookButton.setForeground(Color.white);
        lookButton.setFont(normalFont);
        lookButton.addActionListener(action);
        lookButton.setFocusPainted(false);
        actionPanel.add(lookButton);
        con.add(actionPanel);
    }


    /**display world**/
    public static void showMaze(){
        world = new World();
        new Thread(world).start();
    }


    public static void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(370, 102, 510, 40);
        mainTextPanel.setBackground(Color.GREEN);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(370, 102, 510, 40);
        mainTextArea.setBackground(Color.blue);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);

        //player movement text
        mainTextPanel1 = new JPanel();
        mainTextPanel1.setBounds(25, 670, 920, 38);
        mainTextPanel1.setBackground(Color.lightGray);
        con.add(mainTextPanel1);
        mainTextArea1 = new JTextArea(" Don't know what to do? Click HELP!!    ===>");
        mainTextArea1.setBounds(25, 670, 920, 36);
        mainTextArea1.setBackground(Color.black);
        mainTextArea1.setForeground(Color.white);
        mainTextArea1.setFont(textFont);
        mainTextArea1.setLineWrap(true);
        mainTextArea1.setWrapStyleWord(true);
        mainTextArea1.setEditable(false);
        mainTextPanel1.add(mainTextArea1);

        //exit text
        exitPanel = new JPanel();
        exitPanel.setBounds(365, 230, 500, 250);
        exitPanel.setBackground(Color.lightGray);
        con.add(exitPanel);
        exitTextArea = new JTextArea("");
        exitTextArea.setBounds(365, 102, 500, 250);
        exitTextArea.setBackground(Color.black);
        exitTextArea.setForeground(Color.white);
        exitTextArea.setFont(exitFont);
        exitTextArea.setLineWrap(true);
        exitTextArea.setWrapStyleWord(true);
        exitTextArea.setEditable(false);

        exitPanel.add(exitTextArea);
        exitPanel.setVisible(false);

        lineTextPanel = new JPanel();
        lineTextPanel.setBounds(25, 40, 1199, 30);
        lineTextPanel.setBackground(Color.black);
        con.add(lineTextPanel);
        lineTestArea = new JTextArea("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        lineTestArea.setBounds(25, 40, 1199, 30);
        lineTestArea.setBackground(Color.black);
        lineTestArea.setForeground(Color.white);
        lineTestArea.setFont(normalFont);
        lineTestArea.setLineWrap(true);
        lineTestArea.setWrapStyleWord(true);
        lineTestArea.setEditable(false);
        lineTextPanel.add(lineTestArea);

        lineTextPanel1 = new JPanel();
        lineTextPanel1.setBounds(25, 640, 1199, 30);
        lineTextPanel1.setBackground(Color.black);
        con.add(lineTextPanel1);
        lineTestArea1 = new JTextArea("------------------------------------------------------------------------------------------------------------------------------------------------------");
        lineTestArea1.setBounds(25, 640, 1199, 30);
        lineTestArea1.setBackground(Color.black);
        lineTestArea1.setForeground(Color.white);
        lineTestArea1.setFont(normalFont);
        lineTestArea1.setEditable(false);
        lineTextPanel1.add(lineTestArea1);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(960, 200, 265, 220);
        choiceButtonPanel.setBackground(Color.gray);
        choiceButtonPanel.setLayout(new GridLayout(6, 1));
        con.add(choiceButtonPanel);

        optionButtonPanel = new JPanel();
        optionButtonPanel.setBounds(480, 300, 300, 200);
        optionButtonPanel.setBackground(Color.black);
        optionButtonPanel.setLayout(new GridLayout(5, 1));
        con.add(optionButtonPanel);

        //name input
        nameInput = new JTextField();
        nameInput.setBackground(Color.black);
        nameInput.setForeground(Color.white);
        nameInput.setFont(normalFont);
        nameInput.addActionListener(choiceHandler);
        nameInput.setActionCommand("inputName");
        optionButtonPanel.add(nameInput);

        //steps input
        stepsInput = new JTextField();
        stepsInput.setBackground(Color.black);
        stepsInput.setForeground(Color.white);
        stepsInput.setFont(normalFont);
        stepsInput.addActionListener(choiceHandler);
        stepsInput.setActionCommand("inputName");
        stepsInput.setEditable(true);
        choiceButtonPanel.add(stepsInput);

        option1 = new JButton("Option 1");
        option1.setBackground(Color.black);
        option1.setForeground(Color.white);
        option1.setFont(normalFont);
        option1.setFocusPainted(false);
        option1.addActionListener(choiceHandler);
        option1.setActionCommand("option1");
        optionButtonPanel.add(option1);

        option2 = new JButton("Option 2");
        option2.setBackground(Color.black);
        option2.setForeground(Color.white);
        option2.setFont(normalFont);
        option2.setFocusPainted(false);
        option2.addActionListener(choiceHandler);
        option2.setActionCommand("option2");
        optionButtonPanel.add(option2);

        option3 = new JButton("Option 3");
        option3.setBackground(Color.black);
        option3.setForeground(Color.white);
        option3.setFont(normalFont);
        option3.setFocusPainted(false);
        option3.addActionListener(choiceHandler);
        option3.setActionCommand("option3");
        optionButtonPanel.add(option3);

        option4 = new JButton("Option 4");
        option4.setBackground(Color.black);
        option4.setForeground(Color.white);
        option4.setFont(normalFont);
        option4.setFocusPainted(false);
        option4.addActionListener(choiceHandler);
        option4.setActionCommand("option4");
        optionButtonPanel.add(option4);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        choice5 = new JButton();
        choice5.setBackground(Color.gray);
        choice5.setForeground(Color.white);
        choice5.setFont(normalFont);
        choice5.setFocusPainted(false);
        choice5.addActionListener(choiceHandler);
        choice5.setActionCommand("c5");
        choiceButtonPanel.add(choice5);

        //setup status, shots, shield
        playerPanel = new JPanel();
        playerPanel.setBounds(30, 5, 1250, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 3));
        con.add(playerPanel);

        playerPanel2 = new JPanel();
        playerPanel2.setBounds(960, 80, 265, 100);
        playerPanel2.setBackground(Color.black);
        playerPanel2.setLayout(new GridLayout(2, 2));
        con.add(playerPanel2);
        playerPanel2.setVisible(false);

        //direction label
        directionLabel = new JLabel("Direction:");
        directionLabel.setFont(normalFont);
        directionLabel.setForeground(Color.white);
        playerPanel.add(directionLabel);
        //direction label number
        directionLabelName = new JLabel();
        directionLabelName.setFont(normalFont);
        directionLabelName.setForeground(Color.white);
        playerPanel.add(directionLabelName);

        //position label
        positionLabel = new JLabel("Position:");
        positionLabel.setFont(normalFont);
        positionLabel.setForeground(Color.white);
        playerPanel.add(positionLabel);
        //posiiton label number
        positionLabelName = new JLabel();
        positionLabelName.setFont(normalFont);
        positionLabelName.setForeground(Color.white);
        playerPanel.add(positionLabelName);

        //status label
        statusLabel = new JLabel("Status:");
        statusLabel.setFont(normalFont);
        statusLabel.setForeground(Color.white);
        playerPanel.add(statusLabel);
        //status label number
        statusLabelNumber = new JLabel();
        statusLabelNumber.setFont(normalFont);
        statusLabelNumber.setForeground(Color.white);
        playerPanel.add(statusLabelNumber);

        //shots label
        shotsLabel = new JLabel("Shots:");
        shotsLabel.setFont(normalFont);
        shotsLabel.setForeground(Color.white);
        shotsLabel.setBackground(Color.red);
        playerPanel2.add(shotsLabel);
        //shot label name
        shotLabelName = new JLabel();
        shotLabelName.setFont(normalFont);
        shotLabelName.setForeground(Color.white);
        playerPanel2.add(shotLabelName);

        //shield label
        shieldLabel = new JLabel("Shield:");
        shieldLabel.setFont(normalFont);
        shieldLabel.setForeground(Color.white);
        shieldLabel.setBackground(Color.red);
        playerPanel2.add(shieldLabel);
        //shield label name
        shieldLabelName = new JLabel();
        shieldLabelName.setFont(normalFont);
        shieldLabelName.setForeground(Color.white);
        playerPanel2.add(shieldLabelName);

        instructions();
        playerSetup();
    }


    /**connect interface stats with server**/
    public static void updateStats(){
        statusLabelNumber.setText(Display.status);
        shotLabelName.setText("" + Display.shots);
        positionLabelName.setText(Display.position);
        directionLabelName.setText(Display.Direction);
    }


    public static void playerSetup() {
        shield = 3;
        shieldLabelName.setText("" + shield);
        updateStats();
        RobotName();
    }


    public static String robotName() {
        return nameInput.getText().toUpperCase();
    }


    public static void RobotName() {
        whatsNext = "robotName";
        mainTextArea.setText("What would you like to name the robot?");
        buttonVisibility.InputOptionVisibility();
        buttonVisibility.inputVisibility();
        nameInput.setText("");
        playerDetails.add(nameInput.getText());
    }


    public static void PlayerTypes() {
        optionButtonPanel.setVisible(false);
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
        nameInput.setVisible(false);

        whatsNext = "playerType";
        mainTextArea.setText("         "+robotName()+": Choose your hero.");

        heroPanel = new JPanel();
        heroPanel.setBounds(460, 250, 350, 200);
        heroPanel.setBackground(Color.black);
        heroPanel.setLayout(new GridLayout(4, 1));
        heroPanel.setVisible(true);
        con.add(heroPanel);

        heroButton = new JButton[hero.size()];
        for (int i = 0; i < heroButton.length; i++) {
            heroButton[i] = new JButton(hero.get(i));
            heroButton[i].setBackground(Color.black);
            heroButton[i].setForeground(Color.white);
            heroButton[i].setFont(normalFont);
            heroButton[i].setActionCommand(hero.get(i));
            heroButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String choice = actionEvent.getActionCommand();
                    playerDetails.add(choice);
                    chooseMaze();
                }
            });
            heroPanel.add(heroButton[i]);
        }
    }


    public static void ipButton(){
        ipPanel = new JPanel();
        ipPanel.setBounds(150, 250, 900, 200);
        ipPanel.setBackground(Color.black);
        ipPanel.setLayout(new GridLayout(5, 5));
        ipPanel.setVisible(true);
        con.add(ipPanel);

        ipButton = new JButton[GameRunner.getWorlds().size()];
        for (int i = 0; i < ipButton.length; i++) {
            ipButton[i] = new JButton(GameRunner.getWorlds().get(i));
            ipButton[i].setActionCommand(GameRunner.getWorlds().get(i));
            ipButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String choice = actionEvent.getActionCommand();
                    FrameNavigation.temp.add(choice);
                    if (choice.equals("127.0.0.1")) {
                        ipPanel.setVisible(false);
                        FrameNavigation.maze = "RandomMaze";
                        GameRunner.connect2World("RandomMaze"+" "+FrameNavigation.temp.get(0), "y");
                    } else {
                        FrameNavigation.maze = "EmptyMaze";
                        GameRunner.connect2World("EmptyMaze" + " " + FrameNavigation.temp.get(0), "y");
                    }
                    launchRobot();
                }
            });
            ipPanel.add(ipButton[i]);
        }
    }


    public static void availableIPs() {
        whatsNext = "IPselection";
        mainTextArea.setText("         Select world to connect...");
        System.out.println(GameRunner.getWorlds());
        buttonVisibility.deadVisibility();
        buttonVisibility.optionVisibility();
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
        optionButtonPanel.setVisible(false);

        ipButton();
    }


    public static void NoIPs() {
        mainTextArea.setText("No available worlds. Select different option:");
        count = 1;
        ipPanel.setVisible(false);
        buttonVisibility.ipVisibility();
        chooseMaze();
    }


    static int count = 0;
    public static void chooseMaze() {
        whatsNext = "chooseMaze";
        if (count == 0)
            mainTextArea.setText("      Select world from the following:");

        buttonVisibility.deadVisibility();
        buttonVisibility.optionVisibility();
        heroPanel.setVisible(false);
        option1.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(true);
        option4.setVisible(true);
        optionButtonPanel.setVisible(true);

        option1.setText("EmptyMaze");
        option2.setText("RandomMaze");
        option3.setText("DesignedMaze");
        option4.setText("ConnectToMaze");
    }


    public static void launchRobot() {
        whatsNext = "launchRobot";
        mainTextArea.setText("                  Launch " + robotName() + "?");

        if (!FrameNavigation.temp.get(0).equals("127.0.0.1")) {
            ipPanel.setVisible(false);
        }
        buttonVisibility.ipVisibility();

        option1.setText("YES");
        option2.setText("NO");
        option3.setText("RESET");
        option4.setText("");
    }


    public static void commands() {
        instructionPanel.setBounds(960, 665, 150,45);
        exit();
        actionCommands();

        buttonVisibility.statsVisibility();
        whatsNext = "commands";
        mainTextArea1.setText(robotName() + ": What command would you like to run next?");
        showMaze();
        buttonVisibility.choiceText();
    }


    public static String[] getCommand(){
        return GameRunner.command0.get(0).split(" ");
    }


    public static void moveY() {
        whatsNext = "moveY";
        mainTextArea1.setText(robotName()+": Moved "+getCommand()[0]+" "+getCommand()[1]+" steps. What command would you like to run next?");
        buttonVisibility.choiceText();
    }


    public static void moveX() {
        whatsNext = "moveX";
        mainTextArea1.setText(robotName()+": Moved "+getCommand()[0]+" "+getCommand()[1]+" steps. What command would you like to run next?");
        buttonVisibility.choiceText();
    }


    public static void lose(){
        whatsNext = "lose";
        exitTextArea.setText(robotName() +": You are dead\n\nNow that's what I call a damaged turtle\n\n<GAME OVER>");
        updateStats();
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }


    public static void close() {
        GameRunner.command0.add(FrameNavigation.command);
        buttonVisibility.Visibility();
        exitPanel.setVisible(true);
        exitTextArea.setText(". . . . .Sad to see you leave. . . . .\n\n. . . . . . . This is a future . . . . . . \n\n            <GAME OVER>");
        mainTextArea1.setText(robotName()+": This game is only for the brave... BYE!!");

        updateStats();
        Timer timer = new Timer(5000, (e) -> window.dispose());
        timer.start();

        GameRunner.loop2();
        GameRunner.loop1();
    }


    public static void exitWindow() {
        buttonVisibility.actionCommandsVisibility();
        world.canvas.setVisible(false);
        playerPanel2.setVisible(false);
        GameRunner.command0.add("close");
        close();
    }


    public static void turn() {
        whatsNext = "turn";
        mainTextArea1.setText(robotName() +": Turned "+getCommand()[1] +". What command would you like to run next?");
        buttonVisibility.choiceText();
    }


    public static void imports(String command) {
        GameRunner.command0.add(command);
        GameRunner.loop2();
        GameRunner.loop1();
    }


    public static class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }


    /** shows pop up exit window **/
    public static class gameExitHandler implements ActionListener {
        public void actionPerformed(ActionEvent even) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit the program?","Can't face the music?",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                FrameNavigation.command = "close";
                world.on = 0;
                world.run();
                exitWindow();
            }
        }
    }


    /** shows pop up help/instructions window **/
    public static class instructionHandler implements ActionListener {
        public void actionPerformed(ActionEvent even) {
            {
                JFrame frame = new JFrame("INSTRUCTIONS");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                JLabel textLabel = new JLabel("" +"<html>" +
                        "Follow on-screen instructions to start the game.<br>"+
                        "1. Place cursor on input box, insert player name and press ENTER. <br>"+
                        "2. Choose world.<br>"+
                        "3. Launch your player.<br>"+
                        "4. Player is an oval.<br> " +
                        "<br>"+
                        "PLAYER ACTION KEYS:<br>"+
                        "1. Use on-screen buttons or.. <br>"+
                        "2. Use standard arrow keys for movement.<br> " +
                        " => Arrow up = MOVE FORWARD <br>"+
                        " => Arrow down = MOVE BACK <br>"+
                        " => Arrow right = TURN RIGHT <br>"+
                        " => Arrow left = TURN LEFT <br>"+
                        "3. Keyboard keys for action.<br>"+
                        " => A = FIRE <br>"+
                        " => S = RELOAD <br>"+
                        " => Z = LOOK <br>"+
                        " => X = REPAIR <br>"+
                        "<br>"+
                        "SWITCHING FROM BUTTONS TO ARROW KEYS:<br>"+
                        "1. Click on maze to use arrow keys. <br>"+
                        "2. Click on buttons. </html>");

                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
            }
        }
    }


    public static class actionHandler implements ActionListener {
        public void actionPerformed(ActionEvent even) {
            {
                if (even.getActionCommand().equals("RELOAD")) {
                    FrameNavigation.reload();
                } else if (even.getActionCommand().equals("FIRE!!")) {
                    FrameNavigation.fire();
                } else if (even.getActionCommand().equals("REPAIR")) {
                    FrameNavigation.repair();
                } else {
                    FrameNavigation.look();
                }
                GameRunner.command0.clear();
                userInterface.updateStats();
            }
        }
    }
}