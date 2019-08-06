
import java.util.Random;
import java.util.function.UnaryOperator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Class to process game initialise and process game commands for the 
 * Mine Sweeper Assignment. Takes dimensions of a minesweeper game and 
 * amount of mines. Creates a minefield with relevant dimensions and 
 * mines and represents as "X" characters. Takes commands provided by 
 * user to reveal tiles based on neighbouring mines. User wins by marking 
 * all tiles containing mines.
 * 
 * @Author  164870
 * @Date    (18/03/2017)
 */
public class Minesweeper {

    private int rows;
    private int columns;
    private int mines;
    private int markCount;
    
    public MineTile[][] minefield;   
    
    private int mineCount = 0;
    private boolean gameOverMark = false;
    private boolean flag = false;
    private boolean gameOverStep = false;
    private int stepCount = 1;
    
    
    /**
     * Constructor for creating minefield.
     * 
     * @param rows - rows for minesweeper board.
     * @param columns - columns for minesweeper board.
     * @param mines - Amount of mines to be placed.
     */
    public Minesweeper(int rows, int columns, int mines)
    {		
        //Assign minefield dimensions 
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        
        //Initialise class to hold tile information
        minefield = new MineTile[rows][columns];
               
        //initialise arrays
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                minefield[i][j] = new MineTile();
            }
        }	       
        populate();		
    }
    
    /**
     * Sets mines and increments neighbouring tiles.
     */
    public void populate()
    {		
        //Random number generator
        Random rand = new Random();

        //Check mines haven't been exceeded		
        while (mineCount < this.mines)
        {
            int rowPos = rand.nextInt(rows);
            int columnPos = rand.nextInt(columns);

            if (!minefield[rowPos][columnPos].isMined() && !(rowPos == 0 && columnPos == 0))
            {
                mineTile(rowPos, columnPos);
                mineCount++;
            }
        }
    }

    /**
     * Mine specified tile.
     * 
     * @param row - row to be mined.
     * @param column - column to be mined.
     * @return Boolean - whether tile has a mine.
     */
    public Boolean mineTile(int row, int column)
    {
        //Case if tile has mine
        if (minefield[row][column].isMined())
        {            
            return false;
        }	
        //Case if tile is successfully mined
        else
        { 
            for (int i = row - 1; i <= row + 1; i++)
            {
                if (i >= 0 && i < rows)
                {
                    for (int j = column - 1; j <= column + 1; j++)
                    {
                        if (j >= 0 && j < columns)
                        {
                            minefield[i][j].setMinedNeighbours();
                        }
                        
                        if (i == row && j == column)
                        {
                            minefield[i][j].setMined();
                        }              
                    }          
                }
            }           
            return true;            
        }     
    }
    
    /**
     * Reveals tile without mine based on coordinate input, otherwise game is 
     * lost.
     * 
     * @param stepRow
     * @param stepCol
     * @return false - tile has a mine, and game is lost
     *          true - tile doesn't have a mine, and is revealed, along with 
     *                 appropriate neighbours.
     */
    public boolean step(int stepRow, int stepCol)
    {
        //if tile to be stepped on is Marked, return false
        if ((minefield[stepRow][stepCol].isMarkedRevealed()) && stepCount == 1)
        {                  
            return true;
        }
        else
        {
            stepCount++;
        }
        
        //If tile is already revealed, return false
        if (minefield[stepRow][stepCol].isRevealed())
        {                      
            return true;            
        }       
        
        //If incorrect tile is stepped, end game
        else
        {
            if (minefield[stepRow][stepCol].isMined())
            {
                stepGameOver();
                return false;
            }
            //Otherwise, reveal tile information, and proceed to 
            //uncover adjacent tiles without neighbouring mines
            else
            {
                minefield[stepRow][stepCol].setRevealed();
            
                if(minefield[stepRow][stepCol].getMinedNeighbours() == 0)
                {
                    for (int i = stepRow - 1; i <= stepRow + 1; i++)
                    {  
                        if (i >= 0 && i < rows)
                        {
                            for (int j = stepCol - 1; j <= stepCol + 1; j++)
                            {  
                                if (j >= 0 && j < columns)
                                {
                                    if(minefield[i][j].getMinedNeighbours() == 0 && !minefield[i][j].isRevealed())
                                    {
                                        step(i, j);
                                    }

                                    if (!minefield[i][j].isRevealed() && !minefield[i][j].isMarkedRevealed())
                                    {
                                        minefield[i][j].setRevealed();
                                    }   
                                }
                            } 
                        }
                    }   
                }     
                
            stepCount = 1;
            return true;                 
            }
        }
    }
    
    /**
     * Reveals tile with a mine, otherwise game is lost.
     * 
     * @param row
     * @param column
     * @return false - Tile doesn't contain mine, and game is lost
     *          true - Tile contains a mine, and is revealed.        
     */
    public boolean markTile(int row, int column)
    {
      
        //Check if tile is already marked. If so, remove mark
        if (minefield[row][column].isMarkedRevealed())
        {
            minefield[row][column].resetMarkedRevealed();
            minefield[row][column].resetMarked();
            markCount--;           
                     
            //If tile was previously marked incorrectly, 
            //set game to be on again
            if (!minefield[row][column].isMined())
            {
                gameNotOver();
            }            
            return true;
        }
        //If tile is already revealed, output message
        else if (minefield[row][column].isRevealed())
        {                        
            return true;
        }
        else
        {
            //If tile is mined, mark tile as successful
            if (minefield[row][column].isMined())
            {
                minefield[row][column].setMarked();
                minefield[row ][column].setMarkedRevealed();
                markCount++;                              
                return true;
            }
            //if tile doesn't contain a mine, mark and set game to be over
            else
            {
                minefield[row][column].setMarkedRevealed();
                gameOver();               
                markCount++;    
                return true;
                
            }
        }
    }

    /**
     * Get row value for unit test.
     * 
     * @return Rows
     */
    public int getRow()
    {
        return rows;
    }
    
    /**
     * Get column value for unit test.
     * 
     * @return Columns
     */
    public int getColumn()
    {
        return columns;
    }
    
    /**
     * Get remaining mines to notify user how many mines remain.
     * 
     * @return Remaining mines
     */
    public int getRemainingMines()
    {
        return mineCount - markCount;
    }
    
    /**
     * Called when wrong tile is marked
     */
    public void gameOver() {
        this.gameOverMark = true;    
    }
    
    /**
     * Called when wrong tile is unmarked
     */
    public void gameNotOver() {
        this.gameOverMark = false;
    }
    
    /**
     * Whether wrong tile is marked.
     * 
     * @return false - correct tiles marked
     *          true - incorrect tile marked
     */
    public boolean isGameOver() {
        return gameOverMark;
    }
    
    /**
     * Returns amount of neighbouring mines of a given tile.
     * 
     * @param r Row 
     * @param c Column
     * @return String representation of neighbouring mines
     */
    public String getMinedNeighbours(int r, int c) {
        return Integer.toString(minefield[r][c].getMinedNeighbours());
    }
    
    /**
     * Whether a tile is mined or not.
     * 
     * @param r Row
     * @param c Column
     * @return  false - tile doesn't have a mine
     *           true - tile has a mine 
     *         
     */
    public boolean isMined (int r, int c) {
        return minefield[r][c].isMined();
    }
           
    /**
     * Whether a tile is to be revealed as marked.
     * 
     * @param r Row
     * @param c Column
     * @return false - Tile is not marked
     *          true - Tile is marked
     *         
     */
    public boolean isMarkedRevealed (int r, int c) {
        return minefield[r][c].isMarkedRevealed();
    }
           
    /**
     * Whether tiles neighbouring mines are to be revealed or not.
     * 
     * @param r Row
     * @param c Column
     * @return false -  Tile is not revealed
     *          true - Tile is revealed
     *        
     */
    public boolean isRevealed(int r, int c) {
        return minefield[r][c].isRevealed();
    }
           
    /**
     * Reveals property of the tile.
     * 
     * @return Value of the tile 
     */  
    public boolean stepGameOver()
    {
       return this.gameOverStep = true;    
    }
    
    
    /**
     * Checks if user has marked all mines correctly
     * 
     * @return false - Mines still remain
     *          true - All mines are revealed        
     */
    public boolean areAllMinesRevealed()
    {
        return markCount == mines;       
    }
    
    
    
    /**
     *turns minefield into a string.
     * 
     * @return String - grid representation of minefield.
     */
    @Override
    public String toString()
    {
        StringBuilder total = new StringBuilder();

        for (int i = 0; i < rows; i++)
        {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < columns; j++)
            {
                if (minefield[i][j].isMarkedRevealed())
                {
                    builder.append("F");
                }
                else if (minefield[i][j].isRevealed())
                {
                    if (minefield[i][j].isMined())
                    {
                         builder.append("*");
                    }
                    else
                    {
                        builder.append(minefield[i][j].getMinedNeighbours());
                    }                    
                }
                else
                {
                    builder.append("X");
                }
            }         
            total.append(builder.toString() + "\n");
        }   

        return total.toString();
    }
}