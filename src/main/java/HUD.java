import java.awt.Graphics;
import javax.swing.JLabel;

public class HUD
{
    public static int lifes = 3;
    public static int score = 0;
    public static int level = 1;
    
    public void tick()
    {
        lifes = Game.clamp(lifes, 0, 3);
    }
    
    public void render(Graphics g)
    {
        g.drawString("Score: " + score,0,16);
        g.drawString("Level: " + level,0,32);
        g.drawString("Lifes: " + lifes,0,48);
    }
    
    public void score(int score)
    {
        this.score(score);
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
}