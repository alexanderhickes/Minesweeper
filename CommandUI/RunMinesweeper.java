
/**
 * Main class for Minesweeper game. Uses recursion to allow commands 
 * to be inputed via command line. When matching inputs are entered 
 * with relevant parameters, calls back to Minesweeper class to 
 * execute operations.
 * 
 * @author 164870
 * @Date   (14/03/2017)
 */
public class RunMinesweeper {

    Minesweeper field;
    Parser parse;

    /**
     * Constructor for Main Minesweeper class
     */
    public RunMinesweeper() 
    {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXX  MINESWEEPER  XXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXX  INSTRUCTIONS: XXXXXXXXXXXXXX");
        System.out.println("XXXX  new: Start new game            XXXX");
        System.out.println("XXXX Type: new Rows Columns Mines    XXXX");
        System.out.println("XXXX---------------------------------XXXX");
        System.out.println("XXXX  step: Blow Tile                XXXX");
        System.out.println("XXXX  Type: step Row Column          XXXX");
        System.out.println("XXXX---------------------------------XXXX");
        System.out.println("XXXX  mark: Select Tiles With Bombs  XXXX");
        System.out.println("XXXX  Type: mark Row Column          XXXX");
        System.out.println("XXXX---------------------------------XXXX");
        System.out.println("XXXX  quit: Quit Game                XXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXX  README  File Contains Clarity  XXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXX  For A new Game:  XXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXX  Type  new  XXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        parse = new Parser(); 
        
        processCommand();

    }
	 
    /**
     * Recursion loop to which reads any input to the command line.
     */
    public void processCommand()	
    {
        boolean exit = true;

        while(exit)
        {
            Command com = parse.getCommand();

            if(com.getCommand() == CommandWord.NEW) 
            {
                System.out.println(".......................");
                
                field = new Minesweeper(com.getRow(), com.getColumn(), com.getMines());
                System.out.println(field.toString());
            }
            else if(com.getCommand() == CommandWord.MARK) 
            {
                System.out.println(".......................");
                field.markTile(com.getRow(), com.getColumn());               
                System.out.println(field.toString());
                
                if (field.areAllMinesRevealed())
                {
                    if (field.isGameOver())
                    {
                        System.out.println(".......................");
                        System.out.println(".......................");
                        System.out.println(field.revealField());
                        System.out.println(".......................");
                    
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("XXXX  Unlucky Pal  XXXX");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                    }
                    else 
                    {
                        System.out.println(".......................");
                        System.out.println(".......................");
                        System.out.println(field.revealField());
                        System.out.println(".......................");
                    
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("XXX  Congrats Chap  XXX");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
                    }
                }

            }
            else if(com.getCommand() == CommandWord.STEP) 
            {
                System.out.println(".......................");
              
                if (field.step(com.getRow(), com.getColumn()))
                {
                    System.out.println(field.toString());
                }               
            }
            else if(com.getCommand() == CommandWord.QUIT) 
            {
                exit = false;
                System.out.println(".......................");
                System.out.println("Cheers for playing :)");
                break;
            }
        }      
    }

    public static void main(String[] args)
    {
        RunMinesweeper game = new RunMinesweeper();     
    }
}

