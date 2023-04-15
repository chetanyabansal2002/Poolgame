package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

/** Hide the ball once it falls into the pocket twice or spawn it back to its
 * original location. If there is a ball on the original location, hide the ball.
 */
public class PocketTwice implements BallPocketStrategy {

    private final int FALL_COUNTER_THRESHOLD = 2;

    public void fallIntoPocket(Game game, Ball ball) {
        ball.incrementFallCounter();
        if (ball.getFallCounter() >= FALL_COUNTER_THRESHOLD) {
            ball.disable();
        } else {
            ball.resetPosition();
            for (Ball ballB: game.getPoolTable().getBalls()) {
                if (ball.isColliding(ballB)) {
                    ball.disable();
                }
            }
        }
    }
}
