
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class for the window which will represent the minesweeper game.
 * 
 * @author 164870
 * @version 30/04/2017
 */
public class GameWindow extends JFrame {
   
    int rows;
    int columns;
    int mines;
    
    int r;
    int c;
    
    ButtonPos buttonData[][];
    JButton tiles[][];
            
    private JMenuBar bar;
    private JMenu actionMenu;
    private JMenuItem gameBarItem;
    
    private JTextField flagsLeft;
    private JPanel infoPanel = new JPanel();
    private JPanel fieldPanel = new JPanel();
        
    Minesweeper game;
    
    private boolean gameIsWon;
    private boolean gameIsOver;
    JFrame winnerDialog = new JFrame();
    JFrame gameOverDialog = new JFrame();
    
    /**
     * Constructor for Minesweeper game window.
     * 
     * @param rows    Row of tiles 
     * @param columns Column of tiles
     * @param mines   Amount of mines
     */
    public GameWindow(int rows, int columns, int mines)
    {
        super("Mine Sweeper");          
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        flagsLeft = new JTextField(String.valueOf(mines));
        game = new Minesweeper(rows, columns, mines);
        tiles = new JButton[rows][columns];
        buttonData = new ButtonPos[rows][columns];
        createGameWindow();
    }

    /**
     * Creates game window.
     */
    public void createGameWindow() 
    {        
        infoPanel.setLayout(new FlowLayout());
        infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        infoPanel.add(flagsLeft);
        fieldPanel.setLayout(new GridLayout(rows, columns));        
        fieldPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
                 
        gameBarItem = new JMenuItem("Game");
        gameBarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                StartWindow nG = new StartWindow();
                closeOperation(); 
            }
        });
         
        actionMenu = new JMenu("New");
        actionMenu.add(gameBarItem);
        bar = new JMenuBar();
        bar.add(actionMenu);
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {       
                r = i;
                c = j;
                tiles[i][j] = new JButton();
                buttonData[i][j] = new ButtonPos(i, j);
                tiles[i][j].setPreferredSize(new Dimension(45, 45));
                tiles[i][j].setBackground(Color.GRAY);
                
                tiles[i][j].addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mousePressed(MouseEvent e) 
                    {                       
                        for (int i = 0; i < rows; i++)
                        {
                            for (int j = 0; j < columns; j++)
                            {  
                                if (tiles[i][j].equals((JButton)e.getSource()))
                                {
                                    if (SwingUtilities.isRightMouseButton(e)) 
                                    {                                        
                                        game.markTile(i, j);  
                                        
                                        if(game.areAllMinesRevealed())
                                        {
                                            if (game.isGameOver())
                                            {
                                                tiles[i][j].setForeground(Color.WHITE);
                                                revealField();
                                                JOptionPane.showMessageDialog(gameOverDialog,
                                                            "Oooops. Wrong tile marked.\n                Try again?");
                                                StartWindow nG = new StartWindow();
                                                closeOperation();                                               
                                            }
                                            else 
                                            {
                                                tiles[i][j].setForeground(Color.WHITE);
                                                revealField();
                                                JOptionPane.showMessageDialog(winnerDialog,
                                                            "                       :)\n              Play again?");
                                                StartWindow nG = new StartWindow();
                                                closeOperation();                                               
                                            }
                                        }                                        
                                        updateDisplay();                                                                            
                                    }
                                    
                                    if (SwingUtilities.isLeftMouseButton(e))
                                    {                                                                              
                                        if(game.step(i, j))
                                        {
                                            updateDisplay();
                                        }
                                        else
                                        {
                                            tiles[i][j].setBackground(Color.RED);
                                            revealField();
                                            JOptionPane.showMessageDialog(gameOverDialog,
                                                            "                   Boom.\n               Try again?"); 
                                            StartWindow nG = new StartWindow();
                                            closeOperation();
                                        } 
                                    }
                                }
                            }
                        }
                    }
                });
                           
            fieldPanel.add(tiles[i][j]);
            }
        }
                
        this.getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.getContentPane().add(fieldPanel, BorderLayout.SOUTH);
        this.setJMenuBar(bar);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);       
    }
    
    /**
     * updates all tile displays.
     */
    public void updateDisplay()
    {
        flagsLeft.setText(String.valueOf(game.getRemainingMines()));
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {                   
                if (game.isRevealed(i, j))
                {                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 0)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 1)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.BLUE);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 2)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.GREEN);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 3)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.RED);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 4)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.BLUE);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 5)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.PINK);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                     
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 6)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.cyan);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 7)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.YELLOW);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    }
                    
                    if (Integer.parseInt(game.getMinedNeighbours(i, j)) == 8)
                    {
                        tiles[i][j].setBackground(Color.LIGHT_GRAY);
                        tiles[i][j].setForeground(Color.MAGENTA);
                        tiles[i][j].setText(game.getMinedNeighbours(i, j));
                    } 
                }    
                else
                {
                    tiles[i][j].setText("");
                }
                
                if (game.isMarkedRevealed(i, j))
                {
                    tiles[i][j].setForeground(Color.WHITE);
                    tiles[i][j].setText("F");
                }
            }
        }
    }
    
    /**
     * Set game to be won.
     */
    public void setGameWon()
    {
        this.gameIsWon = true;
    }
    
    /**
     * Set game to be over.
     */
    public void setGameOver()
    {
        this.gameIsOver = true;
    }
    
    /**
     * Ends game with game over dialog.
     */
    public void gameOver()
    {
        revealField();
        JOptionPane.showMessageDialog(gameOverDialog,
                    "Boom."); 
    }
    
    /**
     * Ends game with winner dialog.
     */
    public void gameWon()
    {
        revealField();
        JOptionPane.showMessageDialog(winnerDialog,
                    "Yea boi."); 
    }
    
    /**
     * Reveals all tiles at end of game.
     */
    public void revealField()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {                    
                if (game.isMined(i, j))
                {
                    tiles[i][j].setText("*");
                }
                else
                {
                    tiles[i][j].setText(game.getMinedNeighbours(i, j));
                }
            }
        }
    }
    
    /**
     * Close window.
     */
    public void closeOperation()
    {
        this.dispose();
    }
}
    
   
            
        
    
