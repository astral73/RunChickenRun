
import java.awt.Rectangle;

public class Spawner 
{
    private Handler handler;
    private HUD hud;
    
    public Spawner(Handler handler,HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
    }
    
    public void tick()
    {
        GameObject tempObject = handler.object.getFirst();
        if(tempObject.getY()== Game.HEIGHT - 60 && tempObject.getId() == ID.Player)
        {
            hud.setLevel(hud.getLevel() + 1);
            handler.Clear();
            Game.newLevel();
        }
    }
}