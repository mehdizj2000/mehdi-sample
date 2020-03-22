package au.org.jaffa.kafar.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import au.org.jaffa.kafar.domain.Contact;
import au.org.jaffa.kafar.domain.ContactType;
import au.org.jaffa.kafar.domain.Customer;
import au.org.jaffa.kafar.domain.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CustomerRepositoryTest {
    
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    void injectedComponentsAreNotNull() {
	assertThat(dataSource).isNotNull();
	assertThat(jdbcTemplate).isNotNull();
	assertThat(entityManager).isNotNull();
	assertThat(customerRepository).isNotNull();
    }

    @Test
    @Rollback(false)
    void test() {

	Role adminRole = new Role();
	adminRole.setCode("ADMIN");
	adminRole.setName("Administration");

	Role userRole = new Role();
	userRole.setCode("USER");
	userRole.setName("General User");

	Contact contact = new Contact();
	contact.setContactType(ContactType.MOBILE);
	contact.setNumber("040404040");

	Contact contact2 = new Contact();
	contact2.setContactType(ContactType.PHONE);
	contact2.setNumber("2348756234");

	Customer customer = new Customer();
	customer.setFirstName("leo");
	customer.setLastName("ryan");
	customer.addContact(contact);
	customer.addContact(contact2);

	customer.addRole(adminRole);
	customer.addRole(userRole);

	Customer savedCust = customerRepository.save(customer);

	assertNotNull(savedCust);

	Optional<Customer> optCust = customerRepository.findByFirstName("leo");

	assertThat(optCust).isPresent();

	Customer cust01 = optCust.get();

	Set<Contact> contacts = cust01.getContacts();
	MatcherAssert.assertThat(contacts, Matchers.hasSize(2));
	
	log.info("contacts: {}", contacts);

	Set<Role> roles = cust01.getRoles();
	MatcherAssert.assertThat(roles, Matchers.hasSize(2));

	log.info("roles: {}", roles);
	
	Contact contact3 = new Contact();
	contact3.setContactType(ContactType.PHONE);
	contact3.setNumber("2348756234");
	
	cust01.removeContact(contact3);
	customerRepository.save(cust01);
	
    }

}
