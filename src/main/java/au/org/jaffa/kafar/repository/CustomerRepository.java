package au.org.jaffa.kafar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import au.org.jaffa.kafar.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFirstName(String firstName);

}
