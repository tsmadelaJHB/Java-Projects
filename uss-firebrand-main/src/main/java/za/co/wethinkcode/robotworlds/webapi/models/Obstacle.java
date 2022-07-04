package za.co.wethinkcode.robotworlds.webapi.models;

/***
 * Data Access Object, for obstacles. Needed for creating new records in Obstacles
 * table
 *
 * @author              fkalombo
 * @iteration           4
 * @since               2021-10-21
 */
public class Obstacle {

    private Integer ObstacleId;
    private Integer WorldID;
    private String PositionTL;
    private String PositionBR;
    private String type;

    /***
     * Creates a new obstacle with provided paramters, and returns that obstacle
     *
     * @param _worldID
     * @param _positionTL
     * @param _positionBR
     * @param _type
     * @return obstacle
     */
    public static Obstacle create(Integer _obstacleId, Integer _worldID, String _positionTL, String _positionBR, String _type) {
        Obstacle obstacle = new Obstacle();
        obstacle.setObstacleId(_obstacleId);
        obstacle.setWorldID(_worldID);
        obstacle.setPositionTL(_positionTL);
        obstacle.setPositionBR(_positionBR);
        obstacle.setType(_type);
        return obstacle;
    }

    public void setObstacleId(Integer obstacleId) { ObstacleId = obstacleId; }

    public void setWorldID(Integer worldID) {
        WorldID = worldID;
    }

    public void setPositionTL(String positionTL) {
        PositionTL = positionTL;
    }

    public void setPositionBR(String positionBR) {
        PositionBR = positionBR;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getObstacleId() {
        return ObstacleId;
    }

    public Integer getWorldID() {
        return WorldID;
    }

    public String getPositionTL() {
        return PositionTL;
    }

    public String getPositionBR() {
        return PositionBR;
    }

    public String getType() {
        return type;
    }

}
