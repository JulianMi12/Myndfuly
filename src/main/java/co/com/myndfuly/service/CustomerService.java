package co.com.myndfuly.service;

import co.com.myndfuly.data.BasicInfoCustomerData;
import co.com.myndfuly.data.CustomerData;

public interface CustomerService {
  CustomerData getCustomerById(Integer customerId);

  CustomerData getCustomerByEmail(String email);

  CustomerData saveCustomer(CustomerData customer);

  CustomerData updateBasicInfo(Integer customerId, BasicInfoCustomerData customerData);
}
