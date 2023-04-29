package apryraz.tworld;

public class TFState {
  /**
   * TFState represents a state of the world.
   * Contains methods to incialize the world's state and set the values for it,
   * also has different useful methods to compare and print those states.
  **/

  int wDim; // World dimension
  String [][] matrix; //World representation

    /**
     * Constructor of the class
     * @param dim: Dimension of the world.
     */
  public TFState( int dim ) {
    wDim = dim;
    matrix = new String[wDim][wDim];
    initializeState();
  }

    /**
     * Initializes the matrix to '?' in all the cells
     */
  public void initializeState()
  {
      for (int i = 0; i < wDim; i++) {
          for (int j = 0; j < wDim; j++) {
              matrix[i][j] = "?";
          }
      }
  }

    /**
     * Sets a desired value to a determinate position in the world.
     * @param i: X position in the world.
     * @param j: Y position in the world.
     * @param val: Value of the (X,Y) position.
     */
  public void set( int i, int j, String val ) {

         matrix[i-1][j-1] = val;
  }

    /**
     * Compares two TFState objects to check if they are equal.
     * @param obj: TFState object to compare with the actual state.
     * @return: True if they are equal, False if not.
     */
  public boolean equals(Object obj){
       TFState tfstate2 = (TFState) obj;
       boolean status = true;

       for (int i = 0; i < wDim; i++) {
           for (int j = 0; j < wDim; j++) {
               if (! matrix[i][j].equals( tfstate2.matrix[i][j]) )
                 status = false;
           }
       }

       return status;
   }

    /**
     * Prints the state to the terminal.
     */
  public void printState()
  {
      System.out.println("FINDER => Printing Treasure world matrix");
      for (int i = wDim-1; i > -1; i--) {
          System.out.print("\t#\t");
          for (int j = 0; j < wDim; j++) {
              System.out.print(matrix[i][j] + " ");
          }
          System.out.println("\t#");
      }
  }

}
