public class CannonBall {
    /**
     * Simulating a CannonBall!!
     *
     * @author matin tavakoli
     * @param row the cannonball's row!(from 0 to 9)
     * @param column the cannonball's column!(from 0 to 9)
     */
    private int row;//The row that the arrow aims at!
    private int column;//The column that the arrow aims at!

    public CannonBall(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isCannonBallValid() {
        if (row > 9 || row < 0 || column > 9 || column < 0)
            return false;
        else
            return true;//In case nothing goes wrong!
    }
}
