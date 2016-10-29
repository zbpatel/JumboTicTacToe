package tictactoe.com.jumbotictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.graphics.Point;
import android.widget.Toast;

/**
 * Created by DANNY AND ZAC AND ZAC AND DANNY on 2/12/2016.
 */
public class CanvasView extends View implements GlobalConstants{
    int[][] boardToDraw = new int[9][9];
    int xInt = 1, oInt = 0, nullInt = -1;

    // Canvas
    protected Canvas canvasDraw;
    // Paint
    protected Paint paintDraw;

    // Thickness of the lines that make up the main board
    private static final int MAIN_BOARD_LINE_WIDTH = 10;

    // Percentage of the screen height that is occupied by the game board
    private static final double MAIN_BOARD_SIZE = .6;

    // Screen size and width
    private int SCREEN_WIDTH = 0, SCREEN_HEIGHT = 0;
    //Constants
    int BX;
    int BW;
    int W;
    int SW;
    int MS;
    int BY;


    Context contexto;

    public CanvasView(Context context, int screenWidth, int screenHeight){
        super(context);
        SCREEN_HEIGHT = screenHeight;
        SCREEN_WIDTH = screenWidth;

        BX = SCREEN_WIDTH / 12;
        BW = SCREEN_WIDTH - 2 * BX;
        W = BW / 3;
        SW = (W / 4);
        MS = SW / 2;
        BY = (SCREEN_HEIGHT - BW) / 2;

        contexto = context;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
   paintDraw = new Paint();
        canvasDraw = canvas;


        paintDraw.setColor(Color.BLACK);
        paintDraw.setStrokeWidth(10);
      Toast.makeText(contexto, "in ondraw: " + vars.validSpot, Toast.LENGTH_SHORT).show();

        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                //draw blue rectangles
            }
        }



        if (vars.validSpot)
        {

            int drawX = vars.p.x * SW + BX + MS;
            int drawY = vars.p.y * SW + BY + MS;
            canvasDraw.drawLine(drawX, drawY, drawX + SW, drawY + SW, paintDraw);
        }



        // DONT DELETE. WILL BREAK PROGRAM
        //canvas.drawLine(40, 40, 600, 600, paintDraw);
        drawGrids();
    }


    // Draws All Grids

    // When Drawing grids, remember to account for menus at the top
    protected void drawGrids(){

        for (int i = 0; i < 9; i++)
        {
            int x = BX + MS + W * (i % 3);
            int y = BY + MS + W * (i / 3);

            drawSmallGrid(x, y, SW, W - SW);
        }

        drawSmallGrid(BX, BY, W, BW);

        //canvasDraw.drawText(SCREEN_WIDTH +", " + SCREEN_HEIGHT, 20, 20, paintDraw);
        canvasDraw.drawLine(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + 30, paintDraw);
        canvasDraw.drawLine(SCREEN_WIDTH - BX, SCREEN_HEIGHT / 2, SCREEN_WIDTH - BX, SCREEN_HEIGHT / 2 + 30, paintDraw);
        canvasDraw.drawLine(BX, SCREEN_HEIGHT / 2, BX, SCREEN_HEIGHT / 2 + 30, paintDraw);
    }

    protected void drawLargeGrid(int x, int y, int width, int height){
    // Adjusts the x and y so that they are "wrapped" around the screen
        int adjX = x % SCREEN_WIDTH, adjY = y % SCREEN_HEIGHT;

        // Horizontal Lines
        for (int i = 1; i < 3; i++){
            double startX = i * width / 3;
        }
        /*
        for (int j = 1; j <= 4; j++)
				{
					int a = (int) ((j % 2) * j + 1) / 2;
					int b = (int) ((j + 1) % 2) * j / 2;

					g.drawLine(x + (a * SW), y + (b * SW), x + (a * SW) + ((b + 1) / 2) * (W - 40), y + (b * SW)
								+ ((a + 1) / 2) * (W - 40));
				}


         */
    }

    // Draws a board from the specified coordinates to the end of the screen;
    protected void drawLargeGrid(int x, int y){
        drawLargeGrid(x, y, SCREEN_WIDTH - x, SCREEN_HEIGHT - y);
    }

    // Draws a grid
    protected void drawSmallGrid(int x, int y, int sw, int w){
        for (int j = 1; j <= 4; j++)
        {
            int a = ((j % 2) * j + 1) / 2;
            int b = ((j + 1) % 2) * j / 2;

            canvasDraw.drawLine(x + (a * sw), y + (b * sw), x + (a * sw) + ((b + 1) / 2) * (w), y + (b * sw)
                    + ((a + 1) / 2) * (w), paintDraw);
        }
    }

    //Draws an X and a O on the board, at a given board positon
    public void drawX(int x, int y){
      //        int xCalc = (x - gameCanvas.getBoardX() - gameCanvas.getMarginSize()) / gameCanvas.getSW();

        //The reverse of the code used to turn a raw X into a board x
        int screenX = (x * SW) + BX + MS;
        int screenY = (y * SW) + BY + MS;

        canvasDraw.drawRect(screenX, screenY, screenX + SW, screenY+SW, paintDraw);
    }

    public void drawO(int x, int y){

    }

    public void drawX(Point p) {
        drawX(p.x, p.y);
    }

    public void drawO(Point p) {
        drawO(p.x, p.y);
    }
    // Value return functions - for checking which tile was pressed
    public int getBoardX(){
        return BX;
    }

    public int getBoardY(){
        return BY;
    }

    public int getMarginSize(){
        return MS;
    }

    public int getSW(){
        return SW;
    }

}

