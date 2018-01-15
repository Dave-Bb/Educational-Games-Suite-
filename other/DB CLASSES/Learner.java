package tgs;



public class Learner {
    
    private String name;
    private String game;
    private double average;

    public Learner(){
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the game
     */
    public String getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(String game) {
        this.game = game;
    }

    /**
     * @return the average
     */
    public double getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(double average) {
        this.average = average;
    }
    
    @Override
    public String toString() {
        return "" + getAverage();
    }
}
