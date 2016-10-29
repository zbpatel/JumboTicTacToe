package tictactoe.com.jumbotictactoe;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements GlobalConstants {

    Paint painter;
    AbsoluteLayout layout;
    CanvasView gameCanvas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame();
        layout = (AbsoluteLayout) findViewById(R.id.MainLayout);
        painter = new Paint(Paint.ANTI_ALIAS_FLAG);

       // Gets the screen size in pixels, and passes it through to the CanvasView
        Display screen = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        screen.getSize(screenSize);

        // Creates a new CanvasView, which draws the game board
        gameCanvas = new CanvasView(this, screenSize.x, screenSize.y);
        this.setContentView(gameCanvas);
        gameCanvas.invalidate();

        gameCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int touchX = (int) event.getX();
                    int touchY = (int) event.getY();
                    //..event.get
                    vars.p = checkSquare(touchX, touchY);
//                    Toast.makeText(getApplicationContext(), "x: " + p.x + ",   y: " + p.y, Toast.LENGTH_SHORT).show();

                    vars.validSpot = isValidMove(vars.p.y, vars.p.x);

                    //clears the nextquadoptions array so it can later in this area be redefined
                    for (int rr = 0; rr < 3; rr++)
                        for (int cc = 0; cc < 3; cc++)
                            nextQuadOptions[rr][cc] = false;

                    if (vars.validSpot) {
                        int r = vars.p.y - (vars.p.y / 4);
                        int c = vars.p.x - (vars.p.x / 4);

                        //first condition: the miniboard corresponding to the touch is not already won/full
                        //second condition:! if the miniboard corresponding to the touch is the same as the current miniboard and it will be filled with the next placement
                        if (mainBoard[r % 3][c % 3].getWin() == 0
                                && (!(r % 3 == r / 3 && c % 3 == c / 3 && mainBoard[r % 3][c % 3].isAlmostFull(r % 3, c % 3,
                                vars.turn))))
                            nextQuadOptions[r % 3][c % 3] = true;
                        else //otherwise, all other available miniboards become true in nextquadoptions
                            for (int rr = 0; rr < 3; rr++)
                                for (int cc = 0; cc < 3; cc++)
                                    if (mainBoard[rr][cc].getWin() == 0
                                            && (rr != r % 3 || cc != c % 3)
                                            && (!(rr == r / 3 && cc == c / 3 && mainBoard[rr][cc].isAlmostFull(r % 3,
                                            c % 3, vars.turn))))
                                        nextQuadOptions[rr][cc] = true;

                    }
                    gameCanvas.invalidate();
//                    gameCanvas.draw(gameCanvas.canvasDraw);
//                    gameCanvas.

//                    gameCanvas.drawX(0, 0);

                    //Toast.makeText(getApplicationContext(), "x: " + touchX + ",   y: " + touchY, Toast.LENGTH_SHORT).show();

                    // Forces a redraw of the screen
                    // "Save the RAM, save a Tree" - Danny
//                    gameCanvas.invalidate();
                }


                return true;
            }
        });


    }

    public void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j] = new MiniBoard();
                mainBoard[i][j].reset();
                quadOptions[i][j] = false;
            }
        }
            vars.gameState = 2;

            quadOptions[1][1] = true;
            vars.turn = 1;
            vars.winner = 0;


    }

    public boolean isValidMove(int rowN, int colN) {
        int r = rowN - (rowN / 4);
        int c = colN - (colN / 4);


        //if row/col == 3 or 7 or row/col are > 10 or < 0 the move is not valid
        //Basically this reduces it to only spots on the board
        //Also, if the chosen move is not inside an available miniboard (defined by quadoptions) then it is invalid
        //Also, if the space is not empty it is invalid
        if ((vars.p.x + 1) % 4 == 0 || (vars.p.y + 1) % 4 == 0 || vars.p.x < 0 || vars.p.x > 10 || vars.p.y < 0
                || vars.p.y > 10 || !quadOptions[r / 3][c / 3] || mainBoard[r / 3][c / 3].get(r % 3, c % 3) != 0)
            return false;

        return true;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Turns an X,Y coordinate into a board position
    public Point checkSquare(int x, int y){
        Point result = new Point();

        int xCalc = (x - gameCanvas.getBoardX() - gameCanvas.getMarginSize()) / gameCanvas.getSW();
        int yCalc = (y - gameCanvas.getBoardY() - gameCanvas.getMarginSize()) / gameCanvas.getSW();

        result.set(xCalc, yCalc);

        // Doesnt adjust for margins - this occurs when we actually mess with the board

        // Return -1, -1 if the point is not on the board
        if(xCalc < 0 || xCalc > 10)
            result.set(-1, -1);
        if(yCalc < 0 || yCalc > 10)
            result.set(-1, -1);


        Toast.makeText(getApplicationContext(), "x: " + result.x + ",   y: " + result.y, Toast.LENGTH_SHORT).show();

        return result;
    }
}
