
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for Minesweeper Class
 *
 * @Author  164870
 * @Date    (18/03/2017)
 */
public class MinesweeperTest {
    
    private final String initial = "XXX\nXXX\nXXX\n";
    private final String initialRevealed = "000\n000\n000\n";
    
    private final String twoOne = "1*1\n111\n000\n";
    private final String twoOneandThreeThree = "1*1\n122\n01*\n";
    private final String middle = "111\n1*1\n111\n";
    
    private final String stepTestTwoOneFirst = "XXX\n111\n000\n";
    private final String stepTestTwoOneSec = "1*1\n111\n000\n";
    
    private final String markTestMiddle = "XXX\nXFX\nXXX\n";
    private final String markTestForFailure = "FXX\nXXX\nXXX\n";
    
    private final String markTestReveal = "111\n1*1\n111\n";
    
             
    //In order to test Minesweeper, make sure populate is commented 
    //out from the constructor. 
    @Test
    public void testMineInsertion()
    {
        Minesweeper testTwoOne = new Minesweeper(3, 3,1);
        testTwoOne.mineTile(0, 1);        
        assertEquals(twoOne, testTwoOne.revealField());
        
        Minesweeper testTwoOneandThreeThree = new Minesweeper(3, 3,2);
        testTwoOneandThreeThree.mineTile(0,1);
        testTwoOneandThreeThree.mineTile(2, 2);
        assertEquals(twoOneandThreeThree, testTwoOneandThreeThree.revealField());
        
        Minesweeper testMiddle = new Minesweeper(3, 3,1);
        testMiddle.mineTile(1, 1);        
        assertEquals(middle, testMiddle.revealField());
    }
    
    @Test
    public void testPopulate() 
    {
        Minesweeper testPop = new Minesweeper(10, 10, 90);
        testPop.populate();
        String s = testPop.revealField();
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                counter++;
            }
        }
        assertEquals(90, counter);
    }
    
    @Test
    public void testNew()
    {
        Minesweeper newTest = new Minesweeper(3, 3, 1);
        assertEquals(initial, newTest.toString());
        assertEquals(initialRevealed, newTest.revealField());
    }
    
    @Test
    public void testStep()
    {
        Minesweeper stepTest = new Minesweeper(3, 3, 1);
        stepTest.mineTile(0, 1);
        stepTest.step(3, 1);
        stepTest.step(3, 2);
        stepTest.step(3, 3);
        assertEquals(stepTestTwoOneFirst, stepTest.toString());
        
        Minesweeper stepTestSec = new Minesweeper(3, 3, 1);
        stepTestSec.mineTile(0, 1);
        stepTestSec.step(3, 1);
        stepTestSec.step(3, 2);
        stepTestSec.step(3, 3);
        assertEquals(stepTestTwoOneSec, stepTestSec.revealField());
    }
    
    @Test
    public void testMark()
    {
        Minesweeper markTest = new Minesweeper(3, 3, 1);
        markTest.mineTile(1, 1);
        markTest.markTile(2, 2);
        assertEquals(markTestMiddle, markTest.toString());
        
        Minesweeper markTestSec = new Minesweeper(3, 3, 1);
        markTestSec.mineTile(1, 1);
        markTestSec.markTile(2, 2);
        assertEquals(middle, markTestSec.revealField());
        
        Minesweeper markTestFail = new Minesweeper(3, 3, 1);
        markTestFail.mineTile(1, 1);
        markTestFail.markTile(1, 1);
        assertEquals(markTestForFailure, markTestFail.toString());
    }
    
    @Test
    public void testMarkRevealed()
    {
        Minesweeper markTestReveal = new Minesweeper(3, 3 , 1);
        markTestReveal.mineTile(1, 1);
        markTestReveal.markTile(1, 1);
        assertEquals(true, markTestReveal.areAllMinesRevealed());
    }
}
