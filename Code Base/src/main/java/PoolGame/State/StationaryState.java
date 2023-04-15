package PoolGame.State;

import PoolGame.Game;
import java.util.*;
import PoolGame.Items.*;

/**
 * State that represents the moving ball
 */
public class StationaryState implements GameState {
    Game game;
    public StationaryState(Game game){
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

    @Override
    public void checkStationary() {
        

    }

    @Override
    /**
     * check is ball is moving
     * Change state to save to save the current state if
     * the ball moves
     */
    public void checkmoving() {
        boolean check = false;
        for (Ball b:this.game.getPoolTable().getBalls()){
            if (b.hasStopped() ==false){
                check = true;             
            }
        }
        if (check ==true){
            this.game.State = new SaveState(this.game);
        }        

    }
    @Override
    public String getType(){
        return "stationary";
    }    
}
