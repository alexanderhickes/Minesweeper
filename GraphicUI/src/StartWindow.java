
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Class to enable user to select game settings. These include:
 * amount of rows, columns and mines.
 * 
 * @author 164870
 * @version 30/04/2017
 */
public class StartWindow extends JFrame {
    
    private JPanel wrapper = new JPanel();
    private JPanel dataPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel rowsLabel;
    private JTextField enterRows;
    private JLabel columnsLabel;
    private JTextField enterColumns;
    private JLabel minesLabel;
    private JTextField enterMines;
    private JButton start;
    
    /**
     * Constructor for start window
     */
    public StartWindow()
    {
        super("New Game");
        createWindow();
    }
    /**
     * Creates window
     */
    private void createWindow() 
    {
        
        wrapper.setLayout(new BorderLayout());
        wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        dataPanel.setLayout(new GridLayout(3, 2, 10, 10));
        dataPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        rowsLabel = new JLabel("Rows: ");
        dataPanel.add(rowsLabel);
        enterRows = new JTextField();
        dataPanel.add(enterRows);
        columnsLabel = new JLabel("Columns: ");
        dataPanel.add(columnsLabel);
        enterColumns = new JTextField();
        dataPanel.add(enterColumns);
        minesLabel = new JLabel("Mines: ");
        dataPanel.add(minesLabel);
        enterMines = new JTextField();
        dataPanel.add(enterMines);
        
        start = new JButton("Start");
        buttonPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int r = Integer.parseInt(enterRows.getText());
                int c = Integer.parseInt(enterColumns.getText());
                int m = Integer.parseInt(enterMines.getText());
                
                GameWindow newGameWindow = new GameWindow(r, c, m);               
            }
        });
                
        wrapper.add(dataPanel, BorderLayout.NORTH);
        wrapper.add(buttonPanel, BorderLayout.SOUTH);
        
        this.getContentPane().add(wrapper, BorderLayout.NORTH);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * Closes window
     */
    public void closeOperation()
    {
        this.dispose();
    }
    
}
