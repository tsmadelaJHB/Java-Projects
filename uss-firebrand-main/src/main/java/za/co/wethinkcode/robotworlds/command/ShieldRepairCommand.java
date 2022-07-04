package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.Robot;
import za.co.wethinkcode.robotworlds.WorldConfig;


public class ShieldRepairCommand extends Command {
    Shield shield;
    WorldConfig config = new WorldConfig();
    private final int timeDelay = config.getShieldRestoreDelay();

    public ShieldRepairCommand(){
        super("repair");
    }

    @Override
    public boolean execute(Robot target) {
        shield = target.getShieldhp();
        int hp = target.shieldhp.getShieldHp();

        try {
            Thread.sleep(timeDelay); // Time delay while shield repairs
        } catch (InterruptedException e){
            System.err.println(e.getMessage());
        }
            target.shieldRepair();
            target.setStatus("Shield repaired to full.");
        return true;
    }
}