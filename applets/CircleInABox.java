/*
 
 Author      : Angela Lau
 Course      : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 03/10/13
 Program Name: CircleInABox.java
 Objective   : This applet allows the user to move a circle
               horizontally and vertically and to change the
               size of the circle by pressing buttons. The
               entire circle remains within the box at all
               times.
 Demo URL: http://hills.ccsf.edu/~alau46/cs111b/circlebox.html

*/
 
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
 
public class CircleInABox extends Applet implements ActionListener
{
   final int Width = 960;
   final int Height = 580;
   final int MinY = 35;
   final int MaxY = Height - 5;
   final int MinX = 5;
   final int MaxX = Width - 5;
   final int MinDiam = 10;
   final int DeltaXY = 15;
   final int DeltaDiam = 15;

   int x0, y0, x1, y1, diameter;
   int newX0, newY0, newX1, newY1, newDiam;
   Color bgColor, fgColor, circleColor;
   Button left, right, up, down, inflate, deflate;

//*********************************init()*********************************
   public void init()
   {
      // set up applet window
      setSize(new Dimension(Width, Height));
      bgColor = Color.gray;
      fgColor = Color.black;
      circleColor = Color.cyan;
      setBackground(bgColor);
      setForeground(fgColor);
    
      // set up circle
      diameter = 100;
      x0 = (Width - diameter)/2;
      y0 = (Height - diameter)/2 + MinY;
      x1 = x0 + diameter; y1 = y0 + diameter;
      newX0 = x0; newY0 = y0;
      newX1 = x1; newY1 = y1;


      // set up buttons
      left = new Button("Left");
      up = new Button("Up");
      down = new Button("Down");
      right = new Button("Right");
      inflate = new Button("Inflate");
      deflate = new Button("Deflate");
      add(left); add(up); add(down); add(right);
      add(inflate); add(deflate);

      left.addActionListener(this);
      up.addActionListener(this);
      down.addActionListener(this);
      right.addActionListener(this);
      inflate.addActionListener(this);
      deflate.addActionListener(this);
   }

//***************************actionPerformed()****************************
   public void actionPerformed(ActionEvent ae) 
   {
      Object action = ae.getSource();
      int delta;
      // translate circle DeltaXY pixels along the x axis
      if((action == left) || (action == right))
      {
         delta = (action == left)? -DeltaXY : DeltaXY;
         newX0 = x0 + delta;
         newX1 = x1 + delta;
         newY0 = y0; newY1 = y1; newDiam = diameter;
      } 
      // translate circle DeltaXY pixels along the y axis
      else if((action == up) || (action == down))
      {
         delta = (action == up)? -DeltaXY : DeltaXY;
         newY0 = y0 + delta;
         newY1 = y1 + delta;
         newX0 = x0; newX1 = x1; newDiam = diameter;
      }
      // change the diameter from fixed point (x0, y0)
      else
      {
         delta = (action == inflate)? DeltaDiam : -DeltaDiam;
         newDiam = diameter + delta;
         newX1 = x0 + newDiam;
         newY1 = y0 + newDiam;
         newX0 = x0; newY0 = y0;
      }

      // if true, circle remains inside boundary
      if(!((newX0 < MinX) || (newX1 > MaxX) ||
           (newY0 < MinY) || (newY1 > MaxY) ||
           (newDiam < MinDiam)))
      {
         // update circle 
         x0 = newX0; y0 = newY0;
         x1 = newX1; y1 = newY1;
         diameter = newDiam;
      }
      repaint();
   }

//********************************paint()*********************************
   public void paint(Graphics g)
   {
      g.setColor(circleColor);
      g.fillOval(x0,y0,diameter,diameter);
   }
}
