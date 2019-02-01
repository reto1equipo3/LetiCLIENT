package easyorderappclient.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

/**
 * Custom class for cryptography.
 *
 * @author Imanol
 */
public class Crypto {

	/**
	 * Encrypts a password using public key.
	 *
	 * @param password Password to encrypt.
	 * @return Encrypted password.
	 */
	public static String encryptPassword(String password) {
		String encryptedPassword = null;

		FileInputStream fileInputStream = null;
		try {
			Logger.getLogger("easyorderappclient").log(Level.INFO, "Crypto: Encrypting password.");
			/*
			 * We have to read (as a byte[]) the key with which the text is going to be
			 * ciphered, the public key.
			 */
			fileInputStream = new FileInputStream("./src/easyorderappclient/config/easyorderappPublic.key");
			byte[] inputPublicKey = new byte[fileInputStream.available()];
			fileInputStream.read(inputPublicKey);

			/*
			 * To encrypt, we need an object of type PublicKey. This object is going to be
			 * created using the method generatePublic() from the class KeyFactory and using
			 * the specification X509EncodedKeySpec.
			 */
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(inputPublicKey);
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);

			/*
			 * The next step is to create an object of the class Cipher, specifying which 
			 * algorithm is going to be used ("RSA/ECB/PKCS1Padding"). This object must be
			 * initialized in encrypt mode using the previously created key. The message is 
			 * encrypted with the method doFinal().
			 */
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedPasswordArray = cipher.doFinal(password.getBytes());

			encryptedPassword = DatatypeConverter.printHexBinary(encryptedPasswordArray);

		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
			Logger.getLogger("easyorderappclient").log(Level.SEVERE, "Crypto: Error encrypting password, {0}.", ex.getMessage());
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException ex) {
				// Error closing input stream file
			}
		}
		Logger.getLogger("easyorderappclient").log(Level.INFO, "Crypto: Password encrypted.");
		return encryptedPassword;
	}

}
