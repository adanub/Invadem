package invadem;

import processing.core.PConstants;
import processing.core.PImage;

public class Bullet implements IEntity, IMoveable {

    public Bullet(Vector2 pos, IEntity owner, Vector2 velocity, BulletType bulletType) {
        this.pos = pos.Copy();
        this.owner = owner;
        if (!App.Testing) { //Don't load sprites during tests
            sprite = App.GetInstance().GetProjectileSprite(bulletType);
        }
        this.velocity = velocity;
        this.bulletType = bulletType;
        switch (bulletType) {
            case NORMAL:
                damage = 1;
                size = new Vector2(1, 3);
                break;
            case POWER:
                damage = 3;
                size = new Vector2(2, 5);
                break;
        }
    }

    public enum BulletType {
        NORMAL, POWER
    }

    //-----------------------------------------------------------
    //Fields
    public Vector2 size; //The size (in pixels) of this bullet
    private Vector2 pos; //The current position of this bullet
    private int hp = 1; //The number of enemies this bullet can penetrate
    private boolean dead = false;
    private IEntity owner;
    private Vector2 velocity;
    private int damage;
    private BulletType bulletType;

    private PImage sprite;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public Type GetEntityType() {
        return Type.BULLET;
    }

    public Type GetOwnerType() { return owner.GetEntityType(); }

    public Vector2 GetPos() { return pos; }

    public int GetHealthPoints() {
        return hp;
    }

    public Vector2 GetSize() { return size; }

    public boolean IsDead() { return dead; }

    public BulletType GetBulletType() { return bulletType; }

    public int GetDamage() { return damage; }

    //-----------------------------------------------------------
    //Methods
    public void Render() {
        if (!App.Testing) {
            App.GetInstance().imageMode(PConstants.CENTER);
            App.GetInstance().image(sprite, pos.x, pos.y);
        }
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
                    this.Hit(1);
                    bp.Hit(damage);
                    return;
                }
            }
        }

        if (owner.GetEntityType() != Type.TANK) {
            if (!App.GetInstance().GetTank().IsDead() && Collisions.Check(this, App.GetInstance().GetTank())) {
                this.Hit(1);
                App.GetInstance().GetTank().Hit(damage);
                return;
            }
        } else {
            for (Invader i : App.GetInstance().GetInvaders()) {
                if (!i.IsDead() && Collisions.Check(this, i)) {
                    this.Hit(1);
                    i.Hit(damage);
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
