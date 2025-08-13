package co.com.myndfuly.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", length = 80)
  private String name;

  @Column(name = "last_name", length = 80)
  private String lastName;

  @Column(name = "birth")
  private LocalDate birth;

  @Column(name = "email", length = 80)
  private String email;

  @Column(name = "username", length = 40)
  private String username;

  @Column(name = "password", length = 80)
  private String password;
}
