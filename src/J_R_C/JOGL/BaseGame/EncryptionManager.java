
package J_R_C.JOGL.BaseGame;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class EncryptionManager {
	// public static String encrypt(String message) throws Exception {
	//
	// // use key coss2
	// SecretKeySpec skeySpec = new
	// SecretKeySpec(Settings.sEncryptKey.getBytes(), "AES");
	//
	// // Instantiate the cipher
	// Cipher cipher = Cipher.getInstance("AES");
	// cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	//
	// byte[] encrypted = cipher.doFinal(message.getBytes());
	//
	// return Hex.encodeHexString(encrypted);
	//
	// }
	//
	// public static String decrypt(String encrypted) throws Exception {
	//
	// // use key coss2
	// SecretKeySpec skeySpec = new
	// SecretKeySpec(Settings.sEncryptKey.getBytes(), "AES");
	//
	// Cipher cipher = Cipher.getInstance("AES");
	// cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	//
	// byte[] original = cipher.doFinal(Hex.decodeHex(encrypted.toCharArray()));
	//
	// String originalString = new String(original);
	//
	// return originalString;
	// }

	private static Key generateKey() throws Exception {
		byte[] keyValue = Settings.keyStr.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		keyValue = sha.digest(keyValue);
		keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit
		Key key = new SecretKeySpec(keyValue, Settings.ALGO);
		return key;
	}

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Settings.ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Settings.ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
}
