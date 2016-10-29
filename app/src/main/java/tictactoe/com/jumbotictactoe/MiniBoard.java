package tictactoe.com.jumbotictactoe;

/**
 * Created by Austin on 3/18/2016.
 */
public class MiniBoard {
    public byte[][] board = new byte[3][3];
    private int win = 0, moves = 0;

    public MiniBoard(){

    }

    public boolean placeMove(int r, int c, byte player)
    {
        board[r][c] = player;

        moves++;

        if(checkForWin(r, c, player)) {
            win = player;
            return true;
        }
        else if (moves == 9) {
            win = -1;
            return true;
        }

        return false;

    }
    public void removeMove(int r, int c) {
        board[r][c] = 0;
        win = 0;
        moves--;
    }

    public boolean checkForWin(int r, int c, byte player)
    {
        return ((board[r][(c + 1) % 3] == player && board[r][(c + 2) % 3] == player)
                || (board[(r + 1) % 3][c] == player && board[(r + 2) % 3][c] == player)
                || (r == c && board[(r + 1) % 3][(c + 1) % 3] == player && board[(r + 2) % 3][(c + 2) % 3] == player) || (2 - r == c
                && board[(r + 2) % 3][(c + 1) % 3] == player && board[(r + 1) % 3][(c + 2) % 3] == player));
    }

    public int getWin() // -1 = catscratch, 0 = unfinished, 1 = player 1, 2 =
    // player 2
    {
        return win;
    }

    public boolean isAlmostFull(int r, int c, byte player) // true if placing
    // another piece on
    // this board would
    // fill it or "win"
    // it
    {
        if (moves == 8)
            return true;
        if (checkForWin(r, c, player))
            return true;
        return false;

    }

    public int get(int r, int c)
    {
        return board[r][c];
    }

    public void reset()
    {
        for (byte e = 0; e < 9; e++)
            board[e/3][e%3] = 0;
        moves = 0;
        win = 0;
    }




}









