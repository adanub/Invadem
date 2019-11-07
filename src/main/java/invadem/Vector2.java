package invadem;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 Copy() {
        return new Vector2(x, y);
    }

    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }
}
