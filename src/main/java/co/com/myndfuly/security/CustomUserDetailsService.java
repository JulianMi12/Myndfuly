package co.com.myndfuly.security;

import co.com.myndfuly.data.CustomerData;
import co.com.myndfuly.persistence.CustomerRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomerData customer = customerRepository.findById(Integer.parseInt(username));

    return new User(
        String.valueOf(customer.getId()), customer.getPassword(), Collections.emptyList());
  }
}
