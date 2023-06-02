


import java.util.Random;

public class ProduceBombs extends Bomb
{

    public ProduceBombs(GameBoard board, int number)
    {

        super(board);

        int count =0;

        do {
            reproduceBomb();
            count++;
        }while (count < number);
    }

    /**
     * This method produce bombs on random square. If the assigned square has already contained a bomb, then
     * reassign a square to receive this bomb by invoking itself.
     */
    public void reproduceBomb()
    {
        Random r = new Random();

        int xLocation = r.nextInt(boardWidth);
        int yLocation = r.nextInt(boardHeight);

        SmartSquare square = (SmartSquare) board.getSquareAt(xLocation, yLocation);

        if (!square.getBombExist())
        {
            // mark this square as it has a bomb and been traversed.
            square.setBombExist(true);
            square.setTraverse(true);
        } else {
            reproduceBomb();
        }
    }
}
