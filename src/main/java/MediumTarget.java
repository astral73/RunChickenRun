import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class MediumTarget extends GameObject
{
    Handler handler;

    public MediumTarget(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() 
    {
        x += velX;
        y += velY;
        
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
                    HUD.score += 2;
                }
            }
        }
    }

    public void render(Graphics g) 
    {
        if(id == ID.Target)
        {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, 64, 64);
        }
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,64,64);
    }
}
