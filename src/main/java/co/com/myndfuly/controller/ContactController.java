package co.com.myndfuly.controller;

import co.com.myndfuly.controller.dto.ContactDto;
import java.util.List;

public interface ContactController {
  void createContact(Integer customerId, ContactDto contactDto);

  List<ContactDto> getContacts(Integer customerId);
}
