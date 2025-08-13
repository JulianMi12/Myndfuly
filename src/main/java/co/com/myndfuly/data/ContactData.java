package co.com.myndfuly.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactData {
  private Integer id;
  private Integer customerId;
  private String name;
  private LocalDate birth;
  private Integer age;
  private String callingCode;
  private String phoneNumber;
}
