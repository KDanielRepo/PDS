package CarPrice.examples.carinfo;


/**
* examples/carinfo/carPriceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from carinfo.idl
* sobota, 5 listopad 2005 14:36:30 CET
*/

abstract public class carPriceHelper
{
  private static String  _id = "IDL:examples/carinfo/carPrice:1.0";

  public static void insert (org.omg.CORBA.Any a, CarPrice.examples.carinfo.carPrice that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CarPrice.examples.carinfo.carPrice extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CarPrice.examples.carinfo.carPriceHelper.id (), "carPrice");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CarPrice.examples.carinfo.carPrice read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_carPriceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CarPrice.examples.carinfo.carPrice value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CarPrice.examples.carinfo.carPrice narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CarPrice.examples.carinfo.carPrice)
      return (CarPrice.examples.carinfo.carPrice)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CarPrice.examples.carinfo._carPriceStub stub = new CarPrice.examples.carinfo._carPriceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
