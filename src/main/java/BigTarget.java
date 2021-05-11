 import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class BigTarget extends GameObject
{
    Handler handler;
    Random random = new Random();
    int w=random.nextInt(640);
    int h=360+random.nextInt(240);
    public BigTarget(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() 
    {
        while(collision1())
        {
        x =w;  
        y =h;
        }
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
                    HUD.score += 4;
                }
            }
        }
    }
    private boolean collision1()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Target)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    w=random.nextInt(640);
                    h=360+random.nextInt(240);
                    return false;
                }
            }
        }
        return true;
    }

    public void render(Graphics g) 
    {
        if(id == ID.Target)
        {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, 50, 50);
        }
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,50,50);
    }
}
