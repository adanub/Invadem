package invadem;

import org.junit.Test;
import static org.junit.Assert.*;


public class InvaderTest {

    @Test
    public void testInvaderConstruction() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        assertNotNull(inv);
    }

    @Test
    public void testInvaderFireProjectile() {
        App.Testing = true;
        App app = new App();
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        assertNotNull(inv.Shoot());
    }

    @Test
    public void testInvaderIsNotDead() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        assertEquals(false, inv.IsDead());
    }

    @Test
    public void testInvaderIsDead() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        inv.Hit(1);
        assertEquals(true, inv.IsDead());
    }

    @Test
    public void testInvaderIntersectWithPlayerProjectile() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        Tank tank = new Tank(3, null);
        Bullet proj = new Bullet(new Vector2(0, 0), tank, null, Bullet.BulletType.NORMAL);
        assertTrue(Collisions.Check(proj, inv));
    }

    @Test
    public void testInvaderDoesNotIntersectWithPlayerProjectile() {
        App.Testing = true;
        Invader inv = new Invader(Invader.InvaderType.NORMAL, new Vector2(0, 0), null);
        Tank tank = new Tank(3, null);
        Bullet proj = new Bullet(new Vector2(200, 200), tank, null, Bullet.BulletType.NORMAL);
        assertFalse(Collisions.Check(proj, inv));
    }
}
