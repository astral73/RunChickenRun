import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 720;
    public static final int HEIGHT  = 720;
    public static final int RIGHT_WALL = 720;
    public static final int LEFT_WALL = 1;
    public static final int DOWN_WALL = 720;
    public static final int UP_WALL = 1;
    
    private Thread thread;
    private boolean running = false;
    private static Random r;
    private static Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    public static BufferedImage background;
    public static BufferedImage chicken;
    public static BufferedImage cat;
    public static BufferedImage spriteSheet;
    
    public static int numW;
    public static int numH;
    public static int w;
    public static int h;
    public static int randomNumW;
    public static int randomNumH;
    public static int randomNumHTarget;
    public static int randomNumWTarget;
    public static int rth;
    public static int rtw;
    private static int levels=0;
    public enum STATE
    {
        Menu,
        Help,
        Game,
        End
    };
    
    public static STATE gameState = STATE.Menu;
   
    public Game()
    {
 
        handler = new Handler();
        menu = new Menu(this,handler,hud);
        this.addKeyListener(new KeyInput(handler));
        hud = new HUD();
        this.addMouseListener(menu);
      
        
        new Window(WIDTH, HEIGHT, "Run Chicken Run", this);
        spawner = new Spawner(handler,hud);
         r = new Random();
        numW = WIDTH - 32 + 1;
        numH = HEIGHT - 32 + 1;
        w = r.nextInt(Game.WIDTH - 50) ;
        h = r.nextInt(Game.HEIGHT - 50) ;
        randomNumW = w + 32;
        randomNumH = h + 32;
        randomNumHTarget = r.nextInt(Game.HEIGHT - 50) ;
        randomNumWTarget = r.nextInt(Game.WIDTH - 50) ;
        rth = randomNumHTarget + 32;
        rtw = randomNumWTarget + 32;
        
        if(gameState == STATE.Game)
        {
            handler.addObject(new Player(360, 0, ID.Player, handler));
            newLevel();
        }
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop()
    {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() 
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            
            if(running)
            {
                try {
                    render();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick()
    {
        handler.tick();
        if(gameState == STATE.Game)
        {
            hud.tick();
            spawner.tick();
            
            if(HUD.lifes <= 0)
            {
                handler.object.clear();
                Game.gameState = Game.STATE.End;
            }
        }
        
        if(gameState == STATE.Menu || gameState == STATE.End)
        {
            menu.tick();
        }
    }
    
    private void render() throws IOException
    {
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        background = ImageIO.read(new FileInputStream("resources/bg.jpg"));
        chicken = ImageIO.read(new FileInputStream("resources/chicken.png"));
        cat = ImageIO.read(new FileInputStream("resources/cat.png"));
        
        g.drawImage(background, 0, 0, this);
        
        handler.render(g);
        
        if(gameState == STATE.Game)
        {
            hud.render(g);
        }
        
        if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End)
        {
            menu.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
        {
            return var = max;
        }
        
        else if(var <= min)
        {
            return var = min;
        }
        
        else
        {
            return var;
        }
    }
    
    public static void newLevel()
    {  
        levels++;
        if(levels>=4)
        {
            levels=0;
        gameState = STATE.End;
        }
        else
        {
        r = new Random();
        numW = WIDTH - 32 + 1;
        numH = HEIGHT - 32 + 1;
        w = r.nextInt(Game.WIDTH - 50) ;
        h = r.nextInt(Game.HEIGHT - 50) ;
        randomNumW = w + 32;
        randomNumH = h + 32;
        randomNumHTarget = r.nextInt(Game.HEIGHT - 50) ;
        randomNumWTarget = r.nextInt(Game.WIDTH - 50) ;
        rth = randomNumHTarget + 32;
        rtw = randomNumWTarget + 32;
        handler.addObject(new Player(320, 0, ID.Player, handler));
        
        for(int i = 1; i <= HUD.level; i++)
        {
            
            if(i % 2 == 1 && i == 1)
            {
                w = 360+r.nextInt(200) ;
                h = r.nextInt(650);
                randomNumW = w + 32;
                randomNumH = h + 32;
                randomNumHTarget = 360+r.nextInt(240) ;
                randomNumWTarget = r.nextInt(580);
                rth = randomNumHTarget + 32;
                rtw = randomNumWTarget + 32;
                
                handler.addObject(new EnemyCats(randomNumW,randomNumH,ID.Enemy,handler));
                handler.addObject(new Target(randomNumWTarget,randomNumHTarget,ID.Target,handler));
            }
            
            
            if(i % 2 == 1 && i != 1)
            {
                w = 360+r.nextInt(200) ;
                h = r.nextInt(650);
                randomNumW = w + 32;
                randomNumH = h + 32;
                randomNumHTarget = 360+r.nextInt(240) ;
                randomNumWTarget = r.nextInt(580);
                rth = randomNumHTarget + 32;
                rtw = randomNumWTarget + 32;

                handler.addObject(new EnemyCats(randomNumW,randomNumH,ID.Enemy,handler));
            }
            
            if(i % 2 == 0)
            {
                w = 360+r.nextInt(200) ;
                h = r.nextInt(650);
                randomNumW = w + 50;
                randomNumH = h + 50;
                randomNumHTarget = 360+r.nextInt(240) ;
                randomNumWTarget = r.nextInt(580);
                rth = randomNumHTarget + 50;
                rtw = randomNumWTarget + 50;
                
                handler.addObject(new MediumTarget(rtw,rth,ID.Target,handler));
            }
            
            if(i % 2 == 0 && i != 2)
            {
                w = 360+r.nextInt(240) ;
                h = r.nextInt(580);
                randomNumW = w + 80;
                randomNumH = h + 80;
                randomNumHTarget = 360+r.nextInt(240) ;
                randomNumWTarget = r.nextInt(580);
                rth = randomNumHTarget + 80;
                rtw = randomNumWTarget + 80;
                
                handler.addObject(new BigTarget(rtw,rth,ID.Target,handler));
            }
        }
        }
    }
    
    public static void main(String[] args) 
    {
        new Game();
    }
}