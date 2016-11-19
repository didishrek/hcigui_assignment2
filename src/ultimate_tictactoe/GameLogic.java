package ultimate_tictactoe;

/**
 * Created by Adrien Schricke on 19/11/2016.
 */
public class GameLogic {

    public int checkWinner(int[][] table){
        //check column
            for( int i = 0 ; i < 3; ++i){
                if ((table[i][0] == table[i][1]) &&  (table[i][0] == table[i][2]) && (table[i][0] != 0)){
                    System.out.println("Column " + i);
                    return (table[i][0]);
                }
            }

        //check line
        for( int i = 0 ; i < 3; ++i){
            if ((table[0][i] == table[1][i]) &&  (table[0][i] == table[2][i]) && (table[i][0] != 0)){
                System.out.println("Line " + i);
                return (table[0][i]);
            }
        }
        //check diagonal
        //left top to right bottom
        if ((table[0][0] == table[1][1]) &&  (table[0][0] == table[2][2]) && (table[0][0] != 0)){
            System.out.println("diagonal gauche droite");
            return (table[0][0]);
        }
        //left bottom to right top
        if ((table[0][2] == table[1][1]) &&  (table[0][2] == table[2][0]) && (table[0][2] != 0)){
            System.out.println("diagonal droite gauche");
            return (table[0][2]);
        }
        return 0;
    }
}
