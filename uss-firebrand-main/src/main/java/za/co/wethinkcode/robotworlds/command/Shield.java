package za.co.wethinkcode.robotworlds.command;

public class Shield {
    private  int ShieldHp;

    public Shield(int ShieldHp){
        this.ShieldHp = ShieldHp;
    }

    public int getShieldHp(){
        return ShieldHp;
    }
}