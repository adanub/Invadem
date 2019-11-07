package invadem;

import processing.core.PImage;

public class Barrier {
    public Barrier(PImage[] sprites, Vector2 pos) {
        this.sprites = sprites;
        this.pos = pos;

        PImage[] l_u_sprites = null;
        PImage[] m_u_sprites = null;
        PImage[] r_u_sprites = null;
        PImage[] l_sprites = null;

        if (!App.Testing) { //Don't assign sprites for rendering if app is in testing mode
            l_u_sprites = new PImage[]{ sprites[0], sprites[1], sprites[2] };
            m_u_sprites = new PImage[]{ sprites[3], sprites[4], sprites[5] };
            r_u_sprites = new PImage[]{ sprites[6], sprites[7], sprites[8] };
            l_sprites = new PImage[]{ sprites[9], sprites[10], sprites[11] };
        }

        barrierParts = new BarrierPart[] {
                new BarrierPart(l_u_sprites, new Vector2(pos.x - 8, pos.y - 8)), //Left upper
                new BarrierPart(m_u_sprites, new Vector2(pos.x, pos.y - 8)), //Mid upper
                new BarrierPart(r_u_sprites, new Vector2(pos.x + 8, pos.y - 8)), //Right upper
                new BarrierPart(l_sprites, new Vector2(pos.x - 8, pos.y)), //Left mid
                new BarrierPart(l_sprites, new Vector2(pos.x + 8, pos.y)), //Right mid
                new BarrierPart(l_sprites, new Vector2(pos.x - 8, pos.y + 8)), //Left lower
                new BarrierPart(l_sprites, new Vector2(pos.x + 8, pos.y + 8)), //Right lower
        };
    }

    //-----------------------------------------------------------
    //Fields
    private Vector2 pos; //The current position of this Barrier
    private PImage[] sprites;
    private BarrierPart[] barrierParts;

    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public BarrierPart[] GetBarrierParts() {
        return barrierParts;
    }

    //-----------------------------------------------------------
    //Methods
    public void Update() {
        for (BarrierPart b : barrierParts) {
            b.Update();
        }
    }
}
