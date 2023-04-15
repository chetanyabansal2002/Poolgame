package PoolGame.State;

import PoolGame.Game;
/**
 * Responsible for Saving the memento
 * when the user presses the cheat button
 * so we can have a copy of the game before 
 * the button was pressed
 */
public class SaveCheatState implements GameState {
    private Game game;
    public SaveCheatState(Game game){
        this.game = game;
    }


    @Override
    public void Cheat(String colour) {

    }
   /**
    * Saves the memento and changes state to cheat
    *changes state to cheat to obey the users command
    */
    @Override
    public void Save() {
        this.game.prevGames.addMemento(this.game.saveMemento());
        this.game.State = new CheatState(this.game);

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
        return "savecheat";
    }    

}
