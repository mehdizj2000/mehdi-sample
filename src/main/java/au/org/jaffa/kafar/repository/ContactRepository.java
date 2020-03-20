package au.org.jaffa.kafar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.org.jaffa.kafar.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    void deleteByNumber(String number);

}
