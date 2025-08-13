package co.com.myndfuly.controller.impl;

import co.com.myndfuly.controller.ContactController;
import co.com.myndfuly.controller.dto.ContactDto;
import co.com.myndfuly.mapper.ContactMapper;
import co.com.myndfuly.service.ContactService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/myndfuly/contact")
public class ContactControllerImpl implements ContactController {

  private final ContactService contactService;

  @Override
  @PostMapping("/create")
  public void createContact(
      @RequestHeader(name = "Claim-User-Id") Integer customerId,
      @RequestBody ContactDto contactDto) {
    log.info("Start GET /contact/create customerId: {}", customerId);
    contactService.createContact(customerId, ContactMapper.INSTANCE.toData(contactDto));
  }

  @Override
  @GetMapping("/get-contacts")
  public List<ContactDto> getContacts(@RequestHeader(name = "Claim-User-Id") Integer customerId) {
    log.info("Start GET /contact/get-contacts customerId: {}", customerId);
    return ContactMapper.INSTANCE.toDto(contactService.getContacts(customerId));
  }
}
