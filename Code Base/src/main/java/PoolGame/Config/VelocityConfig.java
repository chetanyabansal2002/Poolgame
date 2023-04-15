package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class for the velocity configuration */
public class VelocityConfig implements Configurable {
    private double x;
    private double y;
    
    /**
     * Initialise a config for velocity using a JSONObject
     * @param obj A JSONObject containing key 'x' and 'y'
     */
    public VelocityConfig(Object obj) {
        parseJSON(obj);
    }

    /**
     * Initialise a config with the provided x and y velocity
     * @param velX x velocity
     * @param velY y velocity
     */
    public VelocityConfig(double velX, double velY) {
        this.init(velX, velY);
    }

    private void init(double velX, double velY) {
        this.x = velX;
        this.y = velY;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        this.init((double)json.get("x"), (double)json.get("y"));
        return this;
    }

    /**
     * Get the x-axis velocity as defined in the config
     * @return The x-axis velocity value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y-axis velocity as defined in the config
     * @return The y-axis velocity value
     */
    public double getY() {
        return this.y;
    }
}
