package POADirectory.dirinfo;

/**
* dirinfo/DirectoryPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dirinfo.idl
* Thursday, June 18, 2020 6:23:53 PM CEST
*/

public abstract class DirectoryPOA extends org.omg.PortableServer.Servant
 implements POADirectory.dirinfo.DirectoryOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getDirList", new java.lang.Integer (0));
    _methods.put ("getFileInfo", new java.lang.Integer (1));
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
       case 0:  // dirinfo/Directory/getDirList
       {
         String subdir = in.read_string ();
         String $result[] = null;
         $result = this.getDirList (subdir);
         out = $rh.createReply();
         POADirectory.dirinfo.DirectoryPackage.dlistHelper.write (out, $result);
         break;
       }

       case 1:  // dirinfo/Directory/getFileInfo
       {
         String filename = in.read_string ();
         String $result = null;
         $result = this.getFileInfo (filename);
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
    "IDL:dirinfo/Directory:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Directory _this() 
  {
    return DirectoryHelper.narrow(
    super._this_object());
  }

  public Directory _this(org.omg.CORBA.ORB orb) 
  {
    return DirectoryHelper.narrow(
    super._this_object(orb));
  }


} // class DirectoryPOA