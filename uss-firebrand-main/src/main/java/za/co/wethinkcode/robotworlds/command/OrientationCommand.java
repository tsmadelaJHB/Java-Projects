package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.IWorldNavigator;
import za.co.wethinkcode.robotworlds.Robot;

public class OrientationCommand extends Command {
    /** The directionIndex increments by one when a Right turn is made and decrements by one when a Left turn is made.
     * This method simply prints out the cardinal direction the robot is facing.
     * @param target: Input from Robot as a command to turn show Orientation
     * @return true: Returns true when orientation has been executed
     */
    @Override
    public boolean execute(Robot target) {
        int cardinalDirection = target.getCardinalDirection();
        IWorldNavigator.Direction direction;

        if (cardinalDirection == 0) {
            direction = IWorldNavigator.Direction.NORTH;
        } else if (cardinalDirection == 1) {
            direction = IWorldNavigator.Direction.EAST;
        } else if (cardinalDirection == 2) {
            direction = IWorldNavigator.Direction.SOUTH;
        } else {
            direction = IWorldNavigator.Direction.WEST;
        }
        target.setStatus("Facing "+direction+".");
        return true;
    }

    public OrientationCommand() {
        super("orientation");
    }
}
