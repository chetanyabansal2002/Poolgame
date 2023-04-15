package PoolGame.Observable;


import PoolGame.ConfigReader;
/**
 * Class responsible for tracking if the ball
 * has fallen and changing its points accordingly
 */

public class BallObserver implements Observer  {
    private int Points;
    private String colour;
    private Subject subject;

    private int Multiplier;
    /**
     * 
     * @param subject the ball that is being attached
     * @param Colour colour of the ball to calibrate the points
     */
    public BallObserver(Subject subject,String Colour){
        this.colour = Colour;
        this.Points = 0;
        
        this.subject = subject;
        if (Colour == "red"){
            this.Multiplier = 1;

        }
        if (Colour == "yellow"){
            this.Multiplier = 2;
            
        }
        if (Colour == "green"){
            this.Multiplier = 3;
            
        }
        if (Colour == "brown"){
            this.Multiplier = 4;
            
        }
        if (Colour == "blue"){
            this.Multiplier = 5;
            
        }
        if (Colour == "purple"){
            this.Multiplier = 6;
            
        }
        if (Colour == "black"){
            this.Multiplier = 7;
            
        }
        if (Colour == "orange"){
            this.Multiplier = 8;
            
        }                                                        

    }
    public int getPoints(){
        return this.Points;
    }
    public void setPoints(int p){
        this.Points = p;

    }
    /**
     * 
     * @return the shallow copy of this observer
     */
    public BallObserver cloneBO(){
       BallObserver b = new  BallObserver(this.subject, this.colour) ;
       b.setPoints(this.Points);
       return b;
        

    }
    /**
     * Responisble for changing the points
     * of the game each time there is 
     * a change of the balls position
     * can increase the points or 
     * change back to 0
     */

    @Override
    public void update() {
        if (this.Points > 0){
            this.Points = 0;
        }
        else{
            this.Points = this.Multiplier;
        }

    }
}
