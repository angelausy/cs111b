// <applet code='Myself' width=500 height=350></applet>

/*
 
 Author      : Angela Lau
 Course      : CS 111B
 Instructor  : Abbas Moghtanei 
 Date        : 02/16/13
 Program Name: Myself.java
 Objective   : This program is an applet displaying
               a bit of information about yours truly.
 
*/
 
import java.awt.*;
import java.applet.*;
 
public class Myself extends Applet
{
   public void paint(Graphics g)
   {
      g.drawString("Name: Angela Lau", 20, 20);
      g.drawString("Schedule: CS 111B, CS 155A, " +
                   "CS 160A, CNIT 132, GRPH 25", 20, 38);
   }
}
