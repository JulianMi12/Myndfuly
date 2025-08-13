package co.com.myndfuly.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasicInfoCustomerData {
  private String name;
  private String lastName;
  private LocalDate birth;
  private String username;
}
