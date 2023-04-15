package PoolGame.Observable;
/**
 * Interface the subject must abide by
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();    
}
