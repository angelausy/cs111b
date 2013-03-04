/*
 
 Author      : Angela Lau
 Date        : 02/26/13
 Program Name: Lines.java
 Objective   : This applet displays 500 random lines,
               clears the screen, and then displays 500 new
               random lines, in an infinite loop.
 
*/
 
import java.awt.*;
import java.applet.*;
 
public class Lines extends Applet
{
    final int ITERATIONS = 500;
    final int DELAY = 150;
    int counter, screenWidth, screenHeight;
    Color bgColor, fgColor;

    public void init()
    {
        counter = 0;

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(d);
	screenWidth = (int) d.getWidth();
	screenHeight = (int) d.getHeight();

        setBackground(Color.black);
	setForeground(Color.green);
        bgColor = getBackground();
        fgColor = getForeground();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void cls()
    {
        Graphics g = getGraphics();
        g.setColor(bgColor);
	g.fillRect(0,0,screenWidth,screenHeight); 
	g.setColor(fgColor);
    }

    public void sleep(int msec)
    {
       try
       {
          Thread.sleep(msec);
       } 
       catch(InterruptedException e) 
       {
       }
    }

    public static int rand(int a, int b)
    {
       return((int)((b-a+1)*Math.random())+a); 
    }

    public Color randomColor()
    {
       return(new Color(rand(0,255),rand(0,255),rand(0,255)));
    }
    
    public int randomX()
    {
        return(rand(0,screenWidth));
    }

    public int randomY()
    {
        return(rand(0,screenHeight));
    }

    public void drawRandomLine(Graphics g)
    {
	g.drawLine(randomX(), randomY(), randomX(), randomY());
    }

    public void paint(Graphics g)
    {
        g.setColor(randomColor());
        drawRandomLine(g);
	counter++;
	if(counter < ITERATIONS) 
	{
	    sleep(DELAY);
	    repaint();
	}
	else
	{
	    counter = 0;
            sleep(DELAY);
	    cls();
            sleep(DELAY);
	    repaint();
	}
    }
}
