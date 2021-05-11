
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Target extends GameObject
{
    Handler handler;
    Random random = new Random();
    int w=random.nextInt(650);
    int h=360+random.nextInt(300);
    public Target(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() 
    {
     x=w;
     y=h;
        
        collision();
    }
    
    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.GoldenEgg)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.score += 1;
                }
            }
        }
    }

    public void render(Graphics g) 
    {
        if(id == ID.Target)
        {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, 16, 16);
        }
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,32,32);
    }
}
