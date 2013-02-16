/*

 Author      : Angela Lau 
 Course      : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 02/15/13
 Program Name: Wipe.java
 Objective   : This program deletes one or more files or empty 
               directories specified from the command line.
               A message will be displayed whether or not the 
               action is successful.
               
 Usage: java Wipe [fileOrDir1] [fileOrDir2] ... [fileOrDirN]
 
*/
 
public class Wipe
{
//******************************printError()******************************
   public static void printError(String ... msg)
   {
      switch(msg.length)
      {
         case 0: 
            System.err.println("An unknown error occurred.");
            break;
         case 1:
            System.err.println(msg[0]);
            break;
         case 2:
            String pathname = msg[0];
            String detail = msg[1];
            System.err.println("Cannot delete " + pathname +
                              ": " + detail);
      }
   }

//*********************************main()*********************************
   public static void main(String args[])
   {
      if(args.length == 0)
      {
         printError("No files or directories were" +
                           " specified for deletion.");
         System.exit(1);
      }
      for(String fileOrDir : args)
      {
         FilePlus fp = new FilePlus(fileOrDir);
         if(!fp.canAccess()) 
         {
            printError(fileOrDir, "cannot access");
         }
         else if(!fp.exists())
         {
            printError(fileOrDir, "does not exist");
         }
         else if(!fp.delete())
         {
            FilePlus parent = new FilePlus(fp.getParent());
            if(!parent.canWrite())
            {
               printError(fileOrDir, "permission denied");
            }
            else if(fp.isDirectory() && !fp.isEmpty()) 
            {
               printError(fileOrDir, "non-empty directory");
            }
            else
            {
               printError();
            }
         }
         else
         {
            System.out.println("Deleted " + fileOrDir);
         }   
      }
   }
}

/* Example of usage

wipe> java Wipe writeable/file writeable/emptyDir \
> writeable/nonEmpty noWrite/file imaginary
Deleted writeable/file
Deleted writeable/emptyDir
Cannot delete writeable/nonEmpty: non-empty directory
Cannot delete noWrite/file: permission denied
Cannot delete imaginary: does not exist

*/
