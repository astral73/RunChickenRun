import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;
    private ID id;
    
    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i< handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelX(-5);
                }
                
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelX(5);
                }
                
                if(key == KeyEvent.VK_SPACE)
                {
                    handler.addObject(new GoldenEgg(tempObject.getX(),tempObject.getY() + 8,ID.GoldenEgg,tempObject.getFacing() * 4));
                    
                }
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE)
        {
            System.exit(1);
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i< handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelX(0);
                }
                
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelX(0);
                }
                
                if(key == KeyEvent.VK_SPACE)
                {
                    handler.addObject(new GoldenEgg(tempObject.getX(),tempObject.getY() + 8,ID.GoldenEgg,tempObject.getFacing() * 4));
                    
                }
            }
        }
    }
}
