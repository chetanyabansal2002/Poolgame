package PoolGame;

/** An interface for all the object that can move */
public interface Movable {
    /**
     * Get the x coordinate of the object.
     * @return The x coordinate of the object
     */
    public double getXPos();
    /**
     * Get the y coordinate of the object.
     * @return The y coordinate of the object
     */
    public double getYPos();
    /**
     * Get the velocity of the object in the x-axis.
     * @return The velocity of the object in the x-axis
     */
    public double getXVel();
    /**
     * Get the velocity of the object in the y-axis.
     * @return The velocity of the object in the y-axis
     */
    public double getYVel();
    /**
     * Set the x coordinate of the object
     * @param xPos The new x coordinate
     */
    public void setXPos(double xPos);
    /**
     * Set the y coordinate of the object
     * @param yPos The new y coordinate
     */
    public void setYPos(double yPos);
    /**
     * Set the x velocity of the object.
     * @param xVel The new x velocity of the object.
     */
    public void setXVel(double xVel);
    /**
     * Set the y velocity of the object.
     * @param yVel The new y velocity of the object.
     */
    public void setYVel(double yVel);
    /**
     * Apply friction to the object
     * @param friction The friction to be applied
     */
    public void applyFriction(double friction);
    /** Move the object based on its current velocity */
    public void move();
}
