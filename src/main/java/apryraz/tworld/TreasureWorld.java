package apryraz.tworld;

import java.io.IOException;
import org.sat4j.specs.*;
import org.sat4j.minisat.*;
import org.sat4j.reader.*;

/**
 * The class for the main program of the Barcenas World
 *
 **/
public class TreasureWorld {
  /**
   * Main program for TreasureWorld
   */
  static int wDim; // dimension of the world
  static int numSteps; // number of steps to perform
  static String fileSteps; // file name with sequence of steps to perform
  static int tX; // x coordinate of treasure position
  static int tY; // y coordinate of treasure position

  /**
   * This function should execute the sequence of steps stored in the file
   * fileSteps,
   * but only up to numSteps steps. Each step must be executed with function
   * runNextStep() of the BarcenasFinder agent.
   *
   * @param wDim      the dimension of world
   * @param tX        x coordinate of Barcenas position
   * @param tY        y coordinate of Barcenas position
   * @param numSteps  num of steps to perform
   * @param fileSteps file name with sequence of steps to perform
   *
   **/
  public static void runStepsSequence(int wDim, int tX, int tY,
      int numSteps, String fileSteps) throws IOException, ContradictionException, TimeoutException {
    // Make instances of TreasureFinder agent and environment object classes
    TreasureFinder TAgent;
    TreasureWorldEnv EnvAgent;

    // Set environment object, and load list of pirate positions
    EnvAgent = new TreasureWorldEnv(wDim, tX, tY);

    // load list of steps into the Finder Agent
    TAgent = new TreasureFinder(wDim, EnvAgent);
    TAgent.loadListOfSteps(numSteps, fileSteps);

    // Execute sequence of steps with the Agent
    for (int i = 0; i < numSteps; i++) {
      TAgent.runNextStep();
    }
  }

  /**
   * This function should load five arguments from the command line:
   * arg[0] = dimension of the word
   * arg[1] = x coordinate of treasure position
   * arg[2] = y coordinate of treasure position
   * arg[3] = num of steps to perform
   * arg[4] = file name with sequence of steps to perform
   **/
  public static void main(String[] args) throws ParseFormatException,
      IOException, ContradictionException, TimeoutException {
    if (args.length != 5) {
      System.err.println("Usage: TreasureWorld WorldDimension XPosTreasure YPosTreasure NumberOfSteps FileWithSteps");
    }
    wDim = Integer.parseInt(args[0]);
    tX = Integer.parseInt(args[1]);
    tY = Integer.parseInt(args[2]);
    numSteps = Integer.parseInt(args[3]);
    fileSteps = args[4];

    // Here I run a concrete example, but you should read parameters from
    // the command line, as decribed above.
    runStepsSequence(6, 3, 3, 5, "tests/steps1.txt");
    // runStepsSequence(wDim, tX, tY, numSteps, fileSteps);
  }

}
