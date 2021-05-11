import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Random;

public class Player extends GameObject implements ImageObserver
{
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler) 
    {
        super(x, y, id);
        this.handler = handler;
        velY = 2;
        velX = 0;
    }

    public void tick() 
    {
        x += velX;
        y += velY;
        
        if(velX < 0)
        {
            facing = -1;
        }
        
        else if(velX > 0)
        {
            facing = 1;
        }
        
        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
        collision();
    }
    
    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Enemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.lifes--;
                }
            }
        }
    }

    public void render(Graphics g) 
    {
       Graphics2D g2d = (Graphics2D) g;
        
       if(id == ID.Player)
       {   
           g.drawImage(Game.chicken, getX(), getY(), null);
       }
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,32,32);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        
        return false;
        
    }
}
