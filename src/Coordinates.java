public class Coordinates {
    private int x, prev_x;
    private int y, prev_y;

    public Coordinates(int x, int y, int prev_x, int prev_y){
        this.x = x;
        this.y = y;
        this.prev_x = prev_x;
        this.prev_y = prev_y;
    }

    public int getX() { return x; }
    public int getY() { return y; };
    public int getPrev_x() { return prev_x; }
    public int getPrev_y() { return prev_y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setPrev_x(int prev_x) { this.prev_x = prev_x; }
    public void setPrev_y(int prev_y) { this.prev_y = prev_y; }
}
