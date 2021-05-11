import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.spec.NamedParameterSpec;

public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;
    private HUD hud;
    
    public Menu(Game game, Handler handler, HUD hud)
    {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    
    public void MousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Menu)
        {
            if(mouseOver(mx, my, 260, 150, 200, 64))
            {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(320, 0, ID.Player, handler));
                game.newLevel();
            }

            if(mouseOver(mx, my, 260, 250, 200, 64))
            {
                game.gameState = Game.STATE.Help;
            }

            if(mouseOver(mx, my, 260, 350, 200, 64))
            {
                System.exit(1);
            }
        }
        
        if(game.gameState == Game.STATE.Help)
        {
            if(mouseOver(mx, my, 260, 500, 200, 64))
            {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        
        if(game.gameState == Game.STATE.End)
        {
            if(mouseOver(mx, my, 260, 700, 200, 64))
            {
                game.gameState = Game.STATE.Game;
                HUD.lifes = 3;
                HUD.score = 0;
                HUD.level = 1;
                handler.addObject(new Player(320, 0, ID.Player, handler));
                game.newLevel();
            }
        }
    }
    
    public void mouseReleased(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Menu)
        {
            if(mouseOver(mx, my, 260, 150, 200, 64))
            {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(320, 0, ID.Player, handler));
                game.newLevel();
            }

            if(mouseOver(mx, my, 260, 250, 200, 64))
            {
                game.gameState = Game.STATE.Help;
            }

            if(mouseOver(mx, my, 260, 350, 200, 64))
            {
                System.exit(1);
            }
        }
        
        if(game.gameState == Game.STATE.Help)
        {
            if(mouseOver(mx, my, 260, 500, 200, 64))
            {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        
        if(game.gameState == Game.STATE.End)
        {
            if(mouseOver(mx, my, 260, 500, 200, 64))
            {
                game.gameState = Game.STATE.Game;
                HUD.lifes = 3;
                HUD.score = 0;
                HUD.level = 1;
                handler.addObject(new Player(320, 0, ID.Player, handler));
                game.newLevel();
            }
        }
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if(mx > x && mx < x + width)
        {
            if(my > y && my < y + height)
            {
                return true;
            }
            
            else
            {
                return false;
            }
        }
        
        else
        {
            return false;
        }
    }
    
    public void tick()
    {
        
    }
    
    public void render(Graphics g)
    {
        if(game.gameState == Game.STATE.Menu)
        {
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);

            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Run Chicken Run", 140, 70);

            g.setFont(fnt2);
            g.drawRect(260, 150, 200, 64);
            g.drawString("Play", 330, 190);

            g.drawRect(260, 250, 200, 64);
            g.drawString("Help", 330, 290);

            g.drawRect(260, 350, 200, 64);
            g.drawString("Quit", 330, 390);
        }
        
        else if(game.gameState == Game.STATE.Help)
        {
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
            Font fnt3 = new Font("arial",1,15);
            
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Help", 300, 70);
            
            g.setFont(fnt2);
            g.drawRect(260, 500, 200, 64);
            g.drawString("Back", 330, 540);
            
            g.setFont(fnt3);
            g.drawString("- Use A - D to move chicken!!!", 10, 200);
            g.drawString("- Shoot the targets with spacebar!!!", 10, 220);
            g.drawString("- Avoid Cats!!!", 10, 240);
        }
        
        else if(game.gameState == Game.STATE.End)
        {
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
            Font fnt3 = new Font("arial",1,15);
            
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Game Over", 230, 70);
            
            g.setFont(fnt2);
            g.drawRect(260, 500, 200, 64);
            g.drawString("Retry", 330, 540);
            
            g.setFont(fnt3);
            g.drawString("Your game ended with a score of: " + hud.score , 10, 200);
            g.drawString("Try Again", 10, 220);
        }
    }
}