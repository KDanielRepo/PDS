package POADirectory.dirinfo;

/**
* dirinfo/DirectoryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dirinfo.idl
* Thursday, June 18, 2020 6:18:13 PM CEST
*/

abstract public class DirectoryHelper
{
  private static String  _id = "IDL:dirinfo/Directory:1.0";

  public static void insert (org.omg.CORBA.Any a, POADirectory.dirinfo.Directory that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static POADirectory.dirinfo.Directory extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (POADirectory.dirinfo.DirectoryHelper.id (), "Directory");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static POADirectory.dirinfo.Directory read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_DirectoryStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, POADirectory.dirinfo.Directory value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static POADirectory.dirinfo.Directory narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof POADirectory.dirinfo.Directory)
      return (POADirectory.dirinfo.Directory)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      POADirectory.dirinfo._DirectoryStub stub = new POADirectory.dirinfo._DirectoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static POADirectory.dirinfo.Directory unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof POADirectory.dirinfo.Directory)
      return (POADirectory.dirinfo.Directory)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      POADirectory.dirinfo._DirectoryStub stub = new POADirectory.dirinfo._DirectoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}