package invadem;

import processing.core.PConstants;
import processing.core.PImage;
import java.lang.Math;

public class Invader implements IEntity, IMoveable {

    public Invader(int healthPoints, Vector2 pos, PImage[] sprites) {
        this.healthPoints = healthPoints;
        this.pos = pos.Copy();
        this.sprites = sprites;
        this.startingPos = pos.Copy();
    }

    //-----------------------------------------------------------
    //Fields
    public static int movementFrame;
    public final Vector2 size = new Vector2(22, 16);
    private Vector2 pos;
    private Vector2 startingPos;
    private int healthPoints;
    private PImage[] sprites;
    private int animationIndex = 0; //The index of the current sprite the Invader is using.

    private Direction lastMovementDirection = Direction.DOWN;
    private Direction currentMovementDirection = Direction.LEFT;
    private int continuousMoveSteps; //Number of pixels moved in the current move direction.
    private int framesSinceMoveStep; //Number of frames since moving a step.

    private boolean dead = false;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public Type GetEntityType() { return Type.INVADER; }

    public int GetHealthPoints() { return healthPoints; }

    public Vector2 GetPos() { return pos; }

    public Vector2 GetSize() { return size; }

    //-----------------------------------------------------------
    //Methods
    public void Update() {
        if (!dead) {
            MovementAI();
            Render();
        }
    }

    private void MovementAI() {
        framesSinceMoveStep++;

        //Changing direction if necessary
        switch(currentMovementDirection) {
            case LEFT:
                if (pos.x <= (startingPos.x - 30)) {
                    lastMovementDirection = Direction.LEFT;
                    currentMovementDirection = Direction.DOWN;
                    animationIndex = 1;
                    continuousMoveSteps = 0;
                }
                break;
            case RIGHT:
                if (pos.x >= (startingPos.x + 30)) {
                    lastMovementDirection = Direction.RIGHT;
                    currentMovementDirection = Direction.DOWN;
                    animationIndex = 1;
                    continuousMoveSteps = 0;
                }
                break;
            case DOWN:
                if (continuousMoveSteps >= 8) {
                    if (lastMovementDirection == Direction.LEFT) {
                        currentMovementDirection = Direction.RIGHT;
                    } else {
                        currentMovementDirection = Direction.LEFT;
                    }
                    lastMovementDirection = Direction.DOWN;
                    animationIndex = 0;
                    continuousMoveSteps = 0;
                }
                break;
        }

        //Moving the unit
        if (framesSinceMoveStep >= 2) {
            switch (currentMovementDirection) {
                case LEFT:
                    Move(-1, 0);
                    framesSinceMoveStep = 0;
                    continuousMoveSteps++;
                    break;
                case RIGHT:
                    Move(1, 0);
                    framesSinceMoveStep = 0;
                    continuousMoveSteps++;
                    break;
                case DOWN:
                    Move(0, 1);
                    framesSinceMoveStep = 0;
                    continuousMoveSteps++;
            }
        }
    }

    public void Hit(int damage) {

    }

    public void Move(int x, int y) {
        pos.x += x;
        pos.y += y;
    }

    public void Render() {
        App.GetInstance().imageMode(PConstants.CENTER);
        App.GetInstance().image(sprites[animationIndex], pos.x, pos.y);
    }
}
