/*
 
 Author      : Angela Lau 
 Class       : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 02/15/13
 Program Name: FilePlus.java
 Objective   : FilePlus subclasses the File class. It overrides
               getParent() and includes the methods canAccess()
               and isEmpty().
 
*/

package io;
 
import java.io.*;
 
public class FilePlus extends File
{

//*******************************FilePlus()*******************************
   public FilePlus(String pathname) 
   {
      super(pathname);
   }

//*******************************FilePlus()*******************************
   public FilePlus(String parent, String child)
   {
      super(parent, child);
   }

//******************************getParent()*******************************
// Returns the absolute pathname of the parent, 
// even if the parent is not specified in the object pathname.

   public String getParent()
   {
      String absolutePath = super.getAbsolutePath();
      File f = new File(absolutePath);
      return(f.getParent());
   }

//******************************canAccess()*******************************
// Returns true if the parent pathname is accessible.

   public boolean canAccess()
   {
      String path = this.getParent();
      FilePlus fp = new FilePlus(path);
      return(fp.canExecute());
   }  
   
//*******************************isEmpty()********************************
// Returns true if the object is an empty directory.

   public boolean isEmpty()
   {
      return(super.exists() &&
             super.isDirectory() &&
	          (super.list()).length == 0);
   }
}
