package PoolGame.State;

import PoolGame.Game;
import java.util.*;
import PoolGame.Items.*;
/**
 * state that represents the state where the balls are moving
 */
public class MovingState implements GameState {
    private Game game;
    public MovingState(Game game){
        this.game = game;
    }


    @Override
    public void Cheat(String colour) {

    }

    @Override
    public void Save() {

    }

    @Override
    public void Undo() {

    }
    /**
     * Checks if the balls have stopped moving
     * Changes the state back to Stationary when they have stopped
     */
    @Override
    public void checkStationary() {
        boolean check = true;
        for (Ball b:this.game.getPoolTable().getBalls()){
            if (b.hasStopped() ==false){
                check = false;             
            }
        }
        if (check ==true){
            this.game.State = new StationaryState(this.game);
        }

    }

    @Override
    public void checkmoving() {

    }
    @Override
    public String getType(){
        return "moving";
    }    
}
