/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package PoolGame;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import org.json.simple.parser.ParseException;

import PoolGame.ConfigReader.ConfigKeyMissingException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

/** The JavaFX application */
public class App extends Application {
    public ConfigReader easyConfig;
    public ConfigReader normalConfig;
    public ConfigReader hardConfig;

    private final double FRAMETIME = 1.0 / 60.0;

    private ConfigReader loadConfig(List<String> args) {
        String configPath;
        boolean isResourcesDir = true;
		if (args.size() > 0) {
			configPath = args.get(0);
		} else {
			// configPath = "src/main/resources/config.json";
			configPath = "/config_easy.json";
            isResourcesDir = true;
		}
		// parse the file:
        ConfigReader config = null;
        try {
            config = new ConfigReader(configPath, isResourcesDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ConfigKeyMissingException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
        return config;
    }

    @Override
    public void start(Stage stage) {
        List<String> easy_path = new ArrayList<>();
        easy_path.add("/config_easy.json");
        List<String> normal_path = new ArrayList<>();
        normal_path.add("/config_normal.json");
        List<String> hard_path = new ArrayList<>();
        hard_path.add("/config_hard.json");
        ConfigReader easyConfig = loadConfig(easy_path);
        ConfigReader normalConfig = loadConfig(normal_path);
        ConfigReader hardConfig = loadConfig(hard_path);
        this.easyConfig = easyConfig;
        this.normalConfig = normalConfig;
        this.hardConfig = hardConfig;
     


        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("PoolGame");
        stage.show();
        
        //ConfigReader config = loadConfig(getParameters().getRaw());
        
        //Game game = new Game(config);
        Game game = new Game(easyConfig);
        
        Canvas canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());

        stage.setWidth(game.getWindowDimX());
        stage.setHeight(game.getWindowDimY() +
                        Pocket.RADIUS +
                        PoolTable.POCKET_OFFSET +
                        4); // Magic number to get bottom to align


        
        this.intialiseRoot(root, canvas, game);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
        (actionEvent) -> {
                game.tick();
                
                
                
                
            });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }
    /**
     * Used to intialise the root. Seperated the method so it can be done in between the game
     * @param root - Reference to the root of the game
     * @param canvas - the canvas
     * @param game - the refernce to the game
     */

    public void intialiseRoot(Group root,Canvas canvas,Game game){
        root.getChildren().add(canvas);
        game.addDrawables(root);
        Button easyMode = new Button("Easy");
        easyMode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().clear();
                game.reload(easyConfig);
                intialiseRoot(root, canvas, game);
                
            }
        });
        easyMode.setLayoutX(720);
        easyMode.setLayoutY(30);
        root.getChildren().add(easyMode);

        Button NormalMode = new Button("Normal");
        NormalMode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().clear();
                game.reload(normalConfig);
                intialiseRoot(root, canvas, game);
                
            }
        });
        NormalMode.setLayoutX(720);
        NormalMode.setLayoutY(60);
        root.getChildren().add(NormalMode);


        Button HardMode = new Button("Hard");
        HardMode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().clear();
                game.reload(hardConfig);
                intialiseRoot(root, canvas, game);
                
            }
        });
        HardMode.setLayoutX(720);
        HardMode.setLayoutY(90);
        root.getChildren().add(HardMode);


    }




    /**
     * The entry point of the program
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}