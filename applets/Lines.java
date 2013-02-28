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
    int counter, delay, screenWidth, screenHeight;

    public void init()
    {
        counter = 0;
	delay = 265;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	screenWidth = d.width;
	screenHeight = d.height;
	setSize(d);
        setBackground(Color.black);
	setForeground(Color.green);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void cls()
    {
        Graphics g = getGraphics();
        g.setColor(getBackground());
	g.fillRect(0,0,screenWidth,screenHeight); 
	g.setColor(getForeground());
    }

    public void sleep(int msec)
    {
       try
       {
          Thread.sleep(msec);
       } 
       catch(InterruptedException e) {}
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

    public void paint(Graphics g)
    {
        g.setColor(randomColor());
	g.drawLine(randomX(), randomY(), randomX(), randomY());
	counter++;
	if(counter < 500) 
	{
	    sleep(delay);
	    repaint();
	}
	else
	{
	    counter = 0;
	    cls();
	    sleep(delay);
	    repaint();
	}
    }
}
