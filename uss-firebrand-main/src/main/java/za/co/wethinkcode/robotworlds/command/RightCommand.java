package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.Robot;

public class RightCommand extends Command {
    /** This method checks the current direction and makes a decision on where Right is, depending on where
     * the robot is currently facing.
     * @param target: Input from Robot as a command to turn Right
     * @return true: Returns true when the Right turn has been executed
     */
    @Override
    public boolean execute(Robot target) {
        Robot.directionIndex += 1; // For the cardinal directions
        if (Robot.directionIndex > 3) {
            Robot.directionIndex = 0;
        }

        target.updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }

    public RightCommand() {
        super("right");
    }
}
