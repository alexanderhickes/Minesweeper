
/**
 * Class to store index information of buttons which will represent tiles.
 * 
 * @author 164870
 * @version 30/04/2017
 */
public class ButtonPos {
    
    int row;
    int col;
    
    /**
     * Constructor for Class ButtonPos.
     * 
     * @param  r  Row
     * @param  c  Column
     */
    public ButtonPos(int r, int c)
    {
       this.row = r;
       this.col = c;
    }
    
    /**
     * Returns Row.
     * 
     * @return Row index 
     */
    public int getRow() {
            return row;
    }
    
    /**
     * Returns Column.
     * 
     * @return Column index 
     */
    public int getCol() {
        return col;
    }
}
