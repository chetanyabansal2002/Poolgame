package PoolGame.State;
/**
 * Interface for the possible game States
 */
public interface GameState {

    public void Cheat(String colour);
    public void Save();
    public void Undo();
    public void checkStationary();
    public void checkmoving();
    public String getType();

    

    
}
