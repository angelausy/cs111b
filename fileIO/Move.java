/*
 
 Author      : CS 111B notes 
 Date        : 02/06/13
 Program Name: Move.java
 Objective   : This program moves a file to a new location. 
 Usage       : java Move file1 file2 
 
*/
 
import java.util.*;
import java.io.*;
 
public class Move
{
   public static void die(String ... msg)
   {
      if(msg.length == 0)
      {
         System.err.println("An error occurred in the program.");
      }
      else
      {
         System.err.println(msg[0]);
      }
      System.exit(1);
   }

   public static void main(String args[]) throws FileNotFoundException
   {
      if(args.length != 2) die("Usage: java Move file1 file2");
      String file1 = args[0];
      String file2 = args[1];
      File f1 = new File(file1);
      if(!f1.exists()) die(file1 + " does not exist.");
      if(!f1.canRead()) die(file1 + " is not readable.");
      if(!f1.isFile()) die(file1 + " is not a normal file.");
      File f2 = new File(file2);
      if(f2.exists())
      {
         System.out.print(file2 + " already exists, overwrite" +
                          " (Y/N)? ");
         try(Scanner sc = new Scanner(System.in))
         {
            String answer = sc.nextLine();
            if(Character.toUpperCase(answer.charAt(0)) == 'N')
            {
               System.exit(0);
            }
         }
         if(!f2.canWrite()) die("Cannot write into " + file2);
      }
      f1.renameTo(f2);
   }
}
