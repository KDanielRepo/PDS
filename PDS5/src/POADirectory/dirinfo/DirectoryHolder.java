package POADirectory.dirinfo;

/**
* dirinfo/DirectoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dirinfo.idl
* Thursday, June 18, 2020 6:18:13 PM CEST
*/

public final class DirectoryHolder implements org.omg.CORBA.portable.Streamable
{
  public POADirectory.dirinfo.Directory value = null;

  public DirectoryHolder ()
  {
  }

  public DirectoryHolder (POADirectory.dirinfo.Directory initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = POADirectory.dirinfo.DirectoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    POADirectory.dirinfo.DirectoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return POADirectory.dirinfo.DirectoryHelper.type ();
  }

}
