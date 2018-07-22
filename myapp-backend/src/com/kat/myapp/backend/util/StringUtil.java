package com.kat.myapp.backend.util;

public class StringUtil {
	public static final int _RIGHT = 0;
	public static final int _LEFT  = 1;

	
	public static String rightPadString(String string, int length) {
	    return paddingSpace(string, length, _RIGHT);    
	}
	
	public static String leftPadString(String string, int length) {
	    return paddingSpace(string, length, _LEFT);    
	}
	
	
	
	public static String paddingSpace(String string, int length, int direction) {
		if ( string == null )
			string = "";
		if ( direction == _LEFT )
	        return String.format("%1$"+length+ "s", string);
	    return String.format("%1$-"+length+ "s", string);    	
	}
	
    public static String addQuotes(String item) {
        return "'" +item + "'";
    }
    
    public static String protectQuote(String s)
    {
        return s.replaceAll("'", "''");
    }
    
    public static boolean isEmptyOrSpaces(String s)
    {
      if ((s == null) || (s.length() == 0))
        return true;
      
      int length = s.length();
      
      for (int i = 0; i < length; i++)
      {
        if (s.charAt(i) != ' ')
          return false;
      }
      
      return true;
    }
    
    public static byte[] hexStringToByteArray(String hex)
    {
        byte[] result = new byte[hex.length() / 2];
        int    i;
            
        for (i = 0; i < result.length; i++)
        {
          result[i] = 
                (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
           
        return result;
    }
        
    public static String byteArrayToHexString(byte in[]) 
    {
        byte ch;
        int  i  = 0; 
            
        if (in == null || in.length <= 0) return null;
        
        String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
                            "A", "B", "C", "D", "E", "F" };
            
        StringBuilder out = new StringBuilder(in.length * 2);
    
        while (i < in.length) 
        {
            ch = (byte) (in[i] & 0xF0); // Strip off high nibble
            ch = (byte) (ch >>> 4);     // shift the bits down
            ch = (byte) (ch & 0x0F);    // must do this is high order bit is on!
            out.append(pseudo[ (int) ch]); // convert the nibble to a String Character
            ch = (byte) (in[i] & 0x0F); // Strip off low nibble 
            out.append(pseudo[ (int) ch]); // convert the nibble to a String Character
            i++;
        }
        
        return out.toString();
    }    

}
