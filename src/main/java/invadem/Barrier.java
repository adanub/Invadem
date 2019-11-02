package invadem;

import processing.core.PImage;

public class Barrier {
    public Barrier(PImage[] sprites, Vector2 pos) {
        this.sprites = sprites;
        this.pos = pos;

        barrierParts = new BarrierPart[] {
                new BarrierPart(new PImage[]{ sprites[0], sprites[1], sprites[2] }, new Vector2(pos.x - 8, pos.y - 8)), //Left upper
                new BarrierPart(new PImage[]{ sprites[3], sprites[4], sprites[5] }, new Vector2(pos.x, pos.y - 8)), //Mid upper
                new BarrierPart(new PImage[]{ sprites[6], sprites[7], sprites[8] }, new Vector2(pos.x + 8, pos.y - 8)), //Right upper
                new BarrierPart(new PImage[]{ sprites[9], sprites[10], sprites[11] }, new Vector2(pos.x - 8, pos.y)), //Left mid
                new BarrierPart(new PImage[]{ sprites[9], sprites[10], sprites[11] }, new Vector2(pos.x + 8, pos.y)), //Right mid
                new BarrierPart(new PImage[]{ sprites[9], sprites[10], sprites[11] }, new Vector2(pos.x - 8, pos.y + 8)), //Left lower
                new BarrierPart(new PImage[]{ sprites[9], sprites[10], sprites[11] }, new Vector2(pos.x + 8, pos.y + 8)), //Right lower
        };
    }

    //-----------------------------------------------------------
    //Fields
    private Vector2 pos; //The current position of this Barrier
    private PImage[] sprites;
    private BarrierPart[] barrierParts;

    //-----------------------------------------------------------
    //Properties/Getter-Setters

    //-----------------------------------------------------------
    //Methods
    public void Update() {
        for (BarrierPart b : barrierParts) {
            b.Update();
        }
    }
}
