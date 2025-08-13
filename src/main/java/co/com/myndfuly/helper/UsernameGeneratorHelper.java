package co.com.myndfuly.helper;

import co.com.myndfuly.data.BasicInfoCustomerData;
import org.springframework.stereotype.Component;

@Component
public class UsernameGeneratorHelper {

  public static String generateUsername(BasicInfoCustomerData customerData) {
    String firstCharsName = sanitizeAndTruncate(customerData.getName(), 3);
    String firstCharsLastName = sanitizeAndTruncate(customerData.getLastName(), 3);
    int birthYear = customerData.getBirth().getYear();

    String baseUsername = firstCharsName + firstCharsLastName + birthYear;

    return "@" + baseUsername;
  }

  private static String sanitizeAndTruncate(String text, int length) {
    String sanitized = text.toLowerCase();

    sanitized =
        sanitized
            .replace("á", "a")
            .replace("é", "e")
            .replace("í", "i")
            .replace("ó", "o")
            .replace("ú", "u");
    sanitized = sanitized.replace("ñ", "n");

    sanitized = sanitized.replaceAll("[^a-z0-9]", "");

    if (sanitized.length() > length) {
      return sanitized.substring(0, length);
    }

    return sanitized;
  }
}
