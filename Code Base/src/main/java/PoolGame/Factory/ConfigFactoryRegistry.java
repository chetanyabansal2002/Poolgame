package PoolGame.Factory;

import java.util.*;

import PoolGame.Config.Configurable;

/** The config factory registry */
public class ConfigFactoryRegistry {

    private Map<String, ConfigFactory> registry = new HashMap<>();

    /**
     * Register the default factory for the JSON configuration
     */
    public void registerDefault() {
        this.register("Table", new TableConfigFactory());
        this.register("Balls", new BallsConfigFactory());
    }

    /**
     * Insert factory into the registry, retrievable by using the same key.
     * If the key exists, it will replace the existing factory defined.
     * @param key A `String` as the key, case sensitive
     * @param factory The factory associated with the key
     */
    public void register(String key, ConfigFactory factory) {
        // key = key.toLowerCase();
        if (!this.registry.containsKey(key)) {
            this.registry.put(key, factory);
        } else {
            this.registry.replace(key, factory);
        }
    }

    /**
     * Remove the factory associated with the key from the registry.
     * 
     * @param key A `String` that is associated to a factory.
     * @return The factory that is associated with the key, returns `null` if
     * the key was not found.
     */
    public ConfigFactory unregister(String key) {
        // key = key.toLowerCase();
        return this.registry.remove(key);
    }

    /**
     * Get the factory associated with the key.
     * @param key A key to get a factory
     * @return The factory associated with the key, null if no factory is
     * associated with the key
     */
    public ConfigFactory getFactory(String key) {
        // key = key.toLowerCase();
        return this.registry.get(key);
    }

    /**
     * Create an instance using a factory associated with the key
     * @param key The key associated with a factory
     * @param obj The object to be provided to the factory
     * @return null if the key does not exist, the return value of the factory
     * otherwise
     */
    public Configurable create(String key, Object obj) {
        ConfigFactory factory = this.getFactory(key);
        if (factory != null) {
            return factory.make(obj);
        }
        return null;
    }
}
