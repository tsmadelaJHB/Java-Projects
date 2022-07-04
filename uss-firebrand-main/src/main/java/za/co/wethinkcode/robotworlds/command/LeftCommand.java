package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.Robot;

public class LeftCommand extends Command {
    /** This method checks the current direction and makes a decision on where Left is, depending on where
     * the robot is currently facing.
     * @param target: Input from Robot as a command to turn Left
     * @return true: Returns true when the Left turn has been executed
     */
    @Override
    public boolean execute(Robot target) {
        Robot.directionIndex -= 1; // For the cardinal directions
        if (Robot.directionIndex < 0) {
            Robot.directionIndex = 3;
        }

        target.updateDirection(false);
        target.setStatus("Turned left.");
        return true;
    }

    public LeftCommand() {
        super("left");
    }
}
