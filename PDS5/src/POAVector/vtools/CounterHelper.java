package POAVector.vtools;


/**
* vtools/CounterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vector.idl
* Thursday, June 18, 2020 6:34:21 PM CEST
*/


// licznik
abstract public class CounterHelper
{
  private static String  _id = "IDL:vtools/Counter:1.0";

  public static void insert (org.omg.CORBA.Any a, POAVector.vtools.Counter that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static POAVector.vtools.Counter extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (POAVector.vtools.CounterHelper.id (), "Counter");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static POAVector.vtools.Counter read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CounterStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, POAVector.vtools.Counter value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static POAVector.vtools.Counter narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof POAVector.vtools.Counter)
      return (POAVector.vtools.Counter)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      POAVector.vtools._CounterStub stub = new POAVector.vtools._CounterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static POAVector.vtools.Counter unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof POAVector.vtools.Counter)
      return (POAVector.vtools.Counter)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      POAVector.vtools._CounterStub stub = new POAVector.vtools._CounterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}