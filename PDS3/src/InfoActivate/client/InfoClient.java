package InfoActivate.client;/*
 *  Koszalin 2003
 *  InfoClient.java
 *  Klient wywolujacy zdalna metode zdalnego obiektu RMI
 *  Dariusz Rataj (C)
 */
 
import InfoActivate.serwer.InfoInterface;

import java.rmi.*;

public class InfoClient {

 public static void main(String[] args) {

  /* ustawienie zarzadcy ochrony */
  System.setProperty("java.security.policy","C:\\IntelliJProjects\\PDS\\PDS3\\src\\InfoActivate\\client\\java.mypolicy");
  System.setSecurityManager(new SecurityManager());
  try {
  
    /* utworzenie obiektu zdalnego */
   InfoInterface robject = (InfoInterface) Naming.lookup("rmi://127.0.0.1:1099/InfoObject");

   System.out.println(" ------------ Info pobrane z obiektu ----"); 
    /* wywolanie metod zdalnych */
   String date = robject.getDate();
   System.out.println("Data na zdalnym hoscie: " + date );
   String os = robject.getOSInfo();
   System.out.println("  OS na zdalnym hoscie: " + os );
   String jvm = robject.getJVMInfo();
   System.out.println("  VM na zdalnym hoscie: " + jvm );

   System.out.println(" ----------------------------------------"); 
  } catch (Exception ex) { 
     System.err.println("Blad wywolania obiektu: " + ex); 
    }
 } // main
 
} // InfoClient   


