package POAServantActivator.test;

/**
* test/TesterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Test.idl
* Thursday, June 18, 2020 6:33:54 PM CEST
*/

public final class TesterHolder implements org.omg.CORBA.portable.Streamable
{
  public POAServantActivator.test.Tester value = null;

  public TesterHolder ()
  {
  }

  public TesterHolder (POAServantActivator.test.Tester initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = POAServantActivator.test.TesterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    POAServantActivator.test.TesterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return POAServantActivator.test.TesterHelper.type ();
  }

}