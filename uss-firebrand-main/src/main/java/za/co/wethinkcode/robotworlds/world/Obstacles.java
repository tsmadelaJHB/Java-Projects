//package za.co.wethinkcode.robotworlds.world;
//
//import za.co.wethinkcode.robotworlds.Position;
//
//public class Obstacles {
//    private final int x;
//    private final int y;
//    private final int size;
//
//    public Obstacles(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.size = 5;
//    }
//
//    public int getBottomLeftX() {
//        return this.x;
//    }
//
//    public int getBottomLeftY() {
//        return this.y;
//    }
//
//    public int getSize() {
//        return this.size;
//    }
//
//    /** Takes in the position of the robot and depending on that, checks if any Obstacle blocks the robot.
//     * If the X and Y are blocked, return True.
//     * Else return False.
//     */
//    public boolean blocksPosition(Position position) {
//        for (int i = getBottomLeftX(); i < (getBottomLeftX() + 5); i++) {
//            for (int j = getBottomLeftY(); j < (getBottomLeftY() + 5); j++) {
//                if (position.getX() == i && position.getY() == j) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean blocksPath(Position a, Position b) {
//        if (a.getY() == b.getY()) {
//            if (a.getX() > b.getX()) {
//                for (int i = b.getX(); i <= a.getX(); i++) {
//                    if (blocksPosition(new Position(i, b.getY())))
//                        return true;
//                }
//            }
//            if (a.getX() < b.getX()) {
//                for (int i = a.getX(); i <= b.getX(); i++) {
//                    if (blocksPosition(new Position(i, b.getY())))
//                        return true;
//                }
//            }
//            return false;
//        }
//        else if (a.getX() == b.getX()) {
//            if (a.getY() > b.getY()) {
//                for (int i = b.getY(); i <= a.getY(); i++)
//                    if (blocksPosition(new Position(a.getX(), i)))
//                        return true;
//            }
//        }
//        if (a.getY() < b.getY()) {
//            for (int j = a.getY(); j <= (b.getY()); j++)
//                if (blocksPosition(new Position(a.getX(), j)))
//                    return true;
//
//            return false;
//        }
//        return false;
//    }
//}
