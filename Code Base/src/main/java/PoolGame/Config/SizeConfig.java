package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class for the size configuration */
public class SizeConfig implements Configurable {
    private long x;
    private long y;

    /**
     * Initialise a config for size using a JSONObject
     * @param obj A JSONObject containing key 'x' and 'y'
     */
    public SizeConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise a config with the provided x and y dimension
     * @param dimX x-axis dimension
     * @param dimY y-axis dimension
     */
    public SizeConfig(long dimX, long dimY) {
        this.init(dimX, dimY);
    }

    private void init(long dimX, long dimY) {
        if (dimX < 0) {
            throw new IllegalArgumentException("Size of x cannot be less than 0");
        } else if (dimY < 0) {
            throw new IllegalArgumentException("Size of y cannot be less than 0");
        }
        this.x = dimX;
        this.y = dimY;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        this.init((long)json.get("x"), (long)json.get("y"));
        return this;
    }

    /**
     * Get the size in the x dimension of the object as defined in the configuration
     * @return The x dimension size of the object
     */
    public long getX() {
        return this.x;
    }

    /**
     * Get the size in the y dimension of the object as defined in the configuration
     * @return The y dimension size of the object
     */
    public long getY() {
        return this.y;
    }
}
