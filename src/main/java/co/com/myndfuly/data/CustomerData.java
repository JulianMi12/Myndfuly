package co.com.myndfuly.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerData {
  private Integer id;
  private String name;
  private String lastName;
  private LocalDate birth;
  private String email;
  private String password;
  private String username;
}
