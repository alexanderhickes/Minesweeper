
import java.util.Random;
import java.util.function.UnaryOperator;

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
    
    private MineTile[][] minefield;
    private MineTile tileData;
    
    private int mineCount = 0;
    private boolean gameOver = false;
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
        tileData = new MineTile();
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
                            //minefield[i][j].setMine();
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
        //Check row and column is valid
        if (stepRow > rows && stepCol > columns)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX Invalid   Row Col XX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        //Check row input is valid
        if (stepRow > rows)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX   Invalid  Row   XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        //Check Column input is valid
        if (stepCol > columns)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX   Invalid  Col   XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        //if tile to be stepped on is Marked, return false
        if ((minefield[stepRow - 1][stepCol - 1].isMarkedRevealed()) && stepCount == 1)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XXX   Tile Marked   XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        else
        {
            stepCount++;
        }
        //If tile is already revealed, show message
        if (minefield[stepRow - 1][stepCol - 1].isRevealed())
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XXX Already choosen XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
            
        }       
        //If incorrect tile is stepped, end game
        else
        {
            if (minefield[stepRow - 1][stepCol - 1].isMined())
            {
                System.out.println(revealField());
                    
                System.out.println("XXXXXXXXXXXXXXXXXX");
                System.out.println("XXXXX BOOOM! XXXXX");
                System.out.println("XXXXXXXXXXXXXXXXXX");
                System.out.println("XXX Try again? XXX");
                System.out.println("XXXXXXXXXXXXXXXXXX");
                return false;
            }
            //Otherwise, reveal tile information, and proceed to 
            //uncover adjacent tiles without neighbouring mines
            else
            {
                minefield[stepRow - 1][stepCol - 1].setRevealed();
            
                if(minefield[stepRow - 1][stepCol - 1].getMinedNeighbours() == 0)
                {
                     for (int i = stepRow - 2; i <= stepRow; i++)
                     {
                        if (i >= 0 && i < rows)
                        {
                            for (int j = stepCol - 2; j <= stepCol; j++)
                            {                                 
                                if (j >= 0 && j < columns)
                                {
                                    if(minefield[i][j].getMinedNeighbours() == 0 && !minefield[i][j].isRevealed())
                                    {
                                        step(i + 1, j + 1);
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
        //Check row and column is valid
        if (row > rows && column > columns)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX Invalid   Row Col XX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        //Check row input is valid
        if (row > rows)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX   Invalid  Row   XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        //Check Column input is valid
        if (column > columns)
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX   Invalid  Col   XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        
        //Check if tile is already marked. If so, remove mark
        if (minefield[row - 1][column - 1].isMarkedRevealed())
        {
            minefield[row - 1][column - 1].resetMarkedRevealed();
            minefield[row - 1][column - 1].resetMarked();
            markCount--;           
            
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XXX  Flag  Removed  XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");           
            System.out.println("XXXXX Flags Left: XXXXX");
            System.out.println("XXXXXXXXXX " + getRemainingMines() + " XXXXXXXXXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println(".......................");
            
            //If tile was previously marked incorrectly, 
            //set game to be on again
            if (!minefield[row - 1][column - 1].isMined())
            {
                gameNotOver();
            }
            
            return true;
        }
        //If tile is already revealed, output message
        else if (minefield[row - 1][column - 1].isRevealed())
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XXX Already Choosen XXX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            return false;
        }
        else
        {
            //If tile is mined, mark tile as successful
            if (minefield[row - 1][column - 1].isMined())
            {
                minefield[row - 1][column - 1].setMarked();
                minefield[row - 1][column - 1].setMarkedRevealed();
                markCount++;
                
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("XXXX  Tile  Marked XXXX");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("XXXXX Flags Left: XXXXX");
                System.out.println("XXXXXXXXXX " + getRemainingMines() + " XXXXXXXXXX");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println(".......................");
                return true;
            }
            //if tile doesn't contain a mine, mark and set game to be over
            else
            {
                minefield[row - 1][column - 1].setMarkedRevealed();
                gameOver();               
                markCount++;    
                
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("XXXX  Tile  Marked XXXX");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("XXXXX Flags Left: XXXXX");
                System.out.println("XXXXXXXXXX " + getRemainingMines() + " XXXXXXXXXX");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println(".......................");
                return false;
                
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
        this.gameOver = true;    
    }
    public void gameNotOver() {
        this.gameOver = false;
    }
    
    /**
     * Whether wrong tile is marked
     * 
     * @return false - correct tiles marked
     *          true - incorrect tile marked
     */
    public boolean isGameOver() {
        return gameOver;
    }
    /**
     * Reveals property of the tile.
     * 
     * @return Value of the tile 
     */  
    public String revealField()
    {
        String total = "";
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {             
                if (minefield[i][j].isMined())
                {
                    total += "*";
                }
                else
                {
                    total += minefield[i][j].getMinedNeighbours();
                }                    
            }
            total += "\n";
        }
        return total;
    }
    
    /**
     * Checks if user has marked all mines correctly
     * 
     * @return false - Mines still remain
     *          true - All mines are revealed        
     */
    public boolean areAllMinesRevealed()
    {
        if (markCount == mines)
        {
            return true;
        }
        else
        {
            return false;
        }
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