package PoolGame.Factory;

import org.json.simple.JSONObject;

import PoolGame.Config.BallsConfig;
import PoolGame.Config.Configurable;

/** A config factory that will handle the `ball` section of the config */
public class BallsConfigFactory implements ConfigFactory {
    public Configurable make(Object jSONObject) {
        Object obj = ((JSONObject)jSONObject).get("ball");
        return new BallsConfig(obj);
    }
}
