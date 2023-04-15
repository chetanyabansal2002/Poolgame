package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class for the position configuration */
public class PositionConfig implements Configurable {
    private double x;
    private double y;

    /**
     * Initialise a config for position using a JSONObject
     * @param obj A JSONObject containing key 'x' and 'y'
     */
    public PositionConfig(Object obj) {
        this.parseJSON(obj);
    }
    /**
     * Initialise a config with the provided x and y coordinate
     * @param xPos x coordinate
     * @param yPos y coordinate
     */
    public PositionConfig(double xPos, double yPos) {
        this.init(xPos, yPos);
    }
    
    private void init(double xPos, double yPos) {
        if (xPos < 0) {
            throw new IllegalArgumentException("x position cannot be less than 0");
        } else if (yPos < 0) {
            throw new IllegalArgumentException("y position cannot be less than 0");
        }
        this.x = xPos;
        this.y = yPos;

    }
    
    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        this.init((double)json.get("x"), (double)json.get("y"));
        return this;
    }

    /**
     * Get the x coordinate of the object as defined in the config
     * @return The x coordinate of the object position
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the object as defined in the config
     * @return The y coordinate of the object position
     */
    public double getY() {
        return this.y;
    }
}
