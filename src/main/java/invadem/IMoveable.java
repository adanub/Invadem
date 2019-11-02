package invadem;

public interface IMoveable {

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public void Move(int x, int y);
}
