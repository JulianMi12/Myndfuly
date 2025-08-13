package co.com.myndfuly.persistence;

import co.com.myndfuly.data.BasicInfoCustomerData;
import co.com.myndfuly.data.CustomerData;

public interface CustomerRepository {
  CustomerData findById(Integer customerId);

  CustomerData findByEmail(String email);

  CustomerData saveCustomer(CustomerData customer);

  CustomerData updateBasicInfo(Integer customerId, BasicInfoCustomerData customerData);
}
