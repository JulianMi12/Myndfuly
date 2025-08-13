package co.com.myndfuly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyndfulyApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyndfulyApplication.class, args);
  }
}
