package za.co.wethinkcode.robotWorld;

public interface IWorld {

    enum Direction {
        NORTH,
        EAST,
        WEST,
        SOUTH
    }

    enum Response {
        SUCCESS, //position was updated successfully
        SUCCESS_STEPPED_ON_MINE, //robot stepped on a mine and survived.
        FAILED_OUTSIDE_WORLD, //robot will go outside world limits if allowed, so it failed to update the position
        FAILED_OBSTRUCTED, //robot obstructed by at least one obstacle, thus cannot proceed.
        FAILED_FELL_IN_PIT, //robot fell into at least one pit, thus cannot proceed.
        FAILED_STEPPED_ON_MINE, //robot stepped on a mine and died.
    }

    public Response updatePosition(int nrSteps, Robot robot);
}
