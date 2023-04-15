package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class that will contain the ball configuration */
public class BallConfig implements Configurable {
    private String colour;
    private PositionConfig position;
    private VelocityConfig velocity;
    private double mass;

    /**
     * Initialise the instance with the provided JSONObject
     * @param obj A JSONObject containing the ball configuration
     */
    public BallConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise the instance with the provided value
     * @param colour The colour of the ball as String
     * @param posConf The position config instance of the ball
     * @param velConf The velocity config instance of the ball
     * @param mass The mass of the ball
     */
    public BallConfig(String colour, PositionConfig posConf, VelocityConfig velConf, double mass) {
        this.init(colour, posConf, velConf, mass);
    }

    private void init(String colour, PositionConfig posConf, VelocityConfig velConf, double mass) {
        if(!ConfigChecker.colourChecker(colour)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a valid colour.", colour));
        } else if (mass <= 0) {
            throw new IllegalArgumentException("Mass of ball must be greater than 0");
        }
        this.colour = colour;
        this.position = posConf;
        this.velocity = velConf;
        this.mass = mass;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        String colour = (String)json.get("colour");
        PositionConfig posConf = new PositionConfig(json.get("position"));
        VelocityConfig velConf = new VelocityConfig(json.get("velocity"));
        double mass = (double)json.get("mass");
        this.init(colour, posConf, velConf, mass);
        return null;
    }

    /**
     * Get the colour of the ball as defined in the config.
     * @return The colour of the ball
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * Get the position config instance as defined in the config.
     * @return The position config instance
     */
    public PositionConfig getPositionConfig() {
        return this.position;
    }

    /**
     * Get the velocity config instance as defined in the config.
     * @return The velocity config instance
     */
    public VelocityConfig getVelocityConfig() {
        return this.velocity;
    }

    /**
     * Get the mass of the ball as defined in the config.
     * @return The mass of the ball
     */
    public double getMass() {
        return this.mass;
    }
}
