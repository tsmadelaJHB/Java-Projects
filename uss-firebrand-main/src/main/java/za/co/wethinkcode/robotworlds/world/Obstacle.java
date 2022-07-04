package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.Position;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {
    private final Position TopLeft;
    private final Position BottomRight;

    public Obstacle(Position topLeft, Position bottomRight) {
        this.TopLeft = topLeft;
        this.BottomRight = bottomRight;
    }

    public List<Position> blockedCords(){
        List<Position>  cords = new ArrayList<>();
        for (int x = TopLeft.getX(); x <= BottomRight.getX(); x++){
            for (int y = TopLeft.getY(); y >= BottomRight.getY(); y--){
                cords.add(new Position(x,y));
            }
        }
        return cords;
    }

    public Position getBottomRight() {
        return BottomRight;
    }

    public Position getTopLeft() {
        return TopLeft;
    }

    public int getTopLeftX(){
        return TopLeft.getX();
    }

    public int getTopLeftY(){
        return TopLeft.getY();
    }

    public int getBottomRightX(){
        return BottomRight.getX();
    }

    public int getBottomRightY(){
        return  BottomRight.getY();
    }

    public boolean blocksPosition(Position position){
        int topLeftX = this.TopLeft.getX();
        int bottomRightX = this.BottomRight.getX();
        int topLeftY = this.TopLeft.getY();
        int bottomRightY = this.BottomRight.getY();

        if(position.getX() <=BottomRight.getX() && position.getX() >= TopLeft.getX()){
            return position.getY() <= TopLeft.getY() && position.getY() >= BottomRight.getY();
        }
        return false;
    }



    public boolean blocksPath(Position start, Position end){
        if(start.getY() < end.getY()){
            for(int i = start.getY(); i < end.getY(); i++){
                Position path = new Position(start.getX(),i);
                if (blocksPosition(path)){
                    return true;
                }
            }
        }else if(start.getY() > end.getY()){
            for (int i = end.getY(); i < start.getY();i++){
                Position path = new Position(end.getX(),i);
                if (blocksPosition(path)){
                    return true;
                }
            }
        }else if(start.getX() > end.getX()){
            for(int i = end.getX(); i < start.getX(); i++){
                Position path = new Position(i,end.getY());
                if (blocksPosition(path)){
                    return true;
                }
            }
        }else if(start.getX() < end.getX()){
            for(int i = start.getX(); i < end.getX(); i++){
                Position path = new Position(i,start.getY());
                if (blocksPosition(path)){
                    return true;
                }
            }
        }
        return false;
    }

}
