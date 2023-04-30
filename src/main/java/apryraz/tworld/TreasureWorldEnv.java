/**
 * ALEXANDRU CRISTIAN STOIA
 * MARC GASPÀ JOVAL
 * Treasure World
 */
package apryraz.tworld;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

import javax.swing.plaf.TreeUI;

public class TreasureWorldEnv {
  /**
   * X,Y position of Treasure and world dimension
   **/
  int TreasureX, TreasureY, WorldDim;

  /**
   * Class constructor
   *
   * @param dim dimension of the world
   * @param tx  X position of Treasure
   * @param ty  Y position of Treasure
   **/
  public TreasureWorldEnv(int dim, int tx, int ty) {
    TreasureX = tx;
    TreasureY = ty;
    WorldDim = dim;

  }

  /**
   * Process a message received by the TFinder agent,
   * by returning an appropriate answer
   * This version only process answers to moveto and detectsat messages
   *
   * @param msg message sent by the Agent
   *
   * @return a msg with the answer to return to the agent
   **/
  public AMessage acceptMessage(AMessage msg) {
    AMessage ans = new AMessage("voidmsg", "", "", "");

    msg.showMessage();
    if (msg.getComp(0).equals("moveto")) {
      int nx = Integer.parseInt(msg.getComp(1));
      int ny = Integer.parseInt(msg.getComp(2));

      if (withinLimits(nx, ny)) {
        ans = new AMessage("movedto", msg.getComp(1), msg.getComp(2), "");
      } else
        ans = new AMessage("notmovedto", msg.getComp(1), msg.getComp(2), "");

    } else if (msg.getComp(0).equals("detectsat")) {
      // YOU MUST ANSWER ALSO TO THE OTHER MESSAGE TYPE:
      // ( "detectsat", "x" , "y", "" )
      int x = Integer.parseInt(msg.getComp(1));
      int y = Integer.parseInt(msg.getComp(2));

      String value;

      // First we check if outside 9x9
      if (Math.abs(TreasureX - x) > 1 || Math.abs(TreasureY - y) > 1) {
        value = "3";
      }
      // Then we check if is in one of the 4 corners
      else if (treasureInCorners(x, y)) {
        value = "2";
      }
      // Finally if its not in the corners our outside, we can assume its inside the
      // cross
      else {
        value = "1";
      }
      ans = new AMessage("detected", msg.getComp(1), msg.getComp(2), value);
    }
    return ans;

  }

  /**
   * Given a position in the world checks if the treasure is located in the
   * corners
   * around x y
   *
   * @param x position x in the world
   * @param y position y in the world
   * @return true if is in the corners, else false
   */
  private boolean treasureInCorners(int x, int y) {
    int offX[] = { x + 1, x - 1 };
    int offY[] = { y + 1, y - 1 };

    // First checks if one of the offsets coincides with treasure x
    for (var n : offX) {
      if (n == TreasureX) {
        // If found checks if the same happens with the y
        for (var m : offY) {
          if (m == TreasureY)
            return true;
        }
      }
    }

    return false;
  }

  /**
   * Check if position x,y is within the limits of the
   * WorldDim x WorldDim world
   *
   * @param x x coordinate of agent position
   * @param y y coordinate of agent position
   *
   * @return true if (x,y) is within the limits of the world
   **/
  public boolean withinLimits(int x, int y) {

    return (x >= 1 && x <= WorldDim && y >= 1 && y <= WorldDim);
  }

}
