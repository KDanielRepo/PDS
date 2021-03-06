package POAVector.vtools;


/**
* vtools/VectorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vector.idl
* Thursday, June 18, 2020 6:34:21 PM CEST
*/


// wector
public abstract class VectorPOA extends org.omg.PortableServer.Servant
 implements POAVector.vtools.VectorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("add", new java.lang.Integer (0));
    _methods.put ("sub", new java.lang.Integer (1));
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

  // dodawanie wektorow c = a + b
       case 0:  // vtools/Vector/add
       {
         short size = in.read_short ();
         int a[] = POAVector.vtools.VectorPackage.arrayHelper.read (in);
         int b[] = POAVector.vtools.VectorPackage.arrayHelper.read (in);
         int $result[] = null;
         $result = this.add (size, a, b);
         out = $rh.createReply();
         POAVector.vtools.VectorPackage.arrayHelper.write (out, $result);
         break;
       }


  // odejmowanie wektorow c = a - b
       case 1:  // vtools/Vector/sub
       {
         short size = in.read_short ();
         int a[] = POAVector.vtools.VectorPackage.arrayHelper.read (in);
         int b[] = POAVector.vtools.VectorPackage.arrayHelper.read (in);
         int $result[] = null;
         $result = this.sub (size, a, b);
         out = $rh.createReply();
         POAVector.vtools.VectorPackage.arrayHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:vtools/Vector:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Vector _this() 
  {
    return VectorHelper.narrow(
    super._this_object());
  }

  public Vector _this(org.omg.CORBA.ORB orb) 
  {
    return VectorHelper.narrow(
    super._this_object(orb));
  }


} // class VectorPOA
