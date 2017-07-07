//  space class models each of the nine spaces of the game board
//  to be saved as space.java

public class Space{
  //content of space, empty, x, or o
  Content status;
  //position on grid
  int row, col;

  //constructor
  public Space(int row, int col){
    this.row = row;
    this.col = col;
    //clears content from space
    clear();
  }

  //clear space to EMPTY
  public void clear(){
    status = Content.EMPTY;
  }

  //draw to board
  public void draw(){
    switch (status){
      case X:     System.out.printf(" X "); break;
      case O:     System.out.printf(" O "); break;
      case EMPTY: System.out.printf("   "); break;
    }
  }
}
