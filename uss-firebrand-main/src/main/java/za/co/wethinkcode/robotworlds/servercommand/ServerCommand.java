package za.co.wethinkcode.robotworlds.servercommand;

public abstract class ServerCommand {
    /** Takes in an instruction, for example "purge" and executes the command through instantiating it's Class based on
     * the case
     */
    public static void create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
//        System.out.println(args[0]);
        switch (args[0]){
            case "dump":
                Dump dump = new Dump();
                break;
            case "robots":
                Robots robots = new Robots();
                break;
            case "quit":
                Quit quit = new Quit();
                break;
            case "save":
                Save save = new Save();
                save.Handle_Save();
                break;
            case "restore":
                Restore restore = new Restore();
                restore.RestoreHandler();
                break;
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}
