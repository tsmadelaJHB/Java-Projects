package za.co.wethinkcode.robotWorld;

public class RobotType {

    private String name;
    private String shields;
    private String shots;

    public RobotType(String name) {
        this.name = name;
        fillShotShield();
    }

    public void fillShotShield(){
        switch(this.name.toLowerCase()){
            case "infant":
                this.shots = "3";
                this.shields = "5";
                break;
            case "sniper":
                this.shots = "1";
                this.shields = "8";
                break;
            case "ranger":
                this.shots = "2";
                this.shields = "5";
                break;
            case "gunner":
                this.shots = "5";
                this.shields = "6";
                break;
            default:
                this.name = "drone";
                this.shots = "4";
                this.shields = "4";
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getShields() {
        return shields;
    }

    public String getShots() {
        return shots;
    }
}
