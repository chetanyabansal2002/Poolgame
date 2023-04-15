package PoolGame.Items;

import PoolGame.Drawable;
import PoolGame.Game;
import PoolGame.Movable;
import PoolGame.Observable.Observer;
import PoolGame.Strategy.BallPocketStrategy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import PoolGame.Observable.Subject;
import PoolGame.Observable.Observer;
import java.util.*;

import java.util.ArrayList;

/**
 * The ball class for all types of balls.
 */
public class Ball implements Drawable, Movable,Subject {



    /** The type of ball */
    public enum BallType {
        /** A cue ball type */
        CUEBALL,
        /** All other ball type */
        NORMALBALL,
    };

    static final double RADIUS = 15;
    private final double HIT_FORCE_MULTIPLIER = 0.15;
    private final double MIN_VEL = 0.05;  // Minimum velocity for ball to stop
    private final double MAX_HIT_FORCE_MAG = RADIUS;  // If max vel > radius, vector calculation can be wrong or can miss hitting ball, cause each tick can add > pos of another ball


    private double[] originalPos = {0.0, 0.0};
    private double[] originalVel = {0.0, 0.0};
    private double[] vel = {0.0, 0.0};
    private double mass;
    private Color colour;
    private String Colour;
    private Circle shape;
    private Line mouseDragLine;
    private BallType type;
    private BallPocketStrategy pocketAction;
    private boolean disabled = false;




    private List<Observer> observers = new ArrayList<>();
    private int fallCounter = 0;

    /**
     * Initialise the ball based on the provided value
     * @param colour The colour of the ball
     * @param xPos The original x position of the ball
     * @param yPos The original y position of the ball
     * @param xVel The x velocity of the ball
     * @param yVel The y velocity of the ball
     * @param mass The mass of the ball
     * @param type The type of the ball
     * @param pocketAction The action to be carried out when a ball fall into a pocket
     * @throws IllegalArgumentException One of the provided value is invalid
     */
    public Ball(String colour, double xPos, double yPos, double xVel, double yVel, double mass, BallType type, BallPocketStrategy pocketAction) throws IllegalArgumentException {
        this.shape = new Circle(this.originalPos[0], this.originalPos[1], RADIUS);
        this.mouseDragLine = new Line();
        this.mouseDragLine.setVisible(false);
        this.Colour = colour;
        this.setColour(colour);
        this.setXPos(xPos);
        this.setYPos(yPos);
        this.setXVel(xVel);
        this.setYVel(yVel);
        this.vel[0] = xVel;
        this.vel[1] = yVel;
        this.setMass(mass);
        this.setBallType(type);
        this.setPocketAction(pocketAction);
    }

    /** 
     * Initialise a ball without the required values, use setter to set those
     * values
     */
    public Ball() {
        this.shape = new Circle(this.originalPos[0], this.originalPos[1], RADIUS);
        this.mouseDragLine = new Line();
        this.mouseDragLine.setVisible(false);
    }

    /**
     * Gets the radius of the ball.
     * @return The radius of the ball
     */
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get the x coordinate of the ball.
     * @return The x coordinate of the ball
     */
    public double getXPos() {
        return this.shape.getCenterX();
    }

    /**
     * Get the y coordinate of the ball.
     * @return The y coordinate of the ball
     */
    public double getYPos() {
        return this.shape.getCenterY();
    }

    /**
     * Get the velocity of the ball in the x axis.
     * @return The velocity of the ball in the x axis
     */
    public double getXVel() {
        return this.vel[0];
    }

    /**
     * Get the velocity of the ball in the y axis.
     * @return The velocity of the ball in the y axis
     */
    public double getYVel() {
        return this.vel[1];
    }

    /**
     * Get the mass of the ball.
     * @return The mass of the ball
     */
    public double getMass() {
        return this.mass;
    }

    /**
     * Get the type of the ball.
     * @return The type of the ball
     */
    public BallType getBallType() {
        return this.type;
    }

