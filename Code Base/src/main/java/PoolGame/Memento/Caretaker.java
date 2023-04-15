package PoolGame.Memento;
import java.util.*;
/**
 * Caretake class which stores all the Mementos
 */
public class Caretaker {
    private ArrayList<Memento> MementoList = new ArrayList<Memento>(); 
    private Memento intialMemento;


    public void addMemento(Memento m){
        
        this.MementoList.add(m);
    }
    /**
     * @return returns the most recent memento
     * returns intial state if there is no menento
     */
    public Memento getMemento(){
        if (MementoList.size()==0){
            return this.intialMemento;
        }
        Memento mToReturn = this.MementoList.get(this.MementoList.size()-1);
        this.MementoList.remove(this.MementoList.size()-1);
        return mToReturn;
    }
    /**
     * 
     * @param m the intial memento that is the first game
     * state
     */
    public void setInitialMemento(Memento m){
        this.intialMemento = m;
    }
    
    
}
