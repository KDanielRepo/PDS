package POAServantActivator;

import POAAdapterActivator.test.TesterPOA;

public class TesterServant extends TesterPOA {

    public String getMessage() {
        return " Tester: getMessage(): test obiektu ";
    }
}
