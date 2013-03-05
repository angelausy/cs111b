/*
 
 Author      : Angela Lau
 Date        : 02/26/13
 Program Name: Lines.java
 Objective   : This applet displays 100 random lines,
               clears the screen, and then displays 100 new
               random lines, in an infinite loop.
 
*/
 
import java.awt.*;
import java.applet.*;
 
public class Lines extends Applet
{
    final int ITERATIONS = 100;
    final int DELAY = 200;
    int counter, screenWidth, screenHeight;
    Color bgColor, fgColor;

//*********************************init()*********************************
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

//********************************update()********************************
//  override update to avoid clearing the screen everytime repaint()
//  is called
    public void update(Graphics g)
    {
        paint(g);
    }

//*********************************cls()**********************************
//  clear screen
    public void cls()
    {
        Graphics g = getGraphics();
        g.setColor(bgColor);
	g.fillRect(0,0,screenWidth,screenHeight); 
	g.setColor(fgColor);
    }

//********************************sleep()*********************************
//  used in combination with repaint() as solution to timer problem
//  of repaint(long)
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

//*********************************rand()*********************************
    public static int rand(int a, int b)
    {
       return((int)((b-a+1)*Math.random())+a); 
    }

//*****************************randomColor()******************************
    public Color randomColor()
    {
       return(new Color(rand(0,255),rand(0,255),rand(0,255)));
    }
    
//*******************************randomX()********************************
    public int randomX()
    {
        return(rand(0,screenWidth));
    }

//*******************************randomY()********************************
    public int randomY()
    {
        return(rand(0,screenHeight));
    }

//****************************drawRandomLine()****************************
    public void drawRandomLine(Graphics g)
    {
	g.drawLine(randomX(), randomY(), randomX(), randomY());
    }

//********************************paint()*********************************
    public void paint(Graphics g)
    {
        if(counter == ITERATIONS)
        {
            counter = 0;
            sleep(DELAY);
            cls();
        }
        else 
        {
            g.setColor(randomColor());
            drawRandomLine(g);
	    counter++;
        }
	sleep(DELAY);
	repaint();
    }
}
