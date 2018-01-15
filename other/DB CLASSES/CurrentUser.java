package tgs;



public class CurrentUser {
    private int userID;
    private int gameID;
    
    public CurrentUser(){
        
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the gameID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * @param gameID the gameID to set
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    
    @Override
    public String toString() {
        return "" + getUserID();
    }
    
}
