package invadem;

import processing.core.PConstants;
import processing.core.PImage;

public class Bullet implements IEntity, IMoveable {

    public Bullet(Vector2 pos, IEntity owner, Vector2 velocity) {
        this.pos = pos.Copy();
        this.owner = owner;
        sprite = App.GetInstance().GetProjectileSprite();
        this.velocity = velocity;
    }

    //-----------------------------------------------------------
    //Fields
    public final Vector2 size = new Vector2(1, 3); //The size (in pixels) of this Tank
    private Vector2 pos = new Vector2(320, 450); //The current position of this Tank
    private int hp; //The health points of the Tank, corresponding to how much damage it can sustain
    private boolean dead = false;
    private IEntity owner;
    private Vector2 velocity;

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

    public boolean IsDead() { return dead; }

    //-----------------------------------------------------------
    //Methods
    public void Render() {
        App.GetInstance().imageMode(PConstants.CENTER);
        App.GetInstance().image(sprite, pos.x, pos.y);
    }

    public void Update() {
        if (!dead) {
            Move(velocity.x, velocity.y);
            CheckCollisionWithEntities();
            Render();
        }
    }

    private void CheckCollisionWithEntities() {
        //Checking collision with barriers
        for (Barrier b : App.GetInstance().GetBarriers()) {
            for (BarrierPart bp : b.GetBarrierParts()) {
                if (!bp.IsDead() && Collisions.Check(this, bp)) {
                    System.out.println("Collided with Barrier");
                    this.Hit(1);
                    bp.Hit(1);
                    return;
                }
            }
        }

        if (owner.GetEntityType() != Type.TANK) {
            if (!App.GetInstance().GetTank().IsDead() && Collisions.Check(this, App.GetInstance().GetTank())) {
                System.out.println("Collided with Tank");
                this.Hit(1);
                App.GetInstance().GetTank().Hit(1);
                return;
            }
        } else {
            for (Invader i : App.GetInstance().GetInvaders()) {
                if (!i.IsDead() && Collisions.Check(this, i)) {
                    System.out.println("Collided with Invader");
                    this.Hit(1);
                    i.Hit(1);
                }
            }
        }
    }

    public void Move(int x, int y) {
        pos.x += x;
        pos.y += y;
    }

    public void Hit(int damage) {
        if (!dead) {
            dead = true;
        }
    }
}
