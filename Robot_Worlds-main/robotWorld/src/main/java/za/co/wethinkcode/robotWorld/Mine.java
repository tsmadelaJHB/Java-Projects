package za.co.wethinkcode.robotWorld;

public class Mine {
    private int x;
    private int y;

    public Mine(int xCoor, int yCoor) {
        this.x = xCoor;
        this.y = yCoor;
    }

    public Mine(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public boolean blocksPosition(Position position) {
        return this.x == position.getX() && this.y == position.getY();
    }

    public boolean blocksPath(Position a, Position b) {
        if(a.getX() == b.getX() && this.x == a.getX()) {
            if(a.getY() > b.getY()) {
                return b.getY() <= this.y && this.y <= a.getY();
            } else {
                return a.getY() <= this.y && this.y <= b.getY();
            }
        } 
        
        else if(a.getY() == b.getY() && this.y == a.getY()) {
            if(a.getX() > b.getX()) {
                return b.getX() <= this.x && this.x <= a.getX();
            } else {
                return a.getX() <= this.x && this.x <= b.getX();
            }
        }
        return false;
    }
}
