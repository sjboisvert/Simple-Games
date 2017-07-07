/*  TicTacToe to be played at command line, written in
**  object oriented style.  Based on a tutorial by 
**  Chua Hock-Chuan, found at https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
*/

import java.util.Scanner;
import java.util.Random;

public class tttMain{

  private Board board;

  private static Scanner sc = new Scanner(System.in);

  public tttMain(){

    board = new Board();

    init();

    while(board.victory() == 0){
      board.draw();

      if (userMove() == 1)
        break;

      board.draw();

      if (board.victory() != 0)
        break;

      cpuMove();
    }
    if (board.victory() == 1)
      System.out.println("Congratulations!!! You Won!!!");

    else if (board.victory() == 2){
      board.draw();
      System.out.println("I won!!! Try again!");
    }
    else{
      board.draw();
      System.out.println("Game was a draw! Try again!");
    }
  }
  public void init(){
    board.init();
    System.out.printf("Welcome to Tic-Tac-Toe!!!\nYou will play as X and go first\nEnter 1-9 to choose an open space\nEnter 0 to quit\n");
  }
  public int userMove(){
    //prompt user
    System.out.printf("Enter your move: ");
    //store input, check if valid move
    int success = 0;

     //loop until user enters a valid move
     while (success == 0){
       //get value from user
       int move = sc.nextInt();
       //reject if not a space on the board
       if (move < 0 || move > 9)
         System.out.printf("\nInvalid move, must enter 1-9 (or 0 to quit): ");
       else if (move == 0)
          return 1;
       //else check to make sure space is open
       else{
           //iterate through board, use k to map user's move to gameBoard
           for (int row = 0, k = 1; row < board.ROWS; row++){
               for (int col = 0; col < board.COLS; col++){
                   //if user's move is on empty space, put an X on that space and accept move
                   if (k == move && board.spaces[row][col].status == Content.EMPTY){
                       board.spaces[row][col].status = Content.X;
                       success = 1;
                   }
                   k++;
               }
            }
            //if move is on gameBoard but unsuccessful
            if (success == 0)
              System.out.printf("\nThat space is occupied! Retry: \n");
        }
      }
      return 0;
  }
  //computer makes a move at random
 public void cpuMove(){
     //array to store open spaces for possible moves
     int[] open = new int[9];
     //track space in gameBoard
     int k = 0;
     //track place in open array
     int o = 0;

     //iterate through array
     for (int row = 0; row < board.ROWS; row++)
     {
         for (int col = 0; col < board.COLS; col++)
         {
             //check if space is open
             if (board.spaces[row][col].status == Content.EMPTY)
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
              for (int row = 0, key = 0; row < board.ROWS; row++)
              {
                  for (int col = 0; col < board.COLS; col++)
                  {
                      if (key == open[r])
                      {
                          board.spaces[row][col].status = Content.O;
                      }
                      key++;
                  }
              }
              System.out.printf("\nMy Move:\n");
          }
  public static void main(String[] args){
    new tttMain();
  }
}
