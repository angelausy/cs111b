/*

 Author      : Angela Lau
 Class       : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 02/19/13
 Program Name: WC.java
 Objective   : This program displays the number of
               characters (-c), words (-w), or lines (-l)
               in one or more files.
 Usage       : java WC -OPTION FILE1 ... FILEN

*/

import java.util.Scanner;
import java.io.*;

public class WC
{
//*********************************die()**********************************
    public static void die(String msg)
    {
        System.err.println(msg);
        System.exit(1);
    }
    
//*******************************println()********************************
    public static void println(Object obj)
    {
        System.out.println("" + obj);
    }
    
//*****************************parseOption()******************************
//  Expands the two-character string input for the option into the entire
//  word.

    private static String parseOption(String flag)
    {
        switch(flag)
        {
            case "-c": return "character";
            case "-w": return "word";
            case "-l": return "line";
            default:   return "error";
        }
    }
    
//******************************maxLength()*******************************
//  The return value is used to set the width of the file column in the 
//  formatted display.

    public static int maxLength(String args[])
    {
        int max = 0;
        for(String s : args)
        {
            if(s.length() > max) max = s.length();
        }
        return(max);
    }
    
//*******************************getCount()*******************************
//  Returns the number of characters, words, or lines in a file.

    private static long getCount(File f, String option)
                        throws FileNotFoundException
    {
        if(option.equals("character")) return(f.length());
        long count = 0;
        try(Scanner sc = new Scanner(f))
        {
            switch(option)
            {
                case "word":
                     while(sc.hasNext())
                     {
                        ++count;
                        sc.next();
                     }
                     break;
                case "line":
                     while(sc.hasNextLine())
                     {
                        ++count;
                        sc.nextLine();
                     }
            }
        }
        return(count);
    }
    
//*********************************main()*********************************
    public static void main(String args[])
                       throws FileNotFoundException
    {
        int len = args.length;

        if(len < 2) die("Usage: java WC -[lwc] " +
                        "ONE OR MORE FILES");

        String option = parseOption(args[0]);

        if(option.equals("error"))
        {
            die("Invalid option. Available options: -c, -w, -l" +
                "\nExample usage: java WC -c file1 file2 file3");
        }
        
        String files[] = new String[len-1];
        System.arraycopy(args, 1, files, 0, len-1);
        
        int width = maxLength(files);
        
        for(String path : files)
        {
            File f = new File(path);

            System.out.printf("%"+width+"s: ", path);

            if(!f.exists()) println("does not exist");
            else if(!f.isFile()) println("not a normal file");
            else if(!f.canRead()) println("not readable");
            else
            {
                long count = getCount(f, option);
                println(count + " " + option +
                        ((count == 1)? "" : "s"));
            }
        }
    }
}

/* 

Sample output

Sample 1:
FixPhoneNumber.java: 3469 characters
            unicorn: does not exist
                pub: not a normal file
            private: not readable
           Box.java: 1342 characters
           syllabus: 2794 characters
                uno: 1 character

Sample 2:
       DemoBox.java: 62 words
FixPhoneNumber.java: 273 words
           Box.java: 126 words
           syllabus: 375 words
                uno: 1 word
*/