    /**
     * Get the `Node` instance of the ball.
     * @return The node instance for the ball `Shape`.
     */
    public Node getNode() {
        return this.shape;
    }

    /**
     * Get the bound of the JavaFX Node
     * @return The rectangular bound of the object
     */
    public Bounds getLocalBounds() {
        return this.shape.getBoundsInLocal();
    }

    /**
     * Get the action to be executed when a ball fell into a pocket.
     * @return The instance of the action to be executed.
     */
    public BallPocketStrategy getPocketAction() {
        return this.pocketAction;
    }

    /**
     * Get the number of times a ball has fell into a pocket.
     * @return The number of times a ball has fell into a pocket
     */
    public int getFallCounter() {
        return this.fallCounter;
    }

    /**
     * The disabled status of the ball
     * @return Whether a ball has been disabled
     */
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * Check if a ball has 0 velocity.
     * @return Returns true if a ball has 0 velocity, false otherwise
     */
    public boolean hasStopped() {
        return this.getXVel() == 0 && this.getYVel() == 0;
    }

    /**
     * Add the ball to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
        groupChildren.add(this.mouseDragLine);
    }

    public void setXVel(double xVel) {
        this.vel[0] = xVel;
    }
    public void setYVel(double yVel) {
        this.vel[1] = yVel;
    }

    /**
     * Set the colour of the ball.
     * @param colour The new colour of the ball.
     */
    public void setColour(String colour) {
        this.Colour = colour;
        this.colour = Color.valueOf(colour);
        this.shape.setFill(this.colour);
    }

    /**
     * Set the initial x coordinate of the ball. The ball will spawn at this 
     * x coordinate on reset.
     * @param xPos The initial x coordinate of the ball.
     */
    public void setInitialXPos(double xPos) {
        this.originalPos[0] = xPos;
        this.shape.setCenterX(this.originalPos[0]);
    }

    /**
     * Set the initial y coordinate of the ball. The ball will spawn at this 
     * y coordinate on reset.
     * @param yPos The initial y coordinate of the ball.
     */
    public void setInitialYPos(double yPos) {
        this.originalPos[1] = yPos;
        this.shape.setCenterY(this.originalPos[1]);
    }

    public void setXPos(double xPos) {
        this.shape.setCenterX(xPos);
    }

    public void setYPos(double yPos) {
        this.shape.setCenterY(yPos);
    }
    

    /**
     * Set the initial x velocity of the ball.
     * @param xVel The initial x velocity of the ball.
     */
    public void setInitialXVel(double xVel) {
        this.originalVel[0] = xVel;
        this.setXVel(xVel);
    }

    /**
     * Set the initial y velocity of the ball.
     * @param yVel The initial y velocity of the ball.
     */
    public void setInitialYVel(double yVel) {
        this.originalVel[1] = yVel;
        this.setYVel(yVel);
    }

    /**
     * Set the action to be carried out when this ball fall into a pocket.
     * @param action The action to be carried out
     */
    public void setPocketAction(BallPocketStrategy action) {
        this.pocketAction = action;
    }

