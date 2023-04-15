package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.Items.Ball.BallType;
import PoolGame.Strategy.BallPocketStrategy;

/** Builder for white ball */
public class WhiteBallBuilder implements BallBuilder {
    
    private Ball ball;
    private BallType ballType = null;
    private BallPocketStrategy action = null;

    /** Initialise the builder and start a new build */
    public WhiteBallBuilder() {
        this.reset();
    }

    /**
     * Initialise a builder with the ball type and action for the new build
     * @param type The ball type the builder will build
     * @param action The action that the ball have when it falls into a pocket
     */
    public WhiteBallBuilder(BallType type, BallPocketStrategy action) {
        this.ballType = type;
        this.action = action;
        this.reset();
    }
    
    public void reset() {
        this.ball = new Ball();
        this.ball.setColour("white");
        if (ballType != null) {
            this.ball.setBallType(this.ballType);
        }
        if (this.action != null) {
            this.ball.setPocketAction(this.action);
        }
    }

    public void setXPos(double xPos) {
        this.ball.setInitialXPos(xPos);
    }

    public void setYPos(double yPos) {
        this.ball.setInitialYPos(yPos);
    }

    public void setXVel(double xVel) {
        this.ball.setInitialXVel(xVel);
    }

    public void setYVel(double yVel) {
        this.ball.setInitialYVel(yVel);
    }

    public void setMass(double mass) {
        this.ball.setMass(mass);
    }

    public void setBallType(BallType type) {
        this.ballType = type;
        this.ball.setBallType(type);
    }

    public void setPocketAction(BallPocketStrategy action) {
        this.action = action;
        this.ball.setPocketAction(action);
    }
    
    public Ball finaliseBuild() {
        Ball ball = this.ball;
        this.reset();
        return ball;
    }
}
