import java.util.Scanner;

/**
 * Takes inputs from command line
 * 
 * @author Original from Study Direct, Modified by 164870
 * @Date   (18/03/2017)
 */
public class Parser {

    private Scanner input;
    private boolean gameOn = false;

    public Parser() {
        input = new Scanner(System.in);
    }

    /**
     * Parse the input line, converting the first word encountered into a
     * command, and then passing any further arguments that make sense.
     *
     * @return the parsed command
     */
    public Command getCommand() {
        //Initialses parser
        String inputLine = "";
        inputLine = input.nextLine();

        Scanner scanner = new Scanner(inputLine);
        //Takes first line in Command line
        if (scanner.hasNext()) {
            String str = scanner.next();
            CommandWord cw = CommandWord.getCommandWord(str);
            //If input doesn't match a Command, return Unknown
            if (cw == CommandWord.UNKNOWN) {
                return new Command(cw, "Unknown command: " + str);
            }
            //If input matches Command 'Quit', exit loop
            if (cw == CommandWord.QUIT) 
            {
                gameOn = false;
                return new Command(cw, "Bye bye");
            }
            //If input matches Command 'New', proceed to initialise minesweeper
            //game with given rows, columns and mines 
            if (cw == CommandWord.NEW) 
            {
                //if (scanner.hasNext()) 
                if (scanner.hasNextInt()) 
                {
                    int row = scanner.nextInt();
                    if (scanner.hasNextInt()) 
                    {
                        int col = scanner.nextInt();
                        if (scanner.hasNextInt()) 
                        {
                            
                            int mines = scanner.nextInt();
                            
                            if (row > 64)
                            {
                                System.out.println("......................");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("XX  Rows  Exceeded  XX");                             
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                return new Command(CommandWord.UNKNOWN, "parameters not specified");
                            }
                            
                            if(col > 64)
                            {
                                System.out.println("......................");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("XX Columns Exceeded XX");                             
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                return new Command(CommandWord.UNKNOWN, "parameters not specified");
                            }
                            
                            if(mines >= row * col)
                            {
                                System.out.println("......................");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("XX  Too Many Mines  XX");                             
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                                return new Command(CommandWord.UNKNOWN, "parameters not specified");
                            }
                            
                            gameOn = true;
                            return new Command(cw, row, col, mines);  
                                                      
                        }
                        else
                        {
                            System.out.println("....................");
                            System.out.println("XXXXXXXXXXXXXXXXXXXX");
                            System.out.println("XX Provide mines  XX");
                            System.out.println("XXXXXXXXXXXXXXXXXXXX");
                            return new Command(CommandWord.UNKNOWN, "parameters not specified");
                        }
                    }
                    else
                    {
                        System.out.println("................................");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("XX Provide Columns and mines  XX");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        return new Command(CommandWord.UNKNOWN, "parameters not specified");
                    }
                }
                else
                {
                    System.out.println("....................");
                    System.out.println("XXXXXXXXXXXXXXXXXXXX");
                    System.out.println("XX No Parameters  XX");
                    System.out.println("XXXXXXXXXXXXXXXXXXXX");
                    return new Command(CommandWord.UNKNOWN, "parameters not specified");
                }
            }		
            //If input matches Command 'Step', take inputs and call 
            //step method with given row and column parameters
            if (cw == CommandWord.STEP) 
            {
                if(gameOn)
                {
                    if (scanner.hasNextInt()) 
                    {
                        int row = scanner.nextInt();
                        if (scanner.hasNextInt()) 
                        {
                            int col = scanner.nextInt();
                            return new Command(cw, col, row);
                        }
                        else
                        {
                            System.out.println("......................");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("XX Provide Columns  XX");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                            return new Command(CommandWord.UNKNOWN, "parameters not specified");
                        }
                    }
                    else
                    {
                        System.out.println("................................");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("XX Provide Columns and Mines  XX");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        return new Command(CommandWord.UNKNOWN, "parameters not specified");
                    }
                }
                else
                {
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("XX   Start a New game first   XX");
                    
                }
            }
            //If input matches Command 'Mark', take inputs and call 
            //mark method with given row and column parameters
            if (cw == CommandWord.MARK) 
            {
                if (gameOn)
                {
                    if (scanner.hasNextInt()) 
                    {
                        int row = scanner.nextInt();
                        if (scanner.hasNextInt()) 
                        {
                            int col = scanner.nextInt();
                            return new Command(cw, col, row);
                        }
                        else
                        {
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("XX Provide columns  XX");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                            return new Command(CommandWord.UNKNOWN, "parameters not specified");
                        }
                    }
                    else
                    {
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("XX Provide Columns and mines  XX");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        return new Command(CommandWord.UNKNOWN, "parameters not specified");
                    }
                }
                else
                {
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("XX   Start a New game first   XX");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("XX  Provide Correct Command   XX");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                }
            }
            else
            {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("XX  Provide Correct Command   XX");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                return new Command(CommandWord.UNKNOWN, "parameters not specified");
            }
            

        } 
        else 
        {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("XX  Provide Correct Command   XX");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        }
        
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("XX  Provide Correct Command   XX");
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    return new Command(CommandWord.UNKNOWN, "Please tell me what to do");
    }
}


