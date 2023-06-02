public class CheckSquare
{

    private GameBoard board;


    private int boardHeight;


    private int boardWidth;

    private static final int[] distantX = {-1, 0, 1};
    private static final int[] distantY = {-1, 0, 1};


    public CheckSquare(GameBoard board)
    {
        this.board = board;

        boardHeight = (board.getHeight() - 20) / 20;
        boardWidth = (board.getWidth() - 20) / 20;
    }


    private boolean hasKickedBoundary(int x, int y)
    {
        return x < 0 || x >= boardWidth || y < 0 || y >= boardHeight;
    }


    protected boolean isSuccess()
    {

        int count = 0;

        for (int y = 0; y < boardHeight; y++)
        {
            for (int x = 0; x < boardWidth; x++)
            {
                if (((SmartSquare) board.getSquareAt(x, y)).getTraverse())
                    count++;
            }
        }

        return count == boardHeight * boardWidth;
    }


    protected void showBomb(int currentX, int currentY)
    {
        for (int y = 0; y < boardHeight; y++)
        {
            for (int x = 0; x < boardWidth; x++)
            {
                if (currentX == x && currentY == y){}
                else if (((SmartSquare) board.getSquareAt(x, y)).getBombExist())
                    board.getSquareAt(x, y).setImage(CheckSquare.class.getResource("/bomb.png"));
                else if(((SmartSquare) board.getSquareAt(x, y)).getGuessThisSquareIsBomb())
                    board.getSquareAt(x, y).setImage(CheckSquare.class.getResource("/flagWrong.png"));
            }
        }
    }


    protected void countBomb(int currentX, int currentY)
    {

        int count = 0;
        SmartSquare currentObject;

        if (hasKickedBoundary(currentX, currentY))
            return;
        else if(((SmartSquare)board.getSquareAt(currentX, currentY)).getTraverse())
            return;
        else {

            SmartSquare squareObject;


            currentObject = (SmartSquare)board.getSquareAt(currentX, currentY);
            currentObject.setTraverse(true);


            for (int x : distantX)
            {
                for (int y: distantY)
                {
                    if (hasKickedBoundary(currentX + x, currentY + y)){}
                    else if (x == 0 && y == 0){}
                    else{
                        squareObject = (SmartSquare)board.getSquareAt(currentX + x, currentY + y);
                        if (squareObject.getBombExist()) count = count + 1;
                    }
                }
            }
        }


        if (count != 0)
            currentObject.setImage(CheckSquare.class.getResource( "/" + count + ".png"));
        else {

            currentObject.setImage(CheckSquare.class.getResource("/0.png"));
            countBomb(currentX - 1, currentY -1);
            countBomb(currentX, currentY -1);
            countBomb(currentX + 1, currentY -1);
            countBomb(currentX - 1, currentY);
            countBomb(currentX + 1, currentY);
            countBomb(currentX - 1, currentY + 1);
            countBomb(currentX, currentY + 1);
            countBomb(currentX + 1, currentY + 1);
        }
    }
}
