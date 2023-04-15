package PoolGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import PoolGame.Config.BallsConfig;
import PoolGame.Config.GameConfig;
import PoolGame.Config.TableConfig;
import PoolGame.Config.PocketsConfig;
import PoolGame.Factory.ConfigFactoryRegistry;

/** The instance that reads the config */
public class ConfigReader {

	private String path;
	private boolean isResourcesDir;
	private GameConfig config;
	
    /**
	 * Initialise the config reader with the provided value
     * @param path The path of the config file. If the file is in the `resources`
     * directory, set `isResourcesDir` to true and specify the file name
     * @param isResourcesDir Specify if the file is in the `resources` directory
     * 
	 * @throws FileNotFoundException The file path provided does not exist
	 * @throws IOException There is an I/O error while reading the configuration
	 * @throws ParseException Invalid JSON
	 * @throws ConfigKeyMissingException A required JSON key is missing from the configuration
	 * @throws IllegalArgumentException A value defined in the JSON configuration is invalid
	 */
	public ConfigReader(String path, boolean isResourcesDir) throws FileNotFoundException, IOException, ParseException, ConfigKeyMissingException, IllegalArgumentException {
		this.path = path;
		this.isResourcesDir = isResourcesDir;
		this.config = null;
		this.parse();
	}

	/**
	 * Reads the JSON file from the path defined in the instance.
	 * @return The JsonObject read from the path defined in the instance.
	 * @throws FileNotFoundException The file path provided does not exist
	 * @throws IOException There is an I/O error while reading the configuration
	 * @throws ParseException Invalid JSON
	 */
	private JSONObject readFile() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj;
		if (this.isResourcesDir) {
			obj = parser.parse(new InputStreamReader(getClass().getResourceAsStream(path)));
		} else {
			obj = parser.parse(new FileReader(path));
		}
		return (JSONObject)obj;
	}	

	/** 
	 * Parse the JSON file provided during the construction of this instance
	 * @throws FileNotFoundException The file path provided does not exist
	 * @throws IOException There is an I/O error while reading the configuration
	 * @throws ParseException Invalid JSON
	 * @throws ConfigKeyMissingException A required JSON key is missing from the configuration
	 * @throws IllegalArgumentException A value defined in the JSON configuration is invalid
	 */
	public void parse() throws FileNotFoundException, IOException, ParseException, ConfigKeyMissingException, IllegalArgumentException {
		JSONObject jsonConfig = this.readFile();
		ConfigFactoryRegistry registry = new ConfigFactoryRegistry();
		registry.registerDefault();
		
		String requiredKeys[] = {"Table", "Balls"};
		checkRequiredKey(jsonConfig, requiredKeys);
		TableConfig table = (TableConfig)registry.create(requiredKeys[0], jsonConfig.get(requiredKeys[0]));
		BallsConfig balls = (BallsConfig)registry.create(requiredKeys[1], jsonConfig.get(requiredKeys[1]));
		//PocketsConfig pockets =(PocketsConfig)registry.create(requiredKeys[2], jsonConfig.get(requiredKeys[2]));
		this.config = new GameConfig(table, balls);
	}

	private void checkRequiredKey(JSONObject jsonObj, String[] keys) throws ConfigKeyMissingException {
		for (String key : keys) {
			if (!jsonObj.containsKey(key)) {
				throw new ConfigKeyMissingException(String.format("Key \"%s\" missing.", key));
			}
		}
	}

	/**
	 * Get the file path of the configuration
	 * @return The file path of the configuration
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Get the parsed config instance
	 * @return The instance of the game config
	 */
	public GameConfig getConfig() {
		return this.config;
	}

	/**
	 * Reload the configuration from the file.
	 * @throws FileNotFoundException The file path provided does not exist
	 * @throws IOException There is an I/O error while reading the configuration
	 * @throws ParseException Invalid JSON
	 * @throws ConfigKeyMissingException A required JSON key is missing from the configuration
	 * @throws IllegalArgumentException A value defined in the JSON configuration is invalid
	 */
	public void reload() throws FileNotFoundException, IOException, ParseException, ConfigKeyMissingException, IllegalArgumentException {
		this.parse();
	}

	/** Thrown when a required key is missing from the configuration */
	public class ConfigKeyMissingException extends Exception {
		/**
		 * Initialise a new instance of the exception with a message
		 * @param arg0 The message of the exception
		 */
		public ConfigKeyMissingException(String arg0) {
			super(arg0);
		}
	}
}
