package co.com.myndfuly.helper;

import co.com.myndfuly.exception.RestException;
import co.com.myndfuly.exception.enums.ErrorReason;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AesCryptoHelper {

  @Value("${co.com.myndfuly.aes-key}")
  private String aesKey;

  private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final int AES_BLOCK_SIZE = 16;
  private static final SecureRandom secureRandom = new SecureRandom();

  /**
   * Encripta una cadena de texto y la devuelve como una cadena Base64-URL. El IV se genera
   * aleatoriamente y se concatena al inicio de los datos encriptados.
   *
   * @param data La cadena de texto a encriptar.
   * @return La cadena encriptada en formato Base64-URL.
   */
  public String encrypt(String data) {
    if (data == null || data.isEmpty()) {
      return null;
    }

    try {
      byte[] iv = new byte[AES_BLOCK_SIZE];
      secureRandom.nextBytes(iv);
      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

      SecretKeySpec secretKeySpec =
          new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");

      byte[] encryptedData = encryptData(data, secretKeySpec, ivParameterSpec);

      ByteBuffer buffer = ByteBuffer.allocate(iv.length + encryptedData.length);
      buffer.put(iv);
      buffer.put(encryptedData);

      return Base64.getUrlEncoder().encodeToString(buffer.array());

    } catch (Exception e) {
      log.error("Encryption failed with an error: {}", e.getMessage(), e);
      throw RestException.builder()
          .reason(ErrorReason.INTERNAL_SERVER_ERROR)
          .message("Encryption failed due to an internal error.")
          .build();
    }
  }

  /**
   * Desencripta una cadena de texto en formato Base64-URL.
   *
   * @param dataBase64 La cadena a desencriptar.
   * @return La cadena desencriptada.
   */
  public String decrypt(String dataBase64) {
    if (dataBase64 == null || dataBase64.isEmpty()) {
      return null;
    }

    try {
      byte[] decryptedBytes = Base64.getUrlDecoder().decode(dataBase64);

      if (decryptedBytes.length < AES_BLOCK_SIZE) {
        throw RestException.builder()
            .reason(ErrorReason.BAD_REQUEST)
            .message("Invalid data length for decryption. Must be at least AES_BLOCK_SIZE.")
            .build();
      }

      ByteBuffer buffer = ByteBuffer.wrap(decryptedBytes);
      byte[] iv = new byte[AES_BLOCK_SIZE];
      buffer.get(iv);
      byte[] encryptedData = new byte[buffer.remaining()];
      buffer.get(encryptedData);

      SecretKeySpec secretKeySpec =
          new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

      return decryptData(encryptedData, secretKeySpec, ivParameterSpec);

    } catch (IllegalArgumentException e) {
      log.warn("Invalid Base64-URL data format during decryption: {}", e.getMessage());
      throw RestException.builder()
          .reason(ErrorReason.BAD_REQUEST)
          .message("Invalid Base64-URL data format.")
          .build();
    } catch (Exception e) {
      log.error("Decryption failed with an internal error: {}", e.getMessage(), e);
      throw RestException.builder()
          .reason(ErrorReason.INTERNAL_SERVER_ERROR)
          .message("Decryption failed due to an internal error.")
          .build();
    }
  }

  private byte[] encryptData(String plainText, SecretKeySpec key, IvParameterSpec iv)
      throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
  }

  private String decryptData(byte[] cipherText, SecretKeySpec key, IvParameterSpec iv)
      throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key, iv);
    byte[] plainText = cipher.doFinal(cipherText);
    return new String(plainText, StandardCharsets.UTF_8);
  }
}
