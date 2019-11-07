package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class BarrierTest {

    @Test
    public void barrierConstruction() {
        App.Testing = true;
        Barrier b = new Barrier(null, Vector2.Zero());
        assertNotNull(b);
        BarrierPart[] bp = b.GetBarrierParts();
        for (BarrierPart bpart : bp) {
            assertNotNull(bpart);
        }
    }

    @Test
    public void testBarrierNotDestroyed() {
        App.Testing = true;
        Barrier b = new Barrier(null, Vector2.Zero());
        BarrierPart[] bp = b.GetBarrierParts();
        for (BarrierPart bpart : bp) {
            assertFalse(bpart.IsDead());
        }
    }

    @Test
    public void testBarrierHitPointsMax() {
        App.Testing = true;
        Barrier b = new Barrier(null, Vector2.Zero());
        BarrierPart[] bp = b.GetBarrierParts();
        for (BarrierPart bpart : bp) {
            assertEquals(3, bpart.GetHealthPoints());
        }
    }

    @Test
    public void testBarrierHittable1() {
        App.Testing = true;
        Barrier b = new Barrier(null, Vector2.Zero());
        BarrierPart[] bp = b.GetBarrierParts();
        for (BarrierPart bpart : bp) {
            bpart.Hit(1);
            assertEquals(2, bpart.GetHealthPoints());
            bpart.Hit(1);
            assertEquals(1, bpart.GetHealthPoints());
            bpart.Hit(1);
            assertEquals(0, bpart.GetHealthPoints());
            assertTrue(bpart.IsDead());
        }
    }

    @Test
    public void testBarrierHittable2() {
        App.Testing = true;
        Barrier b = new Barrier(null, Vector2.Zero());
        BarrierPart[] bp = b.GetBarrierParts();
        for (BarrierPart bpart : bp) {
            bpart.Hit(0);
            assertEquals(3, bpart.GetHealthPoints());
            bpart.Hit(2);
            assertEquals(1, bpart.GetHealthPoints());
            bpart.Hit(300);
            assertTrue(bpart.IsDead());
        }
    }

}
