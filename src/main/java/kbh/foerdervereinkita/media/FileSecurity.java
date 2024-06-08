package kbh.foerdervereinkita.media;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileSecurity {

  private final Key secretKey;
  private final Cipher cipher;

  private static final String ENC_SUFFIX = ".enc";

  public FileSecurity(
      @Value("${CRYPT_KEY}") String cryptKey,
      @Value("${CRYPT_ALGORITHM}") String algorithm,
      @Value("${CRYPT_TRANSFORMATION}") String transformation)
      throws NoSuchPaddingException, NoSuchAlgorithmException {

    this.secretKey = new SecretKeySpec(cryptKey.getBytes(), algorithm);
    this.cipher = Cipher.getInstance(transformation);
  }

  public byte[] encrypt(byte[] bytes)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return cipher.doFinal(bytes);
  }

  public byte[] decrypt(byte[] bytes)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    return cipher.doFinal(bytes);
  }
}
