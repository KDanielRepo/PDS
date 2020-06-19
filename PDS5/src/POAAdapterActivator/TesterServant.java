package POAAdapterActivator;

import POAAdapterActivator.test.TesterPOA;

public class TesterServant extends TesterPOA {

    public String getMessage() {
       System.out.println(" Servant: wywolanie metody getMessage");
       return " Tester: getMessage(): test obiektu ";
    }
}
