package PoolGame.Items;
import java.util.*;

import javax.xml.namespace.QName;

import PoolGame.Observable.Observer;
import PoolGame.Observable.Subject;
import PoolGame.Observable.BallObserver;
/** A pool Scorecard to keep track of score and time */
public class Scorecard {
    private int Time;
    private int Score;
    private ArrayList<BallObserver> Observers = new ArrayList<BallObserver>();

    /**
     * Build the Scorecard with the provided values
     * @param Observers List of all the ballobservers
     */

    public Scorecard(ArrayList<BallObserver> Observers){
        this.Observers = Observers;
        this.Score = 0;
        this.Time = 0;
    }
    /**
     * Update the time
     */

    public void UpdateTime(){
        this.Time = this.Time + 1;
    }
    public int getTime(){
        
        //System.out.println(this.Time);
        
        return this.Time;
    }
     /**
     * Calculates the score by adding up the scores of all the observers
     * @return the score of the Scorecard
     */
    public int getScore(){
        this.Score = 0;
        for (BallObserver b : this.Observers){
            this.Score = this.Score + b.getPoints();
            
        }
        return this.Score;
    }
    public ArrayList<BallObserver> getBallObservers(){
        return this.Observers;
    }
    public void setTime(int t){
        this.Time=t;

    }
    
}
