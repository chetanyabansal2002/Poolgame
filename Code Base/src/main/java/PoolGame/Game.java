package PoolGame;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Config.PocketConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import PoolGame.Observable.BallObserver;
import PoolGame.Items.Scorecard;
import PoolGame.Memento.Caretaker;
import PoolGame.Memento.Memento;
import PoolGame.State.GameState;
import PoolGame.State.StationaryState;
import PoolGame.State.*;
import PoolGame.State.UndoState;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.*;
import  java.lang.Math.*;


/** The game class that runs the game */
public class Game {
    private Group root;
    private PoolTable table;
    private Scorecard scorecard;
    private boolean shownWonText = false;
    public Caretaker prevGames;
    public GameState State;
    public String CheatColour;
    private Label Time;
    private Label Score;
    private final Text winText = new Text(50, 50, "Win and Bye");

    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
    }

    /**
     * Used to reload the game when the users wants to change the levels
     * @param config- input nesscary to start the game
     */

    public void reload(ConfigReader config){
        this.setup(config);
    }

    private void setup(ConfigReader config) {
        this.prevGames = new Caretaker();
        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<PocketConfig> pocketsConf = config.getConfig().getTableConfig().getPocketsConfig().getPocketConfigs();
        this.table.setupPockets(pocketsConf);
        //this.CheatColour = "blue";


        ArrayList<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        ArrayList<BallObserver> Observers = new ArrayList<BallObserver>();
        for (Ball b:balls){
            BallObserver o = new BallObserver(b,b.getColour());
            Observers.add(o);
            b.attach(o);
        }
        
        this.scorecard = new Scorecard(Observers);


        this.table.setupBalls(balls);
        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2);
        this.winText.setY(table.getDimY() / 2);
        this.State = new StationaryState(this); 
        Memento m = this.saveMemento(); 
        this.prevGames.setInitialMemento(m); //So the program can undo when there are no mementos
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
     * Adds the buttons required for the games
     * The buttons can change the state the functionality can change
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);


        Button a = new Button("Undo");
        a.setLayoutX(720);
        a.setLayoutY(0);
        Button blue = new Button("Cheat Blue");
        blue.setLayoutX(720);
        blue.setLayoutY(120);  
        Button black = new Button("Cheat black");
        black.setLayoutX(720);
        black.setLayoutY(150);    
        Button brown= new Button("Cheat brown");
        brown.setLayoutX(720);
        brown.setLayoutY(180);    
        Button green= new Button("Cheat green");
        green.setLayoutX(720);
        green.setLayoutY(210);    
        Button orange = new Button("Cheat orange");
        orange.setLayoutX(720);
        orange.setLayoutY(240);    
        Button purple = new Button("Cheat purple");
        purple.setLayoutX(720);
        purple.setLayoutY(270);    
        Button red = new Button("Cheat red");
        red.setLayoutX(720);
        red.setLayoutY(300);    
        Button yellow = new Button("Cheat yellow");
        yellow.setLayoutX(720);
        yellow.setLayoutY(330);          
        Game gameCopy = this;





        a.setOnMouseClicked(new EventHandler<MouseEvent>() { //Creation of the undo button
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.State = new UndoState(gameCopy);  ///Changes state of the game to an undo state
                }
            }
        });
        blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                    gameCopy.CheatColour = "blue";
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        }); 

        black.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "black";
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });      
        brown.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "brown";    
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });
        green.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "green";    
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });
        orange.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "orange";    
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });
        purple.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "purple";
                gameCopy.State = new SaveCheatState(gameCopy);
                gameCopy.CheatColour = "purple";
                }
            }
        });
        red.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "red";    
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });
        yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameCopy.State.getType() == "stationary"){
                gameCopy.CheatColour = "yellow";    
                gameCopy.State = new SaveCheatState(gameCopy);
                
                }
            }
        });  
        String s = String.valueOf(this.scorecard.getScore());
        this.Score = new Label("Current Score: "+s);  
        this.Score.setLayoutX(0);
        this.Score.setLayoutX(100);
        

        

        String t = String.valueOf(this.scorecard.getTime());
        this.Time = new Label("Time: "+t);
        this.Time.setLayoutX(0);
        this.Time.setLayoutX(200);        
        groupChildren.add(this.Score);   
        groupChildren.add(Time);           

               

        groupChildren.add(a);
        
        groupChildren.add(blue);
        groupChildren.add(black);
        groupChildren.add(brown);
        groupChildren.add(green);
        groupChildren.add(orange);
        groupChildren.add(purple);
        groupChildren.add(red);
        groupChildren.add(yellow);
        
        


        
        
    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
    }

    /** Code to execute every tick. */
    public void tick() {
        this.scorecard.getTime();
        this.scorecard.UpdateTime();
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
        }
        this.State.Save();
        this.State.Cheat(CheatColour);
        
        this.State.Undo();
        this.State.checkStationary();
        this.State.checkmoving();
        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }
        String s = String.valueOf(this.scorecard.getScore());
        this.Score.setText("Current Score: "+s);  
        int x = 60;
        String t = String.valueOf(Math.floorDiv((this.scorecard.getTime()),x));
        this.Time.setText(("Time: "+t));        
        
        //System.out.println(this.scorecard.getScore());
    }
    /**
     * 
     * @return the current version of the game so the caretake can save it
     */
    public Memento saveMemento(){
        ArrayList<Ball> newBalls = new ArrayList<Ball>();
        ArrayList<BallObserver> newObservers = new ArrayList<BallObserver>();
        int newTime = this.scorecard.getTime();
        for (Ball b:this.table.getBalls()){
            Ball c = b.cloneBall();
            newBalls.add(c);
        }
        for (BallObserver bo:this.scorecard.getBallObservers()){
            BallObserver BO = bo.cloneBO();
            newObservers.add(BO);
        }
        Memento m = new Memento(newObservers, newBalls,newTime);



        return m;
    }

    /**
     * 
     * @param m- the old memento that is reloaded back into the game
     * when the user presses undo
     */

    public void loadMemento(Memento m){
       

        int i = 0;
        while (i<this.table.getBalls().size()){
            Ball b = this.table.getBalls().get(i);
            Ball c = m.getBalls().get(i);
            b.setFallcount(c.getFallCounter());
            b.setXPos(c.getXPos());
            b.setYPos(c.getYPos());
            if ((c.isDisabled() ==false) && (b.isDisabled() == true)){
                b.undisable();     
            }
            if ((c.isDisabled() ==true) && (b.isDisabled() == false)){
                b.disable();     
            }   
                     
            i = i +1;

        }
        this.scorecard.setTime(m.getTime());
        
        
        
    }
    public void setGroup(Group root){
        this.root = root;
    }
    
}
