package co.com.myndfuly.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "contact")
public class ContactEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "customer_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "CUS_CON_FK"),
      nullable = false)
  private CustomerEntity customer;

  @Column(name = "name", length = 80)
  private String name;

  @Column(name = "birth")
  private LocalDate birth;

  @Column(name = "age")
  private Integer age;

  @Column(name = "calling_code", length = 10)
  private String callingCode;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;
}
