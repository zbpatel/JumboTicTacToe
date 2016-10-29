package tictactoe.com.jumbotictactoe;

import android.graphics.Point;

/**
 * Created by Austin on 3/18/2016.
 */
public class GameVariables {
    public int gameState;
//    public int row = 0, col = 0;
    public byte turn;
    public boolean validSpot;
    public byte winner;
    public String[] names = new String[]{"Player 1", "Player 2"};
    public Point p = new Point();
}
