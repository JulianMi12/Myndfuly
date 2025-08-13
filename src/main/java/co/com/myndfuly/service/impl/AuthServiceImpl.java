package co.com.myndfuly.service.impl;

import co.com.myndfuly.data.CustomerData;
import co.com.myndfuly.data.SessionResponseData;
import co.com.myndfuly.data.SignUpRequestData;
import co.com.myndfuly.exception.RestException;
import co.com.myndfuly.exception.enums.ErrorReason;
import co.com.myndfuly.security.JwtHelper;
import co.com.myndfuly.service.AuthService;
import co.com.myndfuly.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final CustomerService customerService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  @Override
  public SessionResponseData signUp(SignUpRequestData signUpRequest) {
    log.info("Start signUp email {}", signUpRequest.getEmail());
    CustomerData newCustomer = customerService.saveCustomer(buildCustomerData(signUpRequest));
    return jwtHelper.generateToken(newCustomer.getId().toString(), newCustomer.getEmail());
  }

  @Override
  public SessionResponseData login(String email, String password) {
    log.info("Start login email {}", email);
    CustomerData customer = customerService.getCustomerByEmail(email);

    if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
      return jwtHelper.generateToken(customer.getId().toString(), customer.getEmail());
    }

    throw RestException.builder()
        .reason(ErrorReason.UNAUTHORIZED)
        .message("Invalid credentials.")
        .build();
  }

  private CustomerData buildCustomerData(SignUpRequestData signUpRequest) {
    CustomerData customer = new CustomerData();
    customer.setEmail(signUpRequest.getEmail());
    customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    return customer;
  }
}
