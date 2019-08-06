
/**
 * Class to hold info on individual tile. Initialised as a 2D array holding
 * information such as whether a tile has a mile, is marked, or is revealed.
 * 
 * @author 164870
 * @Date   (18/03/2017)
 */

public class MineTile {
    
    private boolean mined = false;
    private int minedNeighbours;
    private boolean revealed = false;
    private boolean marked = false;
    private boolean markedRevealed = false;
   
   /**
    * Constructor for MineTile
    */
   public MineTile() {
       mined = false;
       int minedNeighbours = 0;
    }

   /**
    * sets a mine
    */
    public void setMined() {
        this.mined = true;
    }
    
    /**
     * whether tile has a mine
     * 
     * @return true for mine 
     */
    public boolean isMined() {
        return mined;
    }
    
    /**
     * Increments depending on adjacent mines
     */
    public void setMinedNeighbours() {
        minedNeighbours++;
    }
    
    /**
     * How many adjacent tiles have mines
     * 
     * @return No of adjacent mines
     */
    public int getMinedNeighbours() {
        return minedNeighbours;
    }

    /**
     * reveals information on tile
     */
    public void setRevealed() {
        this.revealed = true;
    }
    
    /**
     * Whether information about tile is revealed
     * 
     * @return true if tile info is to be revealed
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     * Whether user has marked the tile
     */
    public void setMarked() {
        this.marked = true;
    }
    
    /**
     * Allows tile be be unmarked
     */
    public void resetMarked() {
        this.marked = false;
    }
    
    /**
     * Whether tile has been marked
     * 
     * @return True if tile is marked 
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Whether tile is marked incorrectly
     */
    public void setMarkedRevealed() {
        this.markedRevealed = true;
    }
    /**
     * Allows user to reset an incorrectly marked tile.
     */
    public void resetMarkedRevealed() {
        this.markedRevealed = false;
    }
    
    /**
     * Whether tile is incorrectly marked
     * 
     * @return True if tile is marked incorrectly 
     */
    public boolean isMarkedRevealed() {
        return markedRevealed;
    }
    
   
   
}

