package za.co.wethinkcode.server.world;

import java.io.File;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO; // image handling functions
import java.awt.image.BufferedImage; // used to keep the image in ram for reading

public class ReadImage {
    List<int[]> maze;
    List<int[]> pits;
    int imageWidth = 37;
    int imageHeight = 60;

    public ReadImage() {
        this.pits = new ArrayList<>();
        this.maze = createMaze();
    }

    private List<int[]> createMaze() {
        BufferedImage imageData = getImage();
        return readPixels(imageData);
    }

    public List<int[]> getObstacles() {
        return this.maze;
    }

    public List<int[]> getPits(){
        return this.pits;
    }

    private BufferedImage getImage() {
        BufferedImage newImage;

        try {
            File currentDir = new File("");
            System.out.println(currentDir.getAbsolutePath());
            File f = new File("world2.png");
            String direction = f.getAbsolutePath();
            File mazeImage = new File(direction);

            newImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            newImage = ImageIO.read(mazeImage);
        } catch (IOException e) {
            System.out.println("Error reading image. Make sure world.png exists in this folder.");
            newImage = null;
        }
        return newImage;
    }

    private List<int[]> readPixels(BufferedImage data) {
        List<int[]> obstacles = new ArrayList<>();

        int[] worldPosition = { 2, 2 };
        obstacles.add(worldPosition);
//        System.out.println(Arrays.toString(obstacles.get(0)));

//        for (int x = 0; x < imageWidth; x++) {
//            for (int y = 0; y < imageHeight; y++) {
//                Color mycolor = new Color(data.getRGB(x, y));
//
//                // anything darker than grey will be considered an obstacle
//                if (mycolor.getRed() < 128 && mycolor.getBlue() < 128 && mycolor.getGreen() < 128) {
//                    int[] worldPosition = { y, x };
//                    obstacles.add(worldPosition);
//                }else if (mycolor.getRed() > 200 && mycolor.getBlue() < 128 && mycolor.getGreen() < 128) {
//                    int[] worldPosition = { y, x };
//                    pits.add(worldPosition);
//                }
//            }
//        }

        return obstacles;
    }
}
