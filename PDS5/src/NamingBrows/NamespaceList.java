import java.util.Properties;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class NamespaceList
{
   public static void main(String args[])
   {
      try {
        //Properties props = new Properties();
       // props.put("org.omg.CORBA.ORBInitialPort", "1050");
        ORB orb = ORB.init(args, null);

        NamingContext nc = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        BindingListHolder bl = new BindingListHolder(); 
        BindingIteratorHolder blIt= new BindingIteratorHolder(); 
        nc.list(1000, bl, blIt); 
               
       Binding bindings[] = bl.value;
       System.out.println(bindings.length);
       if (bindings.length == 0) return;

       for (int i=0; i < bindings.length; i++) {

            // get the object reference for each binding
            org.omg.CORBA.Object obj = nc.resolve(bindings[i].binding_name);
            String objStr = orb.object_to_string(obj);
            int lastIx = bindings[i].binding_name.length-1;

            // check to see if this is a naming context
            if (bindings[i].binding_type == BindingType.ncontext) {
              System.out.println( "Context: " + bindings[i].binding_name[lastIx].id);
            } else {
                System.out.println("Object: " + bindings[i].binding_name[lastIx].id);
            }
        }

       } catch (Exception e) {
        e.printStackTrace(System.err);
       } 
   }
}


