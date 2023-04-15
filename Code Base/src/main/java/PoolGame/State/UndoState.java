package PoolGame.State;

import PoolGame.Game;
/**
 * State responsible for undoing the game
 */

public class UndoState implements GameState {
    private Game game;
    public UndoState(Game game){
        this.game = game;
    }


    @Override
    public void Cheat(String colour) {

    }

    @Override
    public void Save() {

    }


    @Override
    /**
     *Loads the previous memento 
     *into the game changes state to stationary
     */
    public void Undo() {
        this.game.loadMemento(this.game.prevGames.getMemento());
        this.game.State = new StationaryState(this.game);


    }

    @Override
    public void checkStationary() {

    }

    @Override
    public void checkmoving() {

    }
    @Override
    public String getType(){
        return "undo";
    }    
}
