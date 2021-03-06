package POADirectory.dirinfo;

/**
* dirinfo/_DirectoryStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dirinfo.idl
* Thursday, June 18, 2020 6:18:13 PM CEST
*/

public class _DirectoryStub extends org.omg.CORBA.portable.ObjectImpl implements POADirectory.dirinfo.Directory
{

  public String[] getDirList (String subdir)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getDirList", true);
                $out.write_string (subdir);
                $in = _invoke ($out);
                String $result[] = POADirectory.dirinfo.DirectoryPackage.dlistHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getDirList (subdir        );
            } finally {
                _releaseReply ($in);
            }
  } // getDirList

  public String getFileInfo (String filename)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getFileInfo", true);
                $out.write_string (filename);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getFileInfo (filename        );
            } finally {
                _releaseReply ($in);
            }
  } // getFileInfo

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:dirinfo/Directory:1.0"};

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
} // class _DirectoryStub
