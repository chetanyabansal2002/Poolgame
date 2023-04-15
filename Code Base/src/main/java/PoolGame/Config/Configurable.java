package PoolGame.Config;

/** An interface for all the config class */
public interface Configurable {

    /**
     * Parse a JSONObject or JSONArray
     * @param obj Either a JSONObject or JSONArray to be parsed and initialised 
     * into the appropriate class.
     * @return The configuration instance derived from the JSON
     */
    public Configurable parseJSON(Object obj);
}
