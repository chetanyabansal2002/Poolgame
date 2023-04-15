package PoolGame.Memento;
import PoolGame.Items.*;
import PoolGame.Observable.*;
import java.util.*;
/**
 * Memento used to store the balls and the state of the game
 */

public class Memento {
    PoolTable pooltable;
    Scorecard scorecard;
    ArrayList<Ball> Balls = new ArrayList<>();
    ArrayList<BallObserver> Observers = new ArrayList<>();

    int timer;
    /**
     * 
     * @param Observers the list of observers
     * @param Balls the list of balls
     * @param timer the time passed
     */

    public Memento(ArrayList<BallObserver> Observers,ArrayList<Ball> Balls ,int timer){
        this.Observers = Observers;
        this.Balls = Balls;
        this.timer = timer;
    }

    public ArrayList<BallObserver> getObservers(){
        return this.Observers;
    }
    public ArrayList<Ball> getBalls(){
        return this.Balls;
    }
    public int getTime(){
        return this.timer;
    }
}
