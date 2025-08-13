package co.com.myndfuly.controller;

import co.com.myndfuly.controller.dto.BasicInfoCustomerDto;
import co.com.myndfuly.controller.dto.CustomerDto;

public interface CustomerController {
  CustomerDto getCustomerInfo(Integer customerId);

  CustomerDto updateCustomerInfo(Integer customerId, BasicInfoCustomerDto basicInfoCustomerDto);
}
