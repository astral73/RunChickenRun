import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class GoldenEgg extends GameObject
{

    public GoldenEgg(int x, int y, ID id,int vely) 
    {
        super(x, y, id);
        this.velY = vely;
    }

    public void render(Graphics g) 
    {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 8, 8);
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,4,4);
    }

    public void tick() {
        if(velY>=0)
            y+=velY;
        else
            y-=velY;
    }
    
}