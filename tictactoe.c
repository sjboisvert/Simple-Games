/* a simple implementation of tic tac toe that can be played from command line
** user plays as X and goes first, computer chooses moves at random from 
** available spaces
*/

#include <stdio.h>
#include <stdlib.h>

//game board to keep track of moves
int gameBoard[3][3];

//track number of moves
int moves = 0;

//prototypes
void greet(void);
void init(void);
int victory(void);
void draw(void);
int userMove(void);
void cpuMove(void);

int main(void)
{
    //greet player and give instructions
    greet();
    
    //initialize game board
    init();
    
    //main game loop
    while (victory() == 0)
    {
        //draw game board in current state
        draw();
        
        //prompt player for move
        if (userMove() == 1)
            break;
        
        //draw game board
        draw();
        
        //check if player has won
        if (victory() != 0)
            break;
        
        //computer makes move
        cpuMove();
    }
    //game has ended, return message to user based on outcome
    if (victory() == 1)
    {
        printf("Congratulations!!! You Win!!!\n");
        return 0;
    }
    else if (victory() == 2)
    {
        draw();
        printf("I won!!! Try again!\n");
        return 0;
    }
    else
    {
        draw();
        printf("Game was a draw! Try again!\n");
        return 0;
    }
}

//greets player and gives instructions
void greet(void)
{
    printf("Welcome to Tic-Tac-Toe!!!\nYou will play as X and go first\nEnter 1-9 to choose an open space\nEnter 0 to quit\n");
}

//initializes game board
void init(void)
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            gameBoard[i][j] = 0;
        }
    }
}

//checks if game has ended, returns 0 if game not over, 1 if user has won, 2 if computer has won, and 3 if game ends in a draw
int victory(void)
{
    //check rows
    for (int i = 0; i < 3; i++)
    {
        //if entire row has same nonzero value, a player has won
        if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != 0)
        {
            //if row values are 1, user has won
            if (gameBoard[i][0] == 1)
                return 1;
            //else computer has won
            else
                return 2;
        }
    }
    //check columns
     for (int i = 0; i < 3; i++)
    {
        //if entire row has same nonzero value, a player has won
        if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] != 0)
        {
            //if row values are 1, user has won
            if (gameBoard[0][i] == 1)
                return 1;
            //else computer has won
            else
                return 2;
        }
    }
    //check diagonals
    if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != 0)
    {
        if (gameBoard[0][0] == 1)
            return 1;
        else
            return 2;
    }
    if  (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[0][2] != 0)
    {
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

//prints game board in current state
void draw(void)
{
    //create temp array of chars to print to screen based on state of game board
    char tmp[3][3];
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (gameBoard[i][j] == 1)
                tmp[i][j] = 'X';
            else if (gameBoard[i][j] == 2)
                tmp[i][j] = 'O';
            else
                tmp[i][j] = ' ';
        }
    }
    //print to screen
    printf("\n* * * * * * *\n");
    printf("* %c | %c | %c *\n", tmp[0][0], tmp[0][1], tmp[0][2]);
    printf("*---|---|---*\n");
    printf("* %c | %c | %c *\n", tmp[1][0], tmp[1][1], tmp[1][2]);
    printf("*---|---|---*\n");
    printf("* %c | %c | %c *\n", tmp[2][0], tmp[2][1], tmp[2][2]);
    printf("* * * * * * *\n");
}

//prompts user for a move, checks if move is legal, if so, game board is updated
int userMove(void)
{
    //prompt user
    printf("Enter your move: ");
    //store input, check if valid move
    int success = 0;
    int move;
    
    //loop until user enters a valid move
    while (success == 0)
    {
        //get value from user
        scanf("%d", &move);
        //reject if not a space on the board
        if (move < 0 || move > 9)
            printf("\nInvalid move, must enter 1-9 (or 0 to quit): ");
        //else check if player quit
        else if (move == 0)
            return 1;
        //else make sure space is open
        else
        {
            //iterate through board, use k to map user's move to gameBoard
            for (int i = 0, k = 1; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //if user's move is on empty space, put an X on that space and accept move
                    if (k == move && gameBoard[i][j] == 0)
                    {
                        gameBoard[i][j] = 1;
                        moves++;
                        success = 1;
                    }
                    k++;
                }
            }
            //if move is on gameBoard but unsuccessful
            if (success == 0)
            {
                printf("\nThat space is occupied! Retry: \n");
            }
        }
    }
    return 0;
}

//computer makes a move at random
void cpuMove(void)
{
    //array to store open spaces for possible moves
    int open[9];
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
    int r = rand() % (o - 1);
    
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
    //increment moves counter
    moves++;
    //cpu announces move to player
    printf("\nMy move:\n");
}
