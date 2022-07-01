package za.co.wethinkcode.client.UserInterface;

import za.co.wethinkcode.client.Display;
import za.co.wethinkcode.client.GameRunner;
import za.co.wethinkcode.client.Robot;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import javax.swing.*;


public class World extends JPanel implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    FrameNavigation navigation = new FrameNavigation();
    BufferStrategy bufferStrategy;
    Canvas canvas;
    int myX = 0;
    int myY = 0;
    boolean running = true;
    int width = 900;
    int height = 550;


    public World() {
        JPanel panel = (JPanel) userInterface.window.getContentPane();
        panel.setPreferredSize(new Dimension(1250, 750));
        canvas = new Canvas();
        canvas.setBounds(30, 90, width, height);
        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.lightGray);
        panel.add(canvas);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                PlayerKeys(evt);
            }
        });
        userInterface.window.pack();
        userInterface.window.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }


    int on = 1;
    public void run() {
        while (running = true) {
            if (on == 1) {
                Paint();
            } else {
                running = false;
                break;
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
            }
        }
    }


    public void Paint() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        Player(g);
        Obstacles(g);
        Pits(g);
        bufferStrategy.show();
    }


    protected void Player(Graphics2D g) {
        int[] pos = translate(myX, myY);
        if (userInterface.playerDetails.get(1).equals("SniperMask")) {
            g.setColor(Color.black);
        } else if (userInterface.playerDetails.get(1).equals("GunSlinger")) {
            g.setColor(Color.yellow);
        } else if (userInterface.playerDetails.get(1).equals("Akitari")) {
            g.setColor(Color.MAGENTA);
        } else {
            g.setColor(Color.blue);
        }
        g.fillOval(pos[0], pos[1], 15, 15);
    }


    protected void Obstacles(Graphics2D g) {
        if (FrameNavigation.maze.equals("RandomMaze") && FrameNavigation.temp.get(0).equals("127.0.0.1")) {
            for (int[] pos : Display.obstacles) {
                int[] newPos = translate(pos[0], pos[1]);
                g.setColor(Color.DARK_GRAY);
                g.fillRect(newPos[0], newPos[1], 15, 15);
            }
        }
    }

    protected void Pits(Graphics2D g) {
        if (FrameNavigation.temp.get(0).equals("127.0.0.1")) {
            for (int[] pos : Display.pits) {
                int[] newPos = translate(pos[0], pos[1]);
                g.setColor(Color.RED);
                g.fillRect(newPos[0], newPos[1], 15, 15);
            }
        }
    }

    protected int[] translate(int x, int y){
        return new int[]{450 + x, 275 - y};
    }


   public void PlayerKeys(KeyEvent evt) {
        if (!(Display.status.equals("RELOAD") || Display.status.equals("REPAIR"))) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                backMovements();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                forwardMovements();
            }
            if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                navigation.left();
            }
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                navigation.right();
            }
            if (evt.getKeyCode() == 65) {
                FrameNavigation.fire();
            }
            if (evt.getKeyCode() == 83) {
                FrameNavigation.reload();
            }
            if (evt.getKeyCode() == 90) {
                FrameNavigation.look();
            }
            if (evt.getKeyCode() == 88) {
                FrameNavigation.repair();
            }
            GameRunner.command0.clear();
            userInterface.updateStats();
        }
    }


    public void movePlayerButtons(String com) {
        String[] command = com.split(" ");
        if (!(Display.status.equals("RELOAD") || Display.status.equals("REPAIR") || Display.status.equals("DEAD"))) {
            if (command[0].equals("back")) {
                backMovements();
            }
            if (command[0].equals("forward")) {
                forwardMovements();
            }
            if (command[1].equals("left")) {
                navigation.left();
            }
            if (command[1].equals("right")) {
                navigation.right();
            }
            GameRunner.command0.clear();
            userInterface.updateStats();
        }
    }


    public void backMovements() {
        if (Display.Direction.equals("NORTH") && myY - 5 > -height/2 + 15) {
            String currentPos = Display.position;
            navigation.back();
            userInterface.moveY();
            myY -= currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("EAST") && myX - 5 > -width/2 + 15) {
            String currentPos = Display.position;
            navigation.back();
            userInterface.moveX();
            myX -= currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("WEST") && myX + 5 < width/2 - 15) {
            String currentPos = Display.position;
            navigation.back();
            userInterface.moveX();
            myX += currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("SOUTH") && myY + 5 < height/2 - 15){
            String currentPos = Display.position;
            navigation.back();
            userInterface.moveY();
            myY += currentPos.equals(Display.position) ? 0 : 5;
        }
    }



    public void forwardMovements() {
        if (Display.Direction.equals("SOUTH") && myY - 5 > -height/2 + 15) {
            String currentPos = Display.position;
            navigation.forward();
            userInterface.moveY();
            myY -= currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("EAST") && myX + 5 < width/2 - 15) {
            String currentPos = Display.position;
            navigation.forward();
            userInterface.moveX();
            myX += currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("WEST") && myX - 5 > -width/2 + 15) {
            String currentPos = Display.position;
            navigation.forward();
            userInterface.moveX();
            myX -= currentPos.equals(Display.position) ? 0 : 5;
        } else if (Display.Direction.equals("NORTH") && myY + 5 < height/2 - 15) {
            String currentPos = Display.position;
            navigation.forward();
            userInterface.moveY();
            myY += currentPos.equals(Display.position) ? 0 : 5;
        }
    }
}

