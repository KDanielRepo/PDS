package POAgetfile.dirinfo;


/**
* dirinfo/DirectoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dirinfo.idl
* Thursday, June 18, 2020 6:33:30 PM CEST
*/

public interface DirectoryOperations 
{
  String[] getDirList (String subdir);
  String getFileInfo (String filename);
} // interface DirectoryOperations
