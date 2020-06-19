package POAVector;/*
 *  Koszalin 2003
 *  VectorImpl.java
 *  Implementacja metod Vector
 *  Dariusz Rataj (C)
 */

import POAVector.vtools.*;
import POAVector.vtools.VectorPackage.*;
import org.omg.CORBA.*;

class VectorImpl extends VectorPOA {
 
 public VectorImpl(){
  System.out.println(" Servant VectorImpl utworzony... ");
 }
 
 public int[] add (short size, int[] a, int[] b){
 
   int result[] = new int[POAVector.vtools.Vector.MAXSIZE];
   for(int i=0; i < size; i++) {
      result[i] = a[i] + b[i];
    }
   return result; 
 }

 public int[] sub (short size, int[] a, int[] b){
 
   int result[] = new int[POAVector.vtools.Vector.MAXSIZE];
   for(int i=0; i < size; i++) {
      result[i] = a[i] - b[i];
    }
   return result; 
 }
} // VectorImpl
