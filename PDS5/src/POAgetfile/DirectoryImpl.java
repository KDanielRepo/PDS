package POAgetfile;

import POAgetfile.dirinfo.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;

class DirectoryImpl extends DirectoryPOA {
  private String root; // katalog root udost�pniany
 
  // konstruktor definiujacy katalog root
  public DirectoryImpl(String root) {
    System.out.println(" Servant utworzony (udostepnia katalog: " + root + ")");
    this.root = root;
  }

  // metoda zdalna, zwraca liste plikow w katalogu
  public String[] getDirList(String subdir){
     System.out.println(" Wywolanie metody getDirList() dla katalogu " + root + subdir);
     try {
      File dir = new File(root + subdir); 
      String dirlist[] = dir.list();
      for (int i=0; i<dirlist.length; i++)
      {
       File tmpfile = new File(root+subdir+dirlist[i]);
       if (tmpfile.isDirectory())  
          dirlist[i] = dirlist[i] + " \t  <DIR>"; else
          dirlist[i] = dirlist[i] + " \t[" + tmpfile.length() + "]";
      }
      return dirlist;
    } catch (Exception ex) {
       String errors[] = {" Jakis Blad katalogu! "};
       return errors;
      } 
   }
   
   // metoda zdalna, zwraca informacje o pliku
   public String getFileInfo(String filename){
     System.out.println(" Wywolanie metody getFileInfo() dla pliku " + root + filename);
     String answer = "";
     File tmpfile = new File(root + filename);
     boolean hidden = tmpfile.isHidden();
     Date date = new Date();
     date.setTime(tmpfile.lastModified());
     String modified = date.toString();
     if (tmpfile.isDirectory())  
        answer = " is DIRECTORY, hidden=" + hidden + ", modified "+ modified; else
        answer = " is FILE, length " + tmpfile.length() + "B, hidden=" + hidden+ ", modified "+ modified;
    return answer;
   }
} // DirectoryImpl
