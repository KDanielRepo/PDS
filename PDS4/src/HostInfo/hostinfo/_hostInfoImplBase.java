package HostInfo.hostinfo;


/**
* hostinfo/_hostInfoImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from hostinfo.idl
* Thursday, June 18, 2020 8:11:40 AM CEST
*/

public abstract class _hostInfoImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements HostInfo.hostinfo.hostInfo, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _hostInfoImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getDayTime", new java.lang.Integer (0));
    _methods.put ("getAddress", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // hostinfo/hostInfo/getDayTime
       {
         String $result = null;
         $result = this.getDayTime ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // hostinfo/hostInfo/getAddress
       {
         String $result = null;
         $result = this.getAddress ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:hostinfo/hostInfo:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _hostInfoImplBase
