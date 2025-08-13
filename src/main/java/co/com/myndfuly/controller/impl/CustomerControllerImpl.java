package co.com.myndfuly.controller.impl;

import co.com.myndfuly.controller.CustomerController;
import co.com.myndfuly.controller.dto.BasicInfoCustomerDto;
import co.com.myndfuly.controller.dto.CustomerDto;
import co.com.myndfuly.mapper.CustomerMapper;
import co.com.myndfuly.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/myndfuly/customer")
public class CustomerControllerImpl implements CustomerController {

  private final CustomerService customerService;

  @Override
  @GetMapping("/customer-info")
  public CustomerDto getCustomerInfo(@RequestHeader(name = "Claim-User-Id") Integer customerId) {
    log.info("Start GET /customer/customer-info customerId: {}", customerId);
    return CustomerMapper.INSTANCE.toDto(customerService.getCustomerById(customerId));
  }

  @Override
  @PutMapping("/update-basic-info")
  public CustomerDto updateCustomerInfo(
      @RequestHeader(name = "Claim-User-Id") Integer customerId,
      @RequestBody BasicInfoCustomerDto basicInfoCustomerDto) {
    log.info("Start GET /customer/update-basic-info customerId: {}", customerId);
    return CustomerMapper.INSTANCE.toDto(
        customerService.updateBasicInfo(
            customerId, CustomerMapper.INSTANCE.toData(basicInfoCustomerDto)));
  }
}
