package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class App extends PApplet {
    //-----------------------------------------------------------
    //Fields
    private static App instance; //Singleton instance of this class
    private Tank tank;
    private ArrayList<Invader> invaders = new ArrayList<>();
    private ArrayList<Barrier> barriers = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int gameOverDisplay = 0; //Number of frames to display the Game Over screen for
    private int nextLevelDisplay = 120; //Number of frames to display the Next Level screen for
    private int invaderShootDelay = 300; //Number of frames between each invader shot
    private int invaderShootCountdown = 300; //Countdown to the next invader shot

    //Sprites
    private PImage tankSprite;
    private PImage[] barrierSprites;
    private PImage gameOverSprite;
    private PImage nextLevelSprite;
    private PImage projectileSprite;
    private PImage[] invaderSprites;

    //Input
    private boolean left;
    private boolean right;
    private boolean shoot;
    private boolean spacePressed;

    //Object Removal
    private ArrayList<Invader> deadInvaders = new ArrayList<>();
    private ArrayList<Bullet> deadBullets = new ArrayList<>();
    //-----------------------------------------------------------
    //Properties/Getter-Setters
    public static App GetInstance() { return instance; } //Property for accessing Singleton instance

    public int GetMovementInput() {
        if (left && !right) {
            return -1;
        } else if (right && !left) {
            return 1;
        } else {
            return 0;
        }
    }

    public PImage GetProjectileSprite() {
        return projectileSprite;
    }

    public Tank GetTank() { return tank; }

    public ArrayList<Barrier> GetBarriers() { return barriers; }

    public ArrayList<Invader> GetInvaders() { return invaders; }

    public ArrayList<Bullet> GetBullets() { return bullets; }
    //-----------------------------------------------------------
    //Methods
    public App() {
        //Set up your objects
        if (instance == null) { //Assigning this as singleton reference
            instance = this;
        }
    }

    public void LoadSprites() {
        //Loading in game sprite assets
        tankSprite = loadImage("src/main/resources/tank1.png");

        invaderSprites = new PImage[] {
                loadImage("src/main/resources/invader1.png"),
                loadImage("src/main/resources/invader2.png")
        };

        barrierSprites = new PImage[] {
                //Left
                loadImage("src/main/resources/barrier_left1.png"),
                loadImage("src/main/resources/barrier_left2.png"),
                loadImage("src/main/resources/barrier_left3.png"),
                //Top
                loadImage("src/main/resources/barrier_top1.png"),
                loadImage("src/main/resources/barrier_top2.png"),
                loadImage("src/main/resources/barrier_top3.png"),
                //Right
                loadImage("src/main/resources/barrier_right1.png"),
                loadImage("src/main/resources/barrier_right2.png"),
                loadImage("src/main/resources/barrier_right3.png"),
                //Solid
                loadImage("src/main/resources/barrier_solid1.png"),
                loadImage("src/main/resources/barrier_solid2.png"),
                loadImage("src/main/resources/barrier_solid3.png"),
        };

        projectileSprite = loadImage("src/main/resources/projectile.png");

        gameOverSprite = loadImage("src/main/resources/gameover.png");
        nextLevelSprite = loadImage("src/main/resources/nextlevel.png");
    }

    public void setup() {
        frameRate(60);
        LoadSprites();
    }

    public void StartGame() {
        //Creating tank
        tank = new Tank(3, tankSprite);

        //Creating Invaders
        invaders.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Invader inv = new Invader(
                        1,
                        new Vector2(150 + (36 * j), 40 + (36 * i)),
                        invaderSprites
                );
                invaders.add(inv);
            }
        }

        //Creating Barriers
        barriers.clear();
        for (int i = 1; i < 4; i++) {
            barriers.add(
                    new Barrier(barrierSprites, new Vector2(160 * i, 420))
            );
        }

        //Resetting Projectiles
        bullets.clear();
    }

    public void settings() {
        size(640, 480);
    }

    public void draw() { 
        //Main Game Loop
        background(1f); //Setting the background to black
        if (gameOverDisplay > 0) {
            if (gameOverDisplay == 120) {
                StartGame();
            }
            imageMode(CENTER);
            gameOverDisplay--;
            image(gameOverSprite, 320, 240);
        } else if (nextLevelDisplay > 0) {
            if (nextLevelDisplay == 120) {
                StartGame();
            }
            imageMode(CENTER);
            nextLevelDisplay--;
            image(nextLevelSprite, 320, 240);
        } else {
            UpdateEntities();
        }
    }

    public void GameOver() {
        gameOverDisplay = 120;
    }

    public void NextLevel() {
        nextLevelDisplay = 120;
    }

    private void UpdateEntities() {
        //Tank
        tank.Move(GetMovementInput(), 0);
        tank.Update();

        //Invaders
        for (Invader i : invaders) {
            i.Update();
            if (i.IsDead()) {
                deadInvaders.add(i);
            }
        }
        if (invaders.size() == 0) {
            NextLevel();
        }

        //Barriers
        for (Barrier b : barriers) {
            b.Update();
        }

        //Bullets
        for (Bullet b : bullets) {
            b.Update();
            if (b.IsDead()) {
                deadBullets.add(b);
            }
        }

        //Clean-up
        for (Invader i : deadInvaders) {
            invaders.remove(i);
        }
        deadInvaders.clear();

        for (Bullet b : deadBullets) {
            bullets.remove(b);
        }
        deadBullets.clear();

        //Invader shots
        invaderShootCountdown--;
        if (invaderShootCountdown <= 0) {
            invaderShootCountdown = invaderShootDelay;
            AttackWithRandomInvader();
        }
    }

    private void AttackWithRandomInvader() {
        int invaderIndex = (int)(Math.random() * invaders.size());
        if (invaderIndex > invaders.size() - 1) {
            invaderIndex = invaders.size() - 1;
        }
        invaders.get(invaderIndex).Shoot();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (key == CODED) {
            if (keyCode == LEFT) {
                left = true;
            } else if (keyCode == RIGHT) {
                right = true;
            }
        } else if (e.getKey() == ' ' && !spacePressed) {
            tank.Shoot();
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (key == CODED) {
            if (keyCode == LEFT) {
                left = false;
            } else if (keyCode == RIGHT) {
                right = false;
            }
        } else if (e.getKey() == ' ') {
            spacePressed = false;
        }
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
