package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

/** An interface for the action carried out when a ball falls into a pocket */
public interface BallPocketStrategy {
    /**
     * Action to execute when a ball fell into a pocket
     * @param game The instance of the game
     * @param ball The ball that this instance is associated to
     */
    public void fallIntoPocket(Game game, Ball ball);
}
