package POAVector;/*
 *  Koszalin 2003
 *  CounterImpl.java
 *  Implementacja metod Counter
 *  Dariusz Rataj (C)
 */

import POAVector.vtools.*;
import org.omg.CORBA.*;

class CounterImpl extends CounterPOA {
 public int count = 0;

 public CounterImpl(){
  System.out.println(" Servant CounterImpl utworzony... ");
 }

 public int count (){
  return count;
 }
 
 public void count (int newCount){
  this.count = newCount;
 }
 
 public void inc (){
  count++;
 }
 public void dec (){
  count--;
 }
} // CounterImpl
