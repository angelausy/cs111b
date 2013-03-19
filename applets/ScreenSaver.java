/*

Author      : Angela Lau
Date        : 02/26/13
Program Name: ScreenSaver.java
Objective   : This applet fills the entire screen with 500 random
              lines, then 500 random circles, and then 500 random
              triangles in an infinite loop.

URL: http://hills.ccsf.edu/~alau46/cs111b/ScreenSaver.html

*/

import java.awt.*;
import java.applet.*;

public class ScreenSaver extends Applet
{
    final int LIMIT = 500;
    final int DELAY = 150;
    int counter;
    int screenWidth, screenHeight;
    Color bgColor, fgColor;
    
//*********************************init()*********************************
    public void init()
    {
        counter = 0;
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d);
        screenWidth = (int) d.getWidth();
        screenHeight = (int) d.getHeight();
        
        bgColor = Color.black;
        fgColor = Color.green;
        setBackground(bgColor);
        setForeground(fgColor);
    }
    
//********************************update()********************************
//  override update to avoid clearing the screen
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
    
//***************************drawRandomCircle()***************************
    public void drawRandomCircle(Graphics g)
    {
        final int MIN_DIAMETER = 10;
        int circleBufferX = screenWidth - MIN_DIAMETER;
        int circleBufferY = screenHeight - MIN_DIAMETER;

        int xCoord = randomX();
        int yCoord = randomY();
        if(xCoord > circleBufferX) xCoord -= MIN_DIAMETER;
        if(yCoord > circleBufferY) yCoord -= MIN_DIAMETER;

        int dx = screenWidth - xCoord;
        int dy = screenHeight - yCoord;
        int maxDiameter = (dx < dy)? dx : dy;
        int diameter = rand(MIN_DIAMETER, maxDiameter); 

        g.fillOval(xCoord, yCoord, diameter, diameter);
    }
    
//**************************drawRandomTriangle()**************************
    public void drawRandomTriangle(Graphics g)
    {
        int x[] = {randomX(), randomX(), randomX()};
        int y[] = {randomY(), randomY(), randomY()};
        g.fillPolygon(x, y, 3);
    }
    
//********************************paint()*********************************
    public void paint(Graphics g)
    {
        if(counter % LIMIT == 0)
        {
            if(counter ==  LIMIT * 3) counter = 0;
            sleep(DELAY);
            cls();
            sleep(DELAY);
            repaint();
        }
        
        g.setColor(randomColor());
        if(counter < LIMIT * 1) drawRandomLine(g);
        else if(counter < LIMIT * 2) drawRandomCircle(g);
        else drawRandomTriangle(g);
        counter++;
        sleep(DELAY);
        repaint();
    }
}
