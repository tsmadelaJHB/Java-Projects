package za.co.wethinkcode.robotworlds.maze;

import za.co.wethinkcode.robotworlds.Position;
import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements Maze{
    List <Obstacle> obs = new ArrayList<>();
    List <Pits> pit = new ArrayList<>();
    List <Mine> mines = new ArrayList<>();

    /** Adds obstacles to the list
     * @param obstacle obstacle object
     */
    @Override
    public void MakeObsList(Obstacle obstacle){
        this.obs.add(obstacle);
    }

    @Override
    public void MakePitsList(Pits pits){
        this.pit.add(pits);
    }

    @Override
    public void MakeMinesList(Mine mine){
        this.mines.add(mine);
    }
    /** Removes all obstacles from the list
     */
    @Override
    public void cleanObstacles(){
        this.obs.removeAll(getObstacles());
    }

    @Override
    public void cleanPits(){
        this.pit.removeAll(getPits());
    }

    @Override
    public void MakeSpecificObstacle(Position TopLet, Position BottomRight){
        Obstacle obstacle = new Obstacle(TopLet,BottomRight);
        MakeObsList(obstacle);
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.obs;
    }

    @Override
    public List<Pits> getPits() {
        return this.pit;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obs:this.getObstacles()) {
            if (obs.blocksPath(a,b)){
                return true;
            }
        }
        return false;
    }
}

