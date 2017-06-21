import java.util.Scanner;
import java.util.Random;

public class tictactoe{

  public static int[][] gameBoard = new int[3][3];

  public static int moves = 0;

  public static void main(String[] args){

    greet();

    init();

    while (victory() == 0){

      draw();

      if (userMove() == 1)
        break;

      draw();

      if (victory() != 0)
        break;

      cpuMove();

    }
    if (victory() == 1){
        System.out.printf("Congratulations!!! You Win!!!\n");
    }
    else if (victory() == 2){
        draw();
        System.out.printf("I won!!! Try again!\n");
    }
    else{
        draw();
        System.out.printf("Game was a draw! Try again!\n");
    }
  }
  public static void greet(){
      System.out.printf("Welcome to Tic-Tac-Toe!!!\nYou will play as X and go first\nEnter 1-9 to choose an open space\nEnter 0 to quit\n");
  }
  public static void init(){
      for (int i = 0; i < 3; i++)
      {
          for (int j = 0; j < 3; j++)
          {
              gameBoard[i][j] = 0;
          }
      }
  }
  //checks if game has ended, returns 0 if game not over, 1 if user has won, 2 if computer has won, and 3 if game ends in a draw
  public static int victory(){
      //check rows
      for (int i = 0; i < 3; i++){
          //if entire row has same nonzero value, a player has won
          if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != 0){
              //if row values are 1, user has won
              if (gameBoard[i][0] == 1)
                  return 1;
              //else computer has won
              else
                  return 2;
          }
      }
      //check columns
       for (int i = 0; i < 3; i++){
          //if entire row has same nonzero value, a player has won
          if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] != 0){
              //if row values are 1, user has won
              if (gameBoard[0][i] == 1)
                  return 1;
              //else computer has won
              else
                  return 2;
          }
      }
      //check diagonals
      if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != 0){
          if (gameBoard[0][0] == 1)
              return 1;
          else
              return 2;
      }
      if  (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[0][2] != 0){
          if (gameBoard[0][2] == 1)
              return 1;
          else
              return 2;
      }
      //if 9 moves have been made without a victory then game is a draw
      if (moves == 9)
          return 3;
      else
          return 0;
  }

  public static void draw(){
    //create temp array of chars to print to screen based on state of game board
    char[][] tmp = new char[3][3];
    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
            if (gameBoard[i][j] == 1)
                tmp[i][j] = 'X';
            else if (gameBoard[i][j] == 2)
                tmp[i][j] = 'O';
            else
                tmp[i][j] = ' ';
        }
    }
    //print to screen
    System.out.printf("\n* * * * * * *\n");
    System.out.printf("* %c | %c | %c *\n", tmp[0][0], tmp[0][1], tmp[0][2]);
    System.out.printf("*---|---|---*\n");
    System.out.printf("* %c | %c | %c *\n", tmp[1][0], tmp[1][1], tmp[1][2]);
    System.out.printf("*---|---|---*\n");
    System.out.printf("* %c | %c | %c *\n", tmp[2][0], tmp[2][1], tmp[2][2]);
    System.out.printf("* * * * * * *\n");
  }
  //prompts user for a move, checks if move is legal, if so, game board is updated
  public static int userMove(){
    //prompt user
    System.out.printf("Enter your move: ");
    //store input, check if valid move
    int success = 0;

    //loop until user enters a valid move
    while (success == 0){
        //get value from user
        Scanner sc = new Scanner(System.in);
        int move = sc.nextInt();
        //reject if not a space on the board
        if (move < 0 || move > 9)
            System.out.printf("\nInvalid move, must enter 1-9 (or 0 to quit): ");
        else if (move == 0)
          return 1;
        //else check to make sure space is open
        else{
            //iterate through board, use k to map user's move to gameBoard
            for (int i = 0, k = 1; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    //if user's move is on empty space, put an X on that space and accept move
                    if (k == move && gameBoard[i][j] == 0){
                        gameBoard[i][j] = 1;
                        moves++;
                        success = 1;
                    }
                    k++;
                }
            }
            //if move is on gameBoard but unsuccessful
            if (success == 0){
              System.out.printf("\nThat space is occupied! Retry: \n");
            }
          }
        }
        return 0;
  }
  //computer makes a move at random
  public static void cpuMove(){
      //array to store open spaces for possible moves
      int[] open = new int[9];
      //track space in gameBoard
      int k = 0;
      //track place in open array
      int o = 0;

      //iterate through array
      for (int i = 0; i < 3; i++)
      {
          for (int j = 0; j < 3; j++)
          {
              //check if space is open
              if (gameBoard[i][j] == 0)
              {
                  open[o] = k;
                  o++;
                  k++;
              }
              else
                  k++;
          }
      }
      //random number %o to pick from the open spaces
      Random rand = new Random();
      int r = rand.nextInt(o - 1);

      //change value on game board
      for (int i = 0, key = 0; i < 3; i++)
      {
          for (int j = 0; j < 3; j++)
          {
              if (key == open[r])
              {
                  gameBoard[i][j] = 2;
              }
              key++;
          }
      }
      moves++;
      System.out.printf("\nMy Move:\n");
  }
}
