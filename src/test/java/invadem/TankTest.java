package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TankTest {

    @Test
    public void testTankConstruction() {
        Tank tank = new Tank(3, null);
        assertNotNull(tank);
    }

    @Test
    public void testTankProjectile() {
        App app = new App();
        app.Testing = true;
        Tank tank = new Tank(3, null);
        assertNotNull(tank.Shoot());
    }

    @Test
    public void testTankIsNotDead() {
        Tank tank = new Tank(3, null);
        assertEquals(false, tank.IsDead());
    }

}
