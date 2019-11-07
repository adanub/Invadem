package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectileTest {


    @Test
    public void testProjectileConstruction() {
        App.Testing = true;
        Bullet proj = new Bullet(Vector2.Zero(), null, null, Bullet.BulletType.NORMAL);
        assertNotNull(proj);
    }

    @Test
    public void testProjectileIsFriendly() {
        App.Testing = true;
        Tank tank = new Tank(3, null);
        Bullet proj = new Bullet(Vector2.Zero(), tank, null, Bullet.BulletType.NORMAL);
        assertEquals(IEntity.Type.TANK, proj.GetOwnerType());
    }

    @Test
    public void testProjectileIsNotFriendly() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, Vector2.Zero(), null);
        Bullet proj = new Bullet(Vector2.Zero(), inv, null, Bullet.BulletType.NORMAL);
        assertEquals(IEntity.Type.INVADER, proj.GetOwnerType());
    }

    @Test
    public void testProjectileIntersect() {
        App.Testing = true;
        Bullet proj = proj = new Bullet(Vector2.Zero(), null, null, Bullet.BulletType.NORMAL);
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(100, 100), null);
        Tank tank = new Tank(3, null);
        assertFalse(Collisions.Check(proj, inv));
        assertFalse(Collisions.Check(proj, tank));
    }

}
