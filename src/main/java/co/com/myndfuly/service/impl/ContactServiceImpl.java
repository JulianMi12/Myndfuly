package co.com.myndfuly.service.impl;

import co.com.myndfuly.data.ContactData;
import co.com.myndfuly.persistence.ContactRepository;
import co.com.myndfuly.service.ContactService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  @Override
  public void createContact(Integer customerId, ContactData contactData) {
    log.info("Start createContact customerId {}", customerId);
    contactRepository.saveContact(customerId, contactData);
    log.info("End createContact customerId {}", customerId);
  }

  @Override
  public List<ContactData> getContacts(Integer customerId) {
    log.info("Start getContacts customerId {}", customerId);
    return contactRepository.getContacts(customerId);
  }

  @Scheduled(cron = "${co.com.myndfuly.schedule.birthday}")
  private void updateContactAgesForBirthday() {
    log.info("Start updateContactAgesForBirthday");
    LocalDate today = LocalDate.now();
    contactRepository.updateScheduledAge(today.getMonthValue(), today.getDayOfMonth());
    log.info("End updateContactAgesForBirthday");
  }
}
