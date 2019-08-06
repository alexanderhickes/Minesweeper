Aim of the game:
type command 'new', with given dimensions and mines, to create the minefield.
E.g: new 10 10 5

Use command 'step to reveal information about choosen tile, and its neighbours.
-If the tile contains a mine, game over.
-If tile doesn't contain a mine, tile is revealed with the amount of mines adjacent.
 If tile contains zero mined adjacent tiles, it is revealed as "0".
 Adjacent tiles are then searched until a tile that contains at least one neighbouring mine is identfied.
 This tile is then revealed with the amount of adjacent tiles which contain mines.
-If a tile has already been revealed, a message will appear and no operation will occur.
-If tile has been flagged, a message will appear and no operation will occur.
E.g: step 3 3

Use command 'mark' to mark a tile you suppect may contain a mine.
-tile can be marked as long as it hasnt been revealed.
-Maximum amount of flags that can be placed is equivalent to max number of mines.
 As soon as you have placed max number of flags, you will have either won or lost.
-If you mark a tile which has been previously marked, mark will be removed.
E.g: mark 5 5

Use command 'quit' to exit the game.
E.g: quit