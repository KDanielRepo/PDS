package Callback.CallbackApp;

/**
* CallbackApp/MsgCallbackHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from CallbackExample.idl
* sobota, 5 listopad 2005 14:50:17 CET
*/

public final class MsgCallbackHolder implements org.omg.CORBA.portable.Streamable
{
  public Callback.CallbackApp.MsgCallback value = null;

  public MsgCallbackHolder ()
  {
  }

  public MsgCallbackHolder (Callback.CallbackApp.MsgCallback initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Callback.CallbackApp.MsgCallbackHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Callback.CallbackApp.MsgCallbackHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Callback.CallbackApp.MsgCallbackHelper.type ();
  }

}
