package CarPrice.examples.carinfo;


/**
* examples/carinfo/_carPriceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from carinfo.idl
* sobota, 5 listopad 2005 14:36:30 CET
*/

public class _carPriceStub extends org.omg.CORBA.portable.ObjectImpl implements CarPrice.examples.carinfo.carPrice
{

  public float getCarPrice (String model)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCarPrice", true);
                $out.write_string (model);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCarPrice (model        );
            } finally {
                _releaseReply ($in);
            }
  } // getCarPrice

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:examples/carinfo/carPrice:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _carPriceStub