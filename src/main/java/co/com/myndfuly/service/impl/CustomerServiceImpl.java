package co.com.myndfuly.service.impl;

import co.com.myndfuly.data.BasicInfoCustomerData;
import co.com.myndfuly.data.CustomerData;
import co.com.myndfuly.helper.UsernameGeneratorHelper;
import co.com.myndfuly.persistence.CustomerRepository;
import co.com.myndfuly.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerJpaRepository;

  @Override
  public CustomerData getCustomerById(Integer customerId) {
    log.info("Start getCustomerById customerId {}", customerId);
    return customerJpaRepository.findById(customerId);
  }

  @Override
  public CustomerData getCustomerByEmail(String email) {
    log.info("Start getCustomerByEmail email {}", email);
    return customerJpaRepository.findByEmail(email);
  }

  @Override
  public CustomerData saveCustomer(CustomerData customer) {
    log.info("Start saveCustomer customer {}", customer);
    return customerJpaRepository.saveCustomer(customer);
  }

  @Override
  public CustomerData updateBasicInfo(Integer customerId, BasicInfoCustomerData customerData) {
    log.info("Start updateBasicInfo customerId {}", customerId);
    customerData.setUsername(UsernameGeneratorHelper.generateUsername(customerData));
    return customerJpaRepository.updateBasicInfo(customerId, customerData);
  }
}
