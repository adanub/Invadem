package invadem;

import processing.core.PConstants;
import processing.core.PImage;

public class BarrierPart implements IEntity {

    public BarrierPart(PImage[] sprites, Vector2 pos) {
        this.sprites = sprites;
        this.pos = pos;
    }

    //-----------------------------------------------------------
    //Fields
    public final Vector2 size = new Vector2(8, 8); //The size (in pixels) of this Tank
    private Vector2 pos; //The current position of this Barrier
    private int hp = 3; //The health points of the Barrier, corresponding to how much damage it can sustain
    private boolean dead = false;

    private PImage[] sprites;
    private int animationIndex;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public Type GetEntityType() {
        return Type.BARRIER;
    }

    public int GetHealthPoints() {
        return hp;
    }

    public Vector2 GetPos() { return pos; }

    public Vector2 GetSize() { return size; }

    //-----------------------------------------------------------
    //Methods
    public void Update() {
        if (!dead) {
            Render();
        }
    }

    public void Hit(int damage) {

    }

    public void Render() {
        App.GetInstance().imageMode(PConstants.CENTER);
        App.GetInstance().image(sprites[0], pos.x, pos.y);
    }
}
