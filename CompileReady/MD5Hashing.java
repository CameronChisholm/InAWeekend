import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class MD5Hashing
{
	public String hash(String messagePlainText)
	{
		String myHash = "";
		byte[] digest;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(messagePlainText.getBytes());
			digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		}
		catch(Exception ex)
		{
			System.out.println("Exception thrown.");
		}

		return myHash;
	}
}