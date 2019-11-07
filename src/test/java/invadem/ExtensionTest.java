package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExtensionTest {

    @Test
    public void testInvaderTypes() {
        App.Testing = true;
        App app = new App();
        app.StartGame();
        assertNotNull(app);
        assertEquals(Invader.InvaderType.ARMOUR, app.GetInvaders().get(35).GetInvaderType());
        assertEquals(Invader.InvaderType.POWER, app.GetInvaders().get(25).GetInvaderType());
        assertEquals(Invader.InvaderType.NORMAL, app.GetInvaders().get(15).GetInvaderType());
        assertEquals(Invader.InvaderType.NORMAL, app.GetInvaders().get(5).GetInvaderType());
    }

    @Test
    public void testPowerInvaderShoot() {
        App.Testing = true;
        App app = new App();
        Invader inv = new Invader(Invader.InvaderType.POWER, Vector2.Zero(), null);
        Bullet b = inv.Shoot();
        assertEquals(Bullet.BulletType.POWER, b.GetBulletType());
        assertEquals(3, b.GetDamage());
        assertEquals(1, app.GetBullets().size()); //Check that the bullet has been added to the update list in the application
    }

    @Test
    public void testScoring() {
        App.Testing = true;
        App app = new App();
        assertEquals(0, app.currentScore);
        assertEquals(10000, app.highScore);

        app.StartGame();
        app.GetInvaders().get(0).Hit(1); //This should kill this normal invader
        app.UpdateEntities();
        assertEquals(100, app.currentScore); //Normal invader should grant +100 points

        app.GetInvaders().get(37).Hit(3); //This should kill the armoured invader
        app.UpdateEntities();
        assertEquals(350, app.currentScore); //Armoured invader should grant +250 points

        app.GetInvaders().get(27).Hit(1); //This should kill the power invader
        app.UpdateEntities();
        assertEquals(600, app.currentScore); //Power invader should grant +250 points
    }

    @Test
    public void testHighScore() {
        App.Testing = true;
        App app = new App();
        app.currentScore = 11000;
        assertEquals(10000, app.highScore);
        app.GameOver();
        assertEquals(11000, app.highScore);
    }
}
