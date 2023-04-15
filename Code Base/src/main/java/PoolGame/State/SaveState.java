package PoolGame.State;

import PoolGame.Game;
/**
 * state responsible for saving the game memento
 */

public class SaveState implements GameState {
    private Game game;
    public SaveState(Game game){
        this.game = game;
    }


    @Override
    public void Cheat(String colour) {

    }
    

    @Override
    /**
     * Responsible for saving the game and changing state
     * to moving(State is saved when signs of moving are picked)
     */
    public void Save() {
        this.game.prevGames.addMemento(this.game.saveMemento());
        this.game.State = new MovingState(this.game);

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
        return "save";
    }    

}
