/*
 
 Author: Angela Lau 
 Date  : 02/13/13
 Program Name: LongestLines.java
 Objective: This program takes a file and displays all
            lines with more than 66 characters. The line
            number of each line is displayed in square
            brackets. 
*/
 
import java.util.*;
import java.io.*;
 
public class LongestLines
{
//*********************************die()**********************************
   public static void die(String ... msg)
   {
      if(msg.length == 0)
      {
         System.err.println("An error occurred.");
      }
      else
      {
         System.err.println(msg[0]);
      }
      System.exit(1);
   }    

//*********************************main()*********************************
   public static void main(String args[]) throws 
                           FileNotFoundException
   {
      if(args.length != 1) die("Invalid number of arguments");

      String file = args[0]; 
      File f = new File(file);

      if(!f.exists()) die(file + " does not exist.");
      if(!f.isFile()) die(file + " is not a file.");
      if(!f.canRead()) die(file + " is not readable."); 

      try(Scanner sc = new Scanner(f))
      {
         int i = 0;
         while(sc.hasNextLine())
         {
            ++i;
            String line = sc.nextLine();
            if(line.length() > 66) 
            {
               System.out.println("[" + i +"] " + line);
            }
         }
      } 
   }
}
