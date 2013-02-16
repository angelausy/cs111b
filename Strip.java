/*
 
 Author      : Angela Lau 
 Course:     : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 02/12/13
 Program Name: Strip.java
 Objective   : This program will remove either all whitespace from a
               string if there is only one argument or all 
               occurrences of the second string argument from the
               first (and any extraneous whitespace).

*/

public class Strip
{
   
   public static String strip(String ... s)
   {
      int argLen = s.length;
      if(argLen == 0 || argLen > 2)
      {
         System.err.println("Valid arguments: " + 
                            "one or two strings.");
         System.exit(1);
      }

      String orig = s[0];
      String result = "";
      switch(argLen)
      {
         case 1:
            char c;
            orig = orig.trim();
            int origLen = orig.length();
            for(int i=0; i < origLen; i++)
            {
               c = orig.charAt(i);
               if(!Character.isWhitespace(c)) result += c;
            }
            break;
         case 2:
            String junk = s[1];
            int junkLen = junk.length();
            int junkStart = orig.indexOf(junk);
            while(junkStart != -1)
            {
                result += orig.substring(0,junkStart);
                String leftover = orig.substring(junkStart +
                                                junkLen);
                orig = leftover.trim();
                junkStart = orig.indexOf(junk);
            }
            result += orig;
      }
      return(result); 
   }
        
/* Example

strip("I am hungry. I am thirsty.", "am"); 
output: I hungry. I thirsty.

*/