

/**
 * Intermediate class between taking input from command line and giving
 * inputs to minesweeper object.
 * 
 * @author Original from Study Direct, Modified by 164870
 * @Date   (18/03/2017)
 */

public class Command {
    private CommandWord command;
    private int row = 0;
    private int column = 0;
    private int mines = 0;
    private String msg = "";

    /**
     * Initialise with a Command and a message (which may be empty)
     * @param command
     * @param msg
     */
    public Command(CommandWord command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    /**
     * Messages are currently only associated with "unknown" commands
     * @return the message associated with the command
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * If we have a command is 'Step' or 'Mark', instantiate with the 
     * correct row and column
     * @param command
     * @param row
     * @param column
     */

    public Command(CommandWord command, int row, int column) {
        super();
        this.command = command;
        this.row = row;
        this.column = column;
    }
    /**
     * If command is 'new', instantiate with the 
     * correct row and column and mines
     * @param new
     * @param row
     * @param column
     * @param mines
     */
    public Command(CommandWord command, int row, int column, int mines) {
        super();
        this.command = command;
        this.row = row;
        this.column = column;
        this.mines = mines;   

    }

    @Override
    public String toString() {
        return "Command " + command + ", row=" + row + ", column="
        + column;
    }

    /**
     * Principally to be used in a switch statement to decide on the
     * action to be taken, given this command
     * @return the CommandWord 
     */
    public CommandWord getCommand() {
        return command;
    }

    public void setCommand(CommandWord command) {
        this.command = command;
    }

    /**
     * Valid for commands which need a row and column value
     * @return The row value
     */
    public int getRow() {
        return row;
    }

    /**
     * Valid for commands which need a row and column value
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Valid for commands which need a row and column value
     * @return The column value
     */
    public int getColumn() {
        return column;
    }

    /**
     * Valid for commands which need a row and column value
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getMines() {
        return mines;
    }	
}
