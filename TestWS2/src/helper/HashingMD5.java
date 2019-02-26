package helper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class HashingMD5 {
	
	public String getHashPin(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

	
	    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException
	    {
	        String passwordToHash = "1234";
	        byte[] salt = "546789".getBytes();
	         
	        String securePassword = getSecurePassword(passwordToHash, salt);
	        System.out.println(securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66
	        //fa3862cace8227c03da68488bc2dd897
	        //28422d291c732be387e191f266f77da9
	         
	        String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);
	        System.out.println(regeneratedPassowrdToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
	    }
	     
	    private static String getSecurePassword(String passwordToHash, byte[] salt)
	    {
	        String generatedPassword = null;
	        try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(salt);
	            //Get the hash's bytes
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	     
	    //Add salt
	    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
	    {
	        //Always use a SecureRandom generator
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
	        //Create array for salt
	        byte[] salt = new byte[16];
	        //Get a random salt
	        sr.nextBytes(salt);
	        //return salt
	        return salt;
	    }
	

}

