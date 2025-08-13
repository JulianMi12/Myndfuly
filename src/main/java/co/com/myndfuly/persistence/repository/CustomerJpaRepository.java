package co.com.myndfuly.persistence.repository;

import co.com.myndfuly.persistence.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
  Optional<CustomerEntity> findByEmail(String email);

  @Transactional
  @Modifying
  @Query(
      """
          UPDATE CustomerEntity c
              SET c.name = COALESCE(:name, c.name),
                  c.lastName = COALESCE(:lastName, c.lastName),
                  c.birth = COALESCE(:birth, c.birth),
                  c.username = COALESCE(:username, c.username)
          WHERE c.id = :customerId
          """)
  void updateBasicInfo(
      @Param("customerId") Integer customerId,
      @Param("name") String name,
      @Param("lastName") String lastName,
      @Param("birth") LocalDate birth,
      @Param("username") String username);
}
