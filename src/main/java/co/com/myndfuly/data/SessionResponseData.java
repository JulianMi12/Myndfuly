package co.com.myndfuly.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionResponseData {
  private Integer id;
  private String email;
  private String accessToken;
  private String untilDate;
}
