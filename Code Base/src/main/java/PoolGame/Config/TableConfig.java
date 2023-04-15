package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class for the table configuration */
public class TableConfig implements Configurable {
    private String colour;
    private double friction;
    private SizeConfig size;
    private PocketsConfig pockets;

    /**
     * Initialise a config for table using a JSONObject
     * @param obj A JSONObject containing required keys for table
     */
    public TableConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise a config for velocity using the provided values
     * @param colour The colour of the table as String
     * @param friction The friction of the table
     * @param sizeConf A size config instance for the size of the table
     * @param pockets A config of all the pockets
     */
    public TableConfig(String colour, double friction, SizeConfig sizeConf,PocketsConfig pockets) {
        this.init(colour, friction, sizeConf,pockets);
    }

    private void init(String colour, double friction, SizeConfig sizeConf,PocketsConfig pockets) {
        if (!ConfigChecker.colourChecker(colour)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a valid colour.", colour));
        } else if (friction <= 0) {
            throw new IllegalArgumentException("Table friction must be at least 0.");
        } else if (friction >= 1) {
            throw new IllegalArgumentException("Table friction must be smaller than 1.");
        }
        this.colour = colour;
        this.friction = friction;
        this.size = sizeConf;
        this.pockets = pockets;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        String colour = (String)json.get("colour");
        double friction = (double)json.get("friction");
        SizeConfig szConf = new SizeConfig(json.get("size"));
        PocketsConfig pockets = new PocketsConfig(json.get("pockets"));
        this.init(colour, friction, szConf,pockets);
        return this;
    }

    /**
     * Get the colour of the table as defined in the config.
     * @return The colour of the table
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * Get the friction of the table as defined in the config.
     * @return The friction of the table
     */
    public double getFriction() {
        return this.friction;
    }

    /**
     * Get the table size config instance.
     * @return The table size config instance
     */
    public SizeConfig getSizeConfig() {
        return this.size;
    }

    public PocketsConfig getPocketsConfig(){
        return this.pockets;
    }
}
