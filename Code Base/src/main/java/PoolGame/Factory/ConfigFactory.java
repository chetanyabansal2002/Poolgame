package PoolGame.Factory;

import PoolGame.Config.Configurable;

/** The config factory interface for all the config factories */
public interface ConfigFactory {
    /**
     * Create the configurations based on the the provided JsonArray or JsonObject
     * @param jSONObject A JsonArray or JsonObject
     * @return The config instance parsed from the provided Json
     */
    public Configurable make(Object jSONObject);

}
