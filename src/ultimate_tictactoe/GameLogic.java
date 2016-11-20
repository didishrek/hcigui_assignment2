package ultimate_tictactoe;

/**
 * Created by Adrien Schricke on 19/11/2016.
 */
public class GameLogic {

    private int pointPlayer1;
    private int pointPlayer2;
    private int lastX = -1;
    private int lastY = -1;
    private boolean everywhere = true;
    private boolean finished = false;

    public GameLogic() {
        resetPoints();
    }

    public int checkWinner(int[][] table){
        //check column
            for( int i = 0 ; i < 3; ++i){
                if ((table[i][0] == table[i][1]) &&  (table[i][0] == table[i][2]) && (table[i][0] != 0)){
                    return (table[i][0]);
                }
            }
        //check line
        for( int i = 0 ; i < 3; ++i){
            if ((table[0][i] == table[1][i]) &&  (table[0][i] == table[2][i]) && (table[i][0] != 0)){
                return (table[0][i]);
            }
        }
        //left top to right bottom
        if ((table[0][0] == table[1][1]) &&  (table[0][0] == table[2][2]) && (table[0][0] != 0)){
            return (table[0][0]);
        }
        //left bottom to right top
        if ((table[0][2] == table[1][1]) &&  (table[0][2] == table[2][0]) && (table[0][2] != 0)){
            return (table[0][2]);
        }

        //full without winner
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (table[i][j] != 0)
                    count++;
            }
        }
        if (count == 9)
            return 3;
        return 0;
    }

    public void countPoint(XOBoard[][] ultimateBoard){
        resetPoints();
        int count = 0;
        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3 ; ++j){
                if (ultimateBoard[i][j].getWinner() != 0) count++;
                if (ultimateBoard[i][j].getWinner() == 1){
                    pointPlayer1++;
                } else if (ultimateBoard[i][j].getWinner() == 2){
                    pointPlayer2++;
                }
            }
        }
        if (count == 9)
            finished = true;
    }

    public void resetPoints(){
        pointPlayer1 = 0;
        pointPlayer2 = 0;
    }

    public int getUltimateWinner(){
        if (pointPlayer1 > pointPlayer2)
            return 1;
        else if (pointPlayer2 > pointPlayer1)
            return 2;
        else return 0;
    }

    public boolean isAuthorizedBoard(XOBoard[][] ultimateBoard, int indexX, int indexY){
        if (finished)
            return false;
        int winner = ultimateBoard[indexX][indexY].getWinner();
        if (everywhere){
            everywhere = false;
            return true;
        } else if(lastX == indexX && lastY == indexY && winner == 0){
            everywhere = false;
            return true;
        } else if (ultimateBoard[lastX][lastY].getWinner() != 0) {
            everywhere = true;
            return isAuthorizedBoard(ultimateBoard, indexX, indexY);
        }
        return false;
    }


    public void setLastXY(int lastX, int lastY) {
        this.lastX = lastX;
        this.lastY = lastY;
    }

    public void setEverywhere(boolean everywhere) {
        this.everywhere = everywhere;
    }

    public void resetGame(){
        resetPoints();
        everywhere = true;
        lastX = -1;
        lastY = -1;
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }
}
