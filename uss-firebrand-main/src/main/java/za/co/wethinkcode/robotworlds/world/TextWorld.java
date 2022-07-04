package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.maze.Maze;

public class TextWorld extends AbstractWorld{
    public TextWorld(Maze maze) {
        super(maze);
    }

    @Override
    public void showObstacles() { // Prints out location of obstacles
        System.out.print("There are obstacles: \n");
        for (Obstacle obs : getObstacles()){
            System.out.println("- At position " + obs.getTopLeftX() + "," + obs.getTopLeftY() +" " +
                    "(to "+ (obs.getBottomRightX()) + "," + (obs.getBottomRightY()) + ")");
        }
    }

    public void showPits(){
        System.out.print("There are bottomless pits: \n");
        for (Pits pit : getPits()){
            System.out.println("- At position " + pit.getTopLeftX() + "," + pit.getTopLeftY() +" " +
                    "(to "+ (pit.getBottomRightX()) + "," + (pit.getBottomRightY()) + ")");
        }
    }

    @Override
    public void showMines() {
        System.out.print("There are mines: \n");
        for (Mine mines : getMines()){
            System.out.println("- At position " + mines.getMineX() + "," + mines.getMineY());
        }
    }
}
