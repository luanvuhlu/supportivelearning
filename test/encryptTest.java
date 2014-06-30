
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luan
 */
public class encryptTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        DESKeySpec keySpec = new DESKeySpec("luanvu123".getBytes("UTF8"));
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
SecretKey key = keyFactory.generateSecret(keySpec);
sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
String plainTextPassword="123";

// ENCODE plainTextPassword String
byte[] cleartext = plainTextPassword.getBytes("UTF8");      

Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
cipher.init(Cipher.ENCRYPT_MODE, key);
String encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));
// now you can store it 
System.out.println(encrypedPwd);

// DECODE encryptedPwd String
byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encrypedPwd);

Cipher cipher2 = Cipher.getInstance("DES");// cipher is not thread safe
cipher2.init(Cipher.DECRYPT_MODE, key);
byte[] plainTextPwdBytes = (cipher2.doFinal(encrypedPwdBytes));
        System.out.println(new String(plainTextPwdBytes));
    }

}
