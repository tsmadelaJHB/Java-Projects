package za.co.wethinkcode.robotWorld;

public class Obstacle {
    private final int x;
    private final int y;
    
    private final int size = 5;

    public Obstacle(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }

    public Obstacle(int xCoor, int yCoor) {
        this.x = xCoor;
        this.y = yCoor;
    }

    public int getBottomLeftX() {
        return this.x;
    }

    public int getBottomLeftY() {
        return this.y;
    }

    public Position getBottomLeft() {
        return new Position(this.x, this.y);
    }

    public boolean blocksPosition(Position position) {
        return this.x <= position.getX() && position.getX() < this.x + getSize() && 
        this.y <= position.getY() && position.getY() < this.y + getSize();
    }

    public boolean blocksPath(Position a, Position b) {
        if(a.getX() == b.getX() && this.x <= a.getX() && a.getX() < this.x + this.size) {
            if(a.getY() > b.getY()) {
                return b.getY() <= this.y && this.y <= a.getY();
            } else {
                return a.getY() <= this.y && this.y <= b.getY();
            }
        } 
        
        else if(a.getY() == b.getY() && this.y <= a.getY() && a.getY() < this.y + this.size) {
            if(a.getX() > b.getX()) {
                return b.getX() <= this.x && this.x <= a.getX();
            } else {
                return a.getX() <= this.x && this.x <= b.getX();
            }
        }
        return false;
    }

    public Integer getSize() {
        return this.size;
    }
}
