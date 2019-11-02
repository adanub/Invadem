package invadem;

import processing.core.PConstants;
import processing.core.PImage;

public class Bullet implements IEntity {

    public Bullet(Vector2 pos, IEntity owner) {
        this.pos = pos.Copy();
        this.owner = owner;
        sprite = App.GetInstance().GetProjectileSprite();
    }

    //-----------------------------------------------------------
    //Fields
    public final Vector2 size = new Vector2(22, 16); //The size (in pixels) of this Tank
    private Vector2 pos = new Vector2(320, 450); //The current position of this Tank
    private int hp; //The health points of the Tank, corresponding to how much damage it can sustain
    private boolean dead = false;
    private IEntity owner;

    private PImage sprite;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public Type GetEntityType() {
        return Type.BULLET;
    }

    public Vector2 GetPos() { return pos; }

    public int GetHealthPoints() {
        return hp;
    }

    public Vector2 GetSize() { return size; }

    //-----------------------------------------------------------
    //Methods
    public void Render() {
        App.GetInstance().imageMode(PConstants.CENTER);
        App.GetInstance().image(sprite, pos.x, pos.y);
    }

    public void Update() {
        if (!dead) {
            Render();
        }
    }

    public void Hit(int damage) {

    }
}