    /**
     * Set the mass of the ball
     * @param mass The non-zero mass of the ball.
     */
    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("Mass for ball cannot be less than or equal to 0");
        }
        this.mass = mass;
    }

    /**
     * Set the ball type.
     * @param type The type of ball
     */
    public void setBallType(BallType type) {
        this.type = type;
        if (this.type == BallType.CUEBALL) {
            this.registerCueBallMouseAction();
        }
    }

    /** Increment the counter that keeps track of the number of times a ball
     * has fell into a pocket.
     */
    public void incrementFallCounter() {
        this.fallCounter++;
    }

    /** Disable the ball from carrying out any operations and hide the ball */
    public void disable() {
        this.shape.setVisible(false);
        this.disabled = true;
        this.notifyObservers();
        
    }
    public void undisable(){
        this.shape.setVisible(true);
        this.disabled = false;
        this.notifyObservers();
    }

    /** Reset a ball to its initial position and make it visible and interactable */
    public void resetPosition() {
        
        if (this.disabled == true){
        this.notifyObservers();
        }
        this.disabled = false;
        this.shape.setVisible(true);
        this.shape.setCenterX(originalPos[0]);
        this.shape.setCenterY(originalPos[1]);
        this.vel[0] = 0.0;
        this.vel[1] = 0.0;
    }

    /** Reset the velocity to its original value */
    public void resetVelocity() {
        this.setXVel(this.originalVel[0]);
        this.setYVel(this.originalVel[1]);
    }

    /** Reset the ball to its original state */
    public void reset() {
        this.resetPosition();
        this.resetVelocity();
        this.fallCounter = 0;
    }

    /**
     * Register the ball with the actions that is associated with the cue ball.
     */
    private void registerCueBallMouseAction() {
        this.shape.setOnMouseDragged(
            (actionEvent) -> {
                if (this.hasStopped()) {
                    this.mouseDragLine.setVisible(true);
                    this.mouseDragLine.setStartX(this.shape.getCenterX());
                    this.mouseDragLine.setStartY(this.shape.getCenterY());
                    this.mouseDragLine.setEndX(actionEvent.getSceneX());
                    this.mouseDragLine.setEndY(actionEvent.getSceneY());
                }
            }
        );
        this.shape.setOnMouseReleased(
            (actionEvent) -> {
                if (this.hasStopped()) {
                    this.mouseDragLine.setVisible(false);
                    Point2D vec = calculateCueBallVelOnHit(actionEvent.getSceneX(), actionEvent.getSceneY());
                    // System.out.printf("%f, %f\n", vec.getX(), vec.getY());
                    this.setXVel(vec.getX());
                    this.setYVel(vec.getY());
                }
            }
        );
    }

    /**
     * Check if both balls are colliding. Note that the checks were done using
     * rectangular bounds.
     * @param ballB Another ball to check for collision.
     * @return True if the balls are colliding, false otherwise. Will always 
     * return false if the ball is disabled or the provided ball is the same 
     * ball as the current instance.
     */
    public boolean isColliding(Ball ballB) {
        if (this == ballB || this.disabled) {
            return false;
        }
        Bounds ballABounds = this.shape.getBoundsInLocal();
        Bounds ballBBounds = ballB.shape.getBoundsInLocal();
        return ballABounds.intersects(ballBBounds);
    }

    /**
     * Calculate the velocity applied to the CueBall based on coordinates
     * relative to the CueBall. If the magnitude exceeds the max magnitude
     * defined, scale x and y velocity automatically.
     * @param cursorPosX The x coordinate for vector and magnitude calculation
     * @param cursorPosY The y coordinate for vector and magnitude calculation
     * @return A Point2D which contains the new x and y velocity
     */
    private Point2D calculateCueBallVelOnHit(double cursorPosX, double cursorPosY) {
        Point2D ballPos = new Point2D(this.shape.getCenterX(), this.shape.getCenterY());
        Point2D cursorPos = new Point2D(cursorPosX, cursorPosY);
        Point2D vector = ballPos.subtract(cursorPos).multiply(HIT_FORCE_MULTIPLIER);
        double mag = vector.magnitude();
        double excessMag = mag - MAX_HIT_FORCE_MAG;
        if (excessMag > 0) {
            double multiplier = 1.0 - (excessMag / mag);
            vector = vector.multiply(multiplier);
        }
        return vector;
    }

    /**
    * Update the balls velocity after they collide.
    * @param ballB The ball that is colliding with the current ball
    */
    public void handleCollision(Ball ballB) {
        // Code adapted from Week 06 tutorial
        //Properties of two colliding balls
        Point2D posA = new Point2D(this.getXPos(), this.getYPos());
        Point2D posB = new Point2D(ballB.getXPos(), ballB.getYPos());
        Point2D velA = new Point2D(this.getXVel(), this.getYVel());
        Point2D velB = new Point2D(ballB.getXVel(), ballB.getYVel());

        //calculate the axis of collision
        Point2D collisionVector = posB.subtract(posA);
        collisionVector = collisionVector.normalize();

        //the proportion of each balls velocity along the axis of collision
        double vA = collisionVector.dotProduct(velA);
        double vB = collisionVector.dotProduct(velB);

        //if balls are moving away from each other do nothing
        if (vA <= 0 && vB >= 0) {
            return;
        }

        double mR = ballB.getMass()/this.getMass();

        //The velocity of each ball after a collision can be found by solving the quadratic equation
        //given by equating momentum energy and energy before and after the collision and finding the
        //velocities that satisfy this
        //-(mR+1)x^2 2*(mR*vB+vA)x -((mR-1)*vB^2+2*vA*vB)=0
        //first we find the discriminant
        double a = -(mR + 1);
        double b = 2 * (mR * vB + vA);
        double c = -((mR - 1) * vB * vB + 2 * vA * vB);
        double discriminant = Math.sqrt(b * b - 4 * a * c);
        double root = (-b + discriminant)/(2 * a);

        //only one of the roots is the solution, the other pertains to the current velocities
        if (root - vB < 0.01) {
            root = (-b - discriminant)/(2 * a);
        }

        //The resulting changes in velocity for ball A and B
        Point2D deltaVA = collisionVector.multiply(mR * (vB - root));
        Point2D deltaVB = collisionVector.multiply(root - vB);

        this.setXVel(this.getXVel() + deltaVA.getX());
        this.setYVel(this.getYVel() + deltaVA.getY());
        ballB.setXVel(ballB.getXVel() + deltaVB.getX());
        ballB.setYVel(ballB.getYVel() + deltaVB.getY());
    }


    /**
     * Apply friction to the ball and stop the ball if the velocity is below
     * the specified minimum velocity.
     * @param friction The friction multiplier, 0 is no friction, 1 is max friction.
     */
    public void applyFriction(double friction) {
        double xVel = this.getXVel();
        double yVel = this.getYVel();
        if (Math.abs(xVel) + Math.abs(yVel) <= MIN_VEL) {
            this.setXVel(0);
            this.setYVel(0);
        } else {
            double xVelLoss = xVel * friction;
            this.setXVel(xVel - xVelLoss);
            double yVelLoss = yVel * friction;
            this.setYVel(yVel - yVelLoss);
        }
        // if (this.type == BallType.CUEBALL) {
        //     if (xVel != 0 || yVel != 0) {
        //         System.out.printf("%f, %f\n", this.getXVel(), this.getYVel());
        //     }
        // }
    }

    /**
     * Update the position of the ball based on their velocity.
     */
    public void move() {
        double xPos = this.shape.getCenterX() + this.vel[0];
        double yPos = this.shape.getCenterY() + this.vel[1];
        this.shape.setCenterX(xPos);
        this.shape.setCenterY(yPos);
    }
    public void setFallcount(int i){
        this.fallCounter = i;
    }

    /**
     * Triggers the ball action for falling into a pocket.
     * @param game The instance of the game
     */
    public void fallIntoPocket(Game game) {
        this.pocketAction.fallIntoPocket(game, this);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }

    }


    public String getColour(){
        return this.Colour;
    }
    public Ball cloneBall(){
        
        Ball ball = new Ball(this.Colour, this.getXPos(), this.getYPos(), 0, 0, this.mass, this.type, this.pocketAction);
        if (this.disabled == true){
            ball.disable();
        }
        //System.out.println("*******************");
        //System.out.println(this);
        //System.out.println("*******************");
        //System.out.println("/////////////////////");
        //System.out.println(ball);
        //System.out.println("/////////////////////");
        return ball;
    }
}
