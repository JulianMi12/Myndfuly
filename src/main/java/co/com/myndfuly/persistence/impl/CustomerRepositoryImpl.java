package co.com.myndfuly.persistence.impl;

import co.com.myndfuly.data.BasicInfoCustomerData;
import co.com.myndfuly.data.CustomerData;
import co.com.myndfuly.mapper.CustomerMapper;
import co.com.myndfuly.persistence.CustomerRepository;
import co.com.myndfuly.persistence.entity.CustomerEntity;
import co.com.myndfuly.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;

  @Override
  public CustomerData findById(Integer customerId) {
    return CustomerMapper.INSTANCE.toData(
        customerJpaRepository.findById(customerId).orElse(new CustomerEntity()));
  }

  @Override
  public CustomerData findByEmail(String email) {
    return CustomerMapper.INSTANCE.toData(customerJpaRepository.findByEmail(email).orElse(null));
  }

  @Override
  public CustomerData saveCustomer(CustomerData customer) {
    return CustomerMapper.INSTANCE.toData(
        customerJpaRepository.save(CustomerMapper.INSTANCE.toEntity(customer)));
  }

  @Override
  public CustomerData updateBasicInfo(Integer customerId, BasicInfoCustomerData customerData) {
    customerJpaRepository.updateBasicInfo(
        customerId,
        customerData.getName(),
        customerData.getLastName(),
        customerData.getBirth(),
        customerData.getUsername());

    return findById(customerId);
  }
}
