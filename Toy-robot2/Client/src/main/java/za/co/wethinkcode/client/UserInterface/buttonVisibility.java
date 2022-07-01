package za.co.wethinkcode.client.UserInterface;


public class buttonVisibility {
    /**show visibility of buttons, texts and inputs
     **/
    public static void inputVisibility() {
        userInterface.nameInput.setVisible(true);
        userInterface.stepsInput.setVisible(false);
        userInterface.choiceButtonPanel.setVisible(false);
    }


    public static void mainVisibility(){
        userInterface.mainTextArea.setVisible(false);
        userInterface.mainTextPanel.setVisible(false);
        userInterface.optionButtonPanel.setVisible(false);
    }


    public static void InputOptionVisibility() {
        userInterface.choiceButtonPanel.setVisible(false);
        userInterface.optionButtonPanel.setVisible(true);
        userInterface.option1.setVisible(false);
        userInterface.option2.setVisible(false);
        userInterface.option3.setVisible(false);
        userInterface.option4.setVisible(false);
    }


    public static void ipVisibility(){
        userInterface.option1.setVisible(true);
        userInterface.option2.setVisible(true);
        userInterface.option3.setVisible(true);
        userInterface.option4.setVisible(true);
        userInterface.optionButtonPanel.setVisible(true);
    }


    public static void statsVisibility() {
        userInterface.playerPanel2.setVisible(true);
        choiceVisibility();
        mainVisibility();
    }


    public static void choiceVisibility() {
        userInterface.nameInput.setVisible(false);
        userInterface.stepsInput.setVisible(false);
        userInterface.choiceButtonPanel.setVisible(true);
    }


    public static void deadVisibility() {
        userInterface.nameInput.setVisible(false);
        userInterface.stepsInput.setVisible(false);
        userInterface.choiceButtonPanel.setVisible(false);
    }


    public static void optionVisibility() {
        userInterface.nameInput.setVisible(false);
        userInterface.stepsInput.setVisible(false);
        userInterface.option1.setVisible(true);
        userInterface.option2.setVisible(true);
        userInterface.option3.setVisible(true);
        userInterface.option4.setVisible(true);
    }

    public static void Visibility() {
        userInterface.nameInput.setVisible(false);
        userInterface.stepsInput.setVisible(false);
        userInterface.stepsInput.setVisible(false);
        userInterface.choiceButtonPanel.setVisible(false);
        userInterface.optionButtonPanel.setVisible(false);
        userInterface.mainTextPanel.setVisible(false);
    }

    public static void actionCommandsVisibility(){
        userInterface.repairButton.setVisible(false);
        userInterface.lookButton.setVisible(false);
        userInterface.fireButton.setVisible(false);
        userInterface.reloadButton.setVisible(false);
    }

    public static void choiceText() {
        userInterface.updateStats();
        userInterface.choice1.setText("FORWARD");
        userInterface.choice2.setText("BACK");
        userInterface.choice3.setText("LEFT");
        userInterface.choice4.setText("RIGHT");
    }
}