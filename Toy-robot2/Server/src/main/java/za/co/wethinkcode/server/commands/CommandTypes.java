package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

/**
 * Enum to run the command structure instead of a switch statement
 */

public enum CommandTypes {

    LAUNCH {
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Launch.launchJSON(arg);
        }
    },
    FORWARD {
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Movement.movementJSON(true, arg);
        }
    },
    BACK {
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Movement.movementJSON(false, arg);
        }
    },
    SPRINT{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Sprint.sprintJSON(true,arg );
        }
    },
    STATE{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return State.stateJSON(arg);
        }
    },
    TURN{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Turn.turnJSON(arg);
        }
    },
    REPLAY{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Replay.replayJSON(arg);
        }
    },
    LOOK{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Look.lookJSON(arg);
        }
    },
    FIRE{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Fire.fireJSON(arg);
        }
    },
    MINE{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return SetMine.setMineJsonObject(arg);
        }
    },
    RELOAD{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Reload.reloadJSON(arg);
        }
    },
    REPAIR{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Repair.repairJSON(arg);
        }
    },
    CLOSE{
        @Override
        public JSONObject runCommand(CommandArgument arg){
            return Close.closeJSON(arg);
        }
    };


    public abstract JSONObject runCommand(CommandArgument arg);
}
