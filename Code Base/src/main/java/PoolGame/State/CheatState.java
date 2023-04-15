package PoolGame.State;

import PoolGame.Game;
import PoolGame.Items.*;
import java.util.*;
/**
 * the state removes the ball
 */

public class CheatState implements GameState {
    private Game game;
    public CheatState(Game game){
        this.game = game;

    }
    /**
     * @param colour the colour of the balls that must be removed
     * changes state back to stationary
     */

    @Override
    public void Cheat(String colour) {
        for (Ball b:this.game.getPoolTable().getBalls()){
            if ((b.getColour().toLowerCase() == colour.toLowerCase()) &&(b.isDisabled() ==false)){
                b.disable();               
            }
        }
        this.game.State = new StationaryState(this.game);
        

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
    public void checkmoving() {

    }
    @Override
    public String getType(){
        return "cheat";
    }
}
