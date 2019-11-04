package invadem;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;

public class Tank implements IEntity, IMoveable {

    public Tank(int hp, PImage sprite) {
        this.sprite = sprite;
        this.hp = hp;
    }

    //-----------------------------------------------------------
    //Fields
    public final Vector2 size = new Vector2(22, 16); //The size (in pixels) of this Tank
    private Vector2 pos = new Vector2(320, 450); //The current position of this Tank
    private int hp; //The health points of the Tank, corresponding to how much damage it can sustain
    private boolean dead = false;

    private PImage sprite;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public Type GetEntityType() {
        return Type.TANK;
    }

    public Vector2 GetPos() { return pos; }

    public int GetHealthPoints() {
        return hp;
    }

    public Vector2 GetSize() { return size; }

    public boolean IsDead() { return dead; }

    //-----------------------------------------------------------
    //Methods
    public void Update() {
        if (!dead) {
            Render();
        }
    }

    public void Move(int x, int y) {
        if (!dead) {
            pos.x += x;
            pos.y += y;

            //Preventing ship from going out of bounds
            if (pos.x < 0) {
                pos.x = 0;
            } else if (pos.x > 640) {
                pos.x = 640;
            }
        }
    }

    public void Hit(int damage) {
        hp -= 1;
        if (hp <= 0) {
            dead = true;
            hp = 0;
            App.GetInstance().GameOver();
        }
    }

    public void Render() {
        App.GetInstance().imageMode(PConstants.CENTER);
        App.GetInstance().image(sprite, pos.x, pos.y);
    }

    public void Shoot() {
        App.GetInstance().GetBullets().add(new Bullet(new Vector2(pos.x, pos.y - size.y/2), this, new Vector2(0, -1)));
    }
}
