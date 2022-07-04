package za.co.wethinkcode.robotworlds;

public class RobotType {
    private int shields;
    private int ammo;
    private String description;
    private boolean mine;

    public RobotType(String type){
        switch (type.toLowerCase()){
            case "bomber":
                setAmmo(3);
                setShields(2);
                setMine(true);
                setDescription("This boy sets mines, but has a some limitation on ammo and shields");
                break;
            case "sniper":
                setAmmo(5);
                setShields(2);
                setMine(false);
                setDescription("This puppy is built to shoot but can't mine and has a pretty low number of shields");
                break;
            case "brute":
                setAmmo(2);
                setShields(5);
                setMine(true);
                setDescription("This beast is a tank that may have a limited mag but can eat much more damage");
                break;
        }
    }

    public int getAmmo() {
        return ammo;
    }

    public int getShields() {
        return shields;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMine() {
        return mine;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }
}
