//  board class models the game board
//  save as board.java

public class Board{
  //named constants for board dimensions
  public static final int ROWS = 3;
  public static final int COLS = 3;

  //package access
  Space[][] spaces;

  //constructor
  public Board(){
    spaces = new Space[ROWS][COLS];
    for (int row = 0; row < ROWS; row++){
      for (int col = 0; col < COLS; col++){
        spaces[row][col] = new Space(row,col);
      }
    }
  }
  //initialize spaces to empty
  public void init(){
    for (int row = 0; row < ROWS; row++){
      for (int col = 0; col < COLS; col++){
        spaces[row][col].clear();
      }
    }
  }

  //checks if game has ended
  public int victory(){
    //check ROWS
    for (int row = 0; row < ROWS; row++){
      if (spaces[row][0].status == spaces[row][1].status && spaces[row][1].status == spaces[row][2].status && spaces[row][0].status != Content.EMPTY){
        if (spaces[row][0].status == Content.X)
          return 1;
        else
          return 2;
      }
    }
    //check columns
    for (int col = 0; col < COLS; col++){
         //if entire row has same nonzero value, a player has won
         if (spaces[0][col].status == spaces[1][col].status && spaces[1][col].status == spaces[2][col].status && spaces[0][col].status != Content.EMPTY){
             //if row values are 1, user has won
             if (spaces[0][col].status == Content.X)
                 return 1;
             //else computer has won
             else
                 return 2;
         }
     }
     //check diagonals
      if (spaces[0][0].status == spaces[1][1].status && spaces[1][1].status == spaces[2][2].status && spaces[0][0].status != Content.EMPTY){
          if (spaces[0][0].status == Content.X)
              return 1;
          else
              return 2;
      }
      if  (spaces[0][2].status == spaces[1][1].status && spaces[1][1].status == spaces[2][0].status && spaces[0][2].status != Content.EMPTY){
          if (spaces[0][2].status == Content.X)
              return 1;
          else
              return 2;
      }
      //check for empty space, continue playing if so
      for (int row = 0; row < ROWS; row++){
        for (int col = 0; col < COLS; col++){
          if (spaces[row][col].status == Content.EMPTY)
            return 0;
        }
      }
      //else draw if no winner and no empty space
      return 3;
  }

  //draws board to screen
  public void draw(){
    System.out.printf("\n* * * * * * *\n");
    for (int row = 0; row < ROWS; row++){
      System.out.printf("*");
      for (int col = 0; col < COLS; col++){
        spaces[row][col].draw();
        if (col < COLS - 1)
          System.out.printf("|");
      }
      System.out.printf("*\n");
      if (row < ROWS - 1)
        System.out.println("*---|---|---*");
    }
    System.out.printf("* * * * * * *\n");
  }
}
