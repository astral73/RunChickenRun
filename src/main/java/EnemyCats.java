import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.LinkedList;
import java.util.Random;

public class EnemyCats extends GameObject implements ImageObserver
{
    public static Handler handler;
    Random random = new Random();
    final int w=random.nextInt(620);
    final int h=360+random.nextInt(300);
    public EnemyCats(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x = w+velX;
        y=h+ velY;
    }
    
    ImageFilter filter = new RGBImageFilter() {
        
        int transparentColor = Color.white.getRGB() | 0xFF000000;
        
        public int filterRGB(int x, int y, int rgb) {
            if ((rgb | 0xFF000000) == transparentColor) {
               return 0x00FFFFFF & rgb;
            } else {
               return rgb;
            }
        }
    };
    
    ImageProducer filteredImgProd = new FilteredImageSource(Game.cat.getSource(), filter);
    Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

    public void render(Graphics g) {
        if(id == ID.Enemy)
        {
            /*g.setColor(Color.lightGray);
            g.fillOval(x, y, 64, 64);*/
            
            g.drawImage(transparentImg, getX(), getY(), this);
        } 
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x,y,64,64);
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}