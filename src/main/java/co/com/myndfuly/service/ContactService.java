package co.com.myndfuly.service;

import co.com.myndfuly.data.ContactData;
import java.util.List;

public interface ContactService {
  void createContact(Integer customerId, ContactData contactData);

  List<ContactData> getContacts(Integer customerId);
}
