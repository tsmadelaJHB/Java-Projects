package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.Position;
import za.co.wethinkcode.robotworlds.WorldNavigator;

public class Mine {
    private final int x;
    private final int y;

    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getMineX() {
        return this.x;
    }

    public int getMineY() {
        return this.y;
    }

    /** Returns the position of the mine
     */
    public Position minePos(){
        return (new Position(this.x,this.y));
    }


    public boolean mineBoom(Position position){
        WorldNavigator.mine.remove(position);
        return position.getX() == getMineX() && position.getY() == getMineY();
    }

    /** Checks if the Robot steps on a mine. If the robot steps on the mine, it incurs damage. Then the mine is removed
     * from the list because it has been detonated.
     */
    public boolean goBoom(Position a, Position b){
        if (a.getY() == b.getY()) {
            if (a.getX() > b.getX()) {
                for (int i = b.getX(); i <= a.getX(); i++) {
                    if (mineBoom(new Position(i, b.getY()))) {
                        WorldNavigator.mine.remove(new Position(i, b.getY()));
                        return true;
                    }
                }
            }
            if (a.getX() < b.getX()) {
                for (int i = a.getX(); i <= b.getX(); i++) {
                    if (mineBoom(new Position(i, b.getY()))) {
                        WorldNavigator.mine.remove(new Position(i, b.getY()));
                        return true;
                    }
                }
            }
            return false;
        }
        else if (a.getX() == b.getX()) {
            if (a.getY() > b.getY()) {
                for (int i = b.getY(); i <= a.getY(); i++) {
                    if (mineBoom(new Position(a.getX(), i))) {
                        WorldNavigator.mine.remove(new Position(a.getX(), i));
                        return true;
                    }
                }
            }
        }
        if (a.getY() < b.getY()) {
            for (int j = a.getY(); j <= (b.getY()); j++) {
                if (mineBoom(new Position(a.getX(), j))) {
                    WorldNavigator.mine.remove(new Position(a.getX(), j));
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
