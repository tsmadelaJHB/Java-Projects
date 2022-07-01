package za.co.wethinkcode.server;

import java.util.List;
import java.util.Random;
import za.co.wethinkcode.server.world.World;

public class CoordinateGenerator {
    static int count=0;

    /**
     * Function coordinate
     * @param size: size of the world
     * @param world : the world
     * Return the array coordinates
     *
     * @return array of coordinates
     */
    public static int[] coordinate(int size, World world){
        Random random = new Random();
        int x,y;
        int min = size/2;
        int max = (size%2 == 0 ) ? size+1 : size;

        List<int[]> arr = World.getObstacles();
        if (arr.size() !=0 ){
            int[] b = arr.get(0);
            int xo = b[0];
            int yo = b[1];
            x = random.nextInt(max) - min;
            y = random.nextInt(max) - min;
//        System.out.println("Generated position "+x+" "+y);
//        System.out.println("my obstacles "+xo+" "+yo);
            int[] coord = {x,y};
            if(x == xo && y == yo){
                coordinate(size, world);
            }
            return coord;
        }
        else{
            x = random.nextInt(max) - min;
            y = random.nextInt(max) - min;
            int[] coord = {x,y};
            return coord;
        }
    }
}
