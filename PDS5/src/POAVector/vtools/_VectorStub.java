package POAVector.vtools;


/**
* vtools/_VectorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from vector.idl
* Thursday, June 18, 2020 6:34:21 PM CEST
*/


// wector
public class _VectorStub extends org.omg.CORBA.portable.ObjectImpl implements POAVector.vtools.Vector
{


  // dodawanie wektorow c = a + b
  public int[] add (short size, int[] a, int[] b)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add", true);
                $out.write_short (size);
                POAVector.vtools.VectorPackage.arrayHelper.write ($out, a);
                POAVector.vtools.VectorPackage.arrayHelper.write ($out, b);
                $in = _invoke ($out);
                int $result[] = POAVector.vtools.VectorPackage.arrayHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add (size, a, b        );
            } finally {
                _releaseReply ($in);
            }
  } // add


  // odejmowanie wektorow c = a - b
  public int[] sub (short size, int[] a, int[] b)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sub", true);
                $out.write_short (size);
                POAVector.vtools.VectorPackage.arrayHelper.write ($out, a);
                POAVector.vtools.VectorPackage.arrayHelper.write ($out, b);
                $in = _invoke ($out);
                int $result[] = POAVector.vtools.VectorPackage.arrayHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sub (size, a, b        );
            } finally {
                _releaseReply ($in);
            }
  } // sub

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:vtools/Vector:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _VectorStub