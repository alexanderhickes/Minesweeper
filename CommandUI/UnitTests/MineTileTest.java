
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for MineTile class
 * 
 * @Author  164870
 * @Date    (18/03/2017)
 */
public class MineTileTest {
      
    @Test
    public void setMinedTest()
    {
        MineTile test = new MineTile();
        assertEquals(false, test.isMined());
        test.setMined();
        assertEquals(true, test.isMined());
    }
    
    @Test
    public void setMinedNeighboursTest()
    {
        MineTile test = new MineTile();
        assertEquals(0, test.getMinedNeighbours());
        test.setMinedNeighbours();
        assertEquals(1, test.getMinedNeighbours());
        
        for (int i = 1; i < 9; i++)
        {
            test.setMinedNeighbours();
        }
        assertEquals(9, test.getMinedNeighbours());
    }
    
    @Test
    public void setRevealedTest()
    {
        MineTile test = new MineTile();
        assertEquals(false, test.isRevealed());
        test.setRevealed();
        assertEquals(true, test.isRevealed());
    }
    
    @Test
    public void setMarked()
    {
        MineTile test = new MineTile();
        assertEquals(false, test.isMarked());
        test.setMarked();
        assertEquals(true, test.isMarked());
        
        test.resetMarked();
        assertEquals(false, test.isMarked());
    }
    
    @Test
    public void setMarkedRevealedTest()
    {
         MineTile test = new MineTile();
        assertEquals(false, test.isMarkedRevealed());
        test.setMarkedRevealed();
        assertEquals(true, test.isMarkedRevealed());
        
        test.resetMarkedRevealed();
        assertEquals(false, test.isMarkedRevealed());
    }
}
