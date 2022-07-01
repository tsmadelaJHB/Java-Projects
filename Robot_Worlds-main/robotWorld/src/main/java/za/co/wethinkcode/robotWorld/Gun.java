package za.co.wethinkcode.robotWorld;

public class Gun {
    private int distance;
    private int shots;
    private int max;

    public Gun(int shots){
        this.shots = shots;
        this.max = shots;
        this.distance = determineMax();
    }

    public int determineMax(){
        switch (shots){
            case 5:
                return 1;
            case 4:
                return 2;
            case 3:
                return 3;
            case 2:
                return 4;
            case 1:
                return 5;
            default:
                return 0;
        }
    }

    public int getShots() {
        return this.shots;
    }

    public void decreaseShots(){
        this.shots -= 1;
    }

    public void reload(){
        this.shots = max;
    }
}
