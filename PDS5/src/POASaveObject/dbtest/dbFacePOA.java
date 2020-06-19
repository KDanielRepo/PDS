package POASaveObject.dbtest;


/**
* dbtest/dbFacePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dbtest.idl
* Thursday, June 18, 2020 6:33:42 PM CEST
*/

public abstract class dbFacePOA extends org.omg.PortableServer.Servant
 implements POASaveObject.dbtest.dbFaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getName", new java.lang.Integer (0));
    _methods.put ("getLastQuery", new java.lang.Integer (1));
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
       case 0:  // dbtest/dbFace/getName
       {
         int primary_key = in.read_long ();
         String $result = null;
         $result = this.getName (primary_key);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // dbtest/dbFace/getLastQuery
       {
         String $result = null;
         $result = this.getLastQuery ();
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
    "IDL:dbtest/dbFace:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public dbFace _this() 
  {
    return dbFaceHelper.narrow(
    super._this_object());
  }

  public dbFace _this(org.omg.CORBA.ORB orb) 
  {
    return dbFaceHelper.narrow(
    super._this_object(orb));
  }


} // class dbFacePOA
