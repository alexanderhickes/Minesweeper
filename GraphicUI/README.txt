
Minesweeper Assessment 3

Author: 164870
Date: 30/04/2017
________________________


Displaying the data:
To transition from a textual game to a GUI, Buttons were used to represent tiles. 
To do this. I had to create a new class called 'GameWindow' which would act 
as the main window to represent the minesweeper game. The constructor of 'GameWindow'
takes in 3 parameters: rows, columns and mines. These are initially provided as 10, 15
and 20. The constructor then goes on to pass these same parameters into the 'Minesweeper'
class. 

To create the tile display. parameters rows and columns are used to create a 2D
array of 'JButton' objects. There is also another class called 'buttonPos' which holds 
information on the position of the button relative in the array. method 'createGameWindow()'
is then called.

'createGameWindow()' creates a 'GridLayout' relative to the rows and columns needed. A for
loop is then used to initialise every button. As well as setting its relative index in the 
array, is it also responsible for setting the mouselisteners. Left clicks call method 'step'
in 'Minesweeper' with the parameters of the buttons indexes. if true is returned, 'step' has 
been successful and should proceed by updating the display. If 'step' returns false, a tile 
with a mine has been clicked and game should end.

This is the same for 'markTile' which is called on right clicks. when 'markTile' is called on 
a tile which is blank, tile should be set to marked. If tile was already marked, tile should 
reset mark. Conditional test should check whether max flags has been reached, and if so, 
whether flags are correctly attributed. The relevant dialog box should then be produced.
If game is still valid, display should update and either "F" or ““ should be displayed.


Editing the data:
The user can edit the data in two ways: by left/right clicking a tile. When a user left 
clicks on a tile, the row/column index of the tile are passed as parameters into the 'step' 
method located in class 'Minesweeper'. The 'step' method will then make changes to the tile via
the 'mineTile' class. These changes include the following: Revealing the tile. If the tile 
selected is not mined and is not already revealed, then set the tile to be revealed; If the tile 
is mined, set game to be over; If the tile is already revealed, Do nothing.

Right clicks pass tile parameters into 'markTile' method also located in class 'minesweeper'. 
If a tile is unrevealed, a 'F' will be placed, otherwise nothing. If a tile is already marked,
the flag will be removed. Every time a flag is placed, The counter at the top of the 'gameWindow'
decrements until 0. When at zero, a check for whether all flags are correctly allotted is made, 
and game is either won or lost.

Data editing is also present when starting a new game. By setting rows, columns and mines, the 
initial game parameters are altered, and used to build the display.


Optional Extras:
- New game option:
  To for fill the requirements of the spec, with relation to changing difficulty, I have created 
  a JMenu called 'New'. When clicked 'startWindow' appears and the current 'gameWindow' closes.
  'startWindow' allows a user to define the dimensions of the game, as well as how many mines to
  add. This gives the user full control over the difficulty of the game.  
- Flag count:
  At the top of the 'gameWindow' there is a JTextField containing the number of flags left to 
  place. This is to resemble the minesweeper game as close as possible.
- Colours:
  to resemble the original minesweeper game even more, I have changed the colours of 
  buttons to be 'GRAY'. when successfully left clicked, tile will become 'LIGHT_GRAY' and expose
  its neighbours. The text displayed resembles that of the original minesweeper, including no 
  text when no neighbours are present. No clicking a mine, the tile will go red, just like that in
  the original. Right clicking creates a white F, and is used to show the user what tiles they 
  have marked on winning/losing the game. 

  By adding these extra options, I have attempted to resemble the original game as close as possible.
  I feel I have achieved this, with the only differences being: replacing the reset button with 
  a JMenu to support change in difficulty, and replacing the reset in the original game with a flag
  count.
