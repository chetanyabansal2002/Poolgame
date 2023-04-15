package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

/** Hide the ball once it falls into the pocket */
public class PocketOnce implements BallPocketStrategy {
    public void fallIntoPocket(Game game, Ball ball) {
        ball.disable();
    }
}
