package co.com.myndfuly.persistence.repository;

import co.com.myndfuly.persistence.entity.ContactEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, Integer> {
  List<ContactEntity> findByCustomerId(Integer customerId);

  @Transactional
  @Modifying
  @Query(
      """
        UPDATE ContactEntity c
            SET c.age = c.age + 1
        WHERE MONTH(c.birth) = :month AND DAY(c.birth) = :day
        """)
  void updateScheduledAge(@Param("month") Integer month, @Param("day") Integer day);
}
