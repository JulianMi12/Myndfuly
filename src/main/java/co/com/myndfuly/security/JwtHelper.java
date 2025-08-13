package co.com.myndfuly.security;

import co.com.myndfuly.data.SessionResponseData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiration-time}")
  private long expirationTime;

  /**
   * Genera un token JWT para un usuario.
   *
   * @param customerId El ID del usuario.
   * @param email El email del usuario.
   * @return El token JWT como String.
   */
  public SessionResponseData generateToken(String customerId, String email) {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + expirationTime);

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    return buildSessionResponseData(
        Integer.parseInt(customerId),
        email,
        JWT.create()
            .withSubject(customerId)
            .withIssuedAt(now)
            .withExpiresAt(expirationDate)
            .sign(algorithm));
  }

  /**
   * Valida un token JWT y devuelve los datos si es válido.
   *
   * @param token El token JWT a validar.
   * @return El objeto DecodedJWT con los datos del token.
   * @throws JWTVerificationException si el token no es válido o ha expirado.
   */
  public DecodedJWT validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.require(algorithm).build().verify(token);
  }

  /**
   * Extrae el ID de usuario de un token válido.
   *
   * @param token El token JWT.
   * @return El ID de usuario.
   */
  public String getUserIdFromToken(String token) {
    DecodedJWT decodedJWT = validateToken(token);
    return decodedJWT.getSubject();
  }

  private SessionResponseData buildSessionResponseData(
      Integer customerId, String email, String token) {
    SessionResponseData sessionResponseData = new SessionResponseData();
    sessionResponseData.setId(customerId);
    sessionResponseData.setEmail(email);
    sessionResponseData.setAccessToken(token);
    sessionResponseData.setUntilDate(calculateExpirationTime());
    return sessionResponseData;
  }

  private String calculateExpirationTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    ZonedDateTime now = ZonedDateTime.now();
    Duration expirationDuration = Duration.ofMillis(expirationTime);
    ZonedDateTime futureDateTime = now.plus(expirationDuration);
    return futureDateTime.format(formatter);
  }
}
