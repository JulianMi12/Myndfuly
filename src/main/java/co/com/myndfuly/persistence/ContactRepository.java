package co.com.myndfuly.persistence;

import co.com.myndfuly.data.ContactData;
import java.util.List;

public interface ContactRepository {
  void saveContact(Integer customerId, ContactData contactData);

  List<ContactData> getContacts(Integer customerId);

  void updateScheduledAge(Integer currentMonth, Integer currentDay);
}
