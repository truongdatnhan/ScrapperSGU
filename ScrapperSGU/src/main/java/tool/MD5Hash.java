package tool;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {	
	public static String getMd5(String password) {
		String generatedPass=null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(password.getBytes());
			
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)	{
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
			generatedPass = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedPass;
	}
}
