package co.com.myndfuly.persistence.impl;

import co.com.myndfuly.data.ContactData;
import co.com.myndfuly.mapper.ContactMapper;
import co.com.myndfuly.persistence.ContactRepository;
import co.com.myndfuly.persistence.repository.ContactJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {

  private final ContactJpaRepository contactJpaRepository;

  @Override
  public void saveContact(Integer customerId, ContactData contactData) {
    contactJpaRepository.save(ContactMapper.INSTANCE.toEntity(customerId, contactData));
  }

  @Override
  public List<ContactData> getContacts(Integer customerId) {
    return ContactMapper.INSTANCE.toData(contactJpaRepository.findByCustomerId(customerId));
  }

  @Override
  public void updateScheduledAge(Integer currentMonth, Integer currentDay) {
    contactJpaRepository.updateScheduledAge(currentMonth, currentDay);
  }
}
