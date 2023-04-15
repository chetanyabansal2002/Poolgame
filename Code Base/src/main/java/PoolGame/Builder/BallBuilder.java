package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.Items.Ball.BallType;
import PoolGame.Strategy.BallPocketStrategy;

/** The builder interface that specify the methods needed to build a ball */
public interface BallBuilder {
    /** Reset the builder */
    public void reset();
    /**
     * Set the x position of the ball.
     * @param xPos The x position of the ball to be constructed.
     */
    public void setXPos(double xPos);
    /**
     * Set the y position of the ball.
     * @param yPos The y position of the ball to be constructed.
     */
    public void setYPos(double yPos);
    /**
     * Set the x velocity of the ball.
     * @param xVel The x velocity of the ball to be constructed.
     */
    public void setXVel(double xVel);
    /**
     * Set the y velocity of the ball.
     * @param yVel The y velocity of the ball to be constructed.
     */
    public void setYVel(double yVel);
    /**
     * Set the mass of the new ball.
     * @param mass The mass of the new ball to be constructed.
     */
    public void setMass(double mass);
    /**
     * Set the ball type of the new ball.
     * @param type The ball type of the new ball to be constructed.
     */
    public void setBallType(BallType type);
    /**
     * Set the action to be carried out when the new ball falls into a pocket.
     * @param action The action for the new ball to carry out.
     */
    public void setPocketAction(BallPocketStrategy action);
    /**
     * Get the ball that has been configured and start a new build.
     * @return The instance of the ball that has been configured by using the
     * setters
     */
    public Ball finaliseBuild();
}
